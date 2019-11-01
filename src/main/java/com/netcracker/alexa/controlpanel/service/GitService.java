package com.netcracker.alexa.controlpanel.service;

import com.netcracker.alexa.controlpanel.exception.InvalidLocationDateException;
import com.netcracker.alexa.controlpanel.model.db.entity.userpage.Location;
import com.netcracker.alexa.controlpanel.repository.LocationRepository;
import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class GitService {
    private static final Logger logger = LoggerFactory.getLogger(GitService.class);

    private static final String REMOTE_URL = "https://github.com/ncdemo18/ncdemo18.github.io.git";
    private static final String USERNAME = "ncdemo18";

    @Value("${GIT_PASSWORD}")
    private String PASSWORD;

    @Autowired
    private ValidateLocationService validateService;

    @Autowired
    private NameLocationResolverService nameService;

    @Autowired
    private LocationRepository locationRepository;

    public void addLocationOnRepository(String nameLocation, List<MultipartFile> files) throws GitAPIException, IOException, InvalidLocationDateException {
        validateService.checkCorrectLocationDate(nameLocation, files);

        Path localPath = Files.createTempDirectory("testJGitRepository");
        logger.info("Cloning from {} to {}", REMOTE_URL, localPath);
        try (Git result = Git.cloneRepository()
                .setURI(REMOTE_URL)
                .setDirectory(localPath.toFile())
                .call()) {
            logger.info("Having repository: {}", result.getRepository().getDirectory());

            String strLocationFolder = "//mockup//" + nameLocation + "//bg";
            String strTargetFolder = result.getRepository().getDirectory().getParent() + strLocationFolder;
            Path directory = Files.createDirectories(Paths.get(strTargetFolder));
            for (int i = 0; i < files.size(); i++) {
                int numberPage = i + 1;
                Path targetPath = directory.resolve(nameService.resolveName(numberPage));
                Files.write(targetPath, files.get(i).getBytes());
            }

            result.add().addFilepattern(".").call();

            result.commit().setMessage("Added location files").call();
            logger.info("Committed files to repository at {}", result.getRepository().getDirectory());

            result.push().setCredentialsProvider(new UsernamePasswordCredentialsProvider(USERNAME, PASSWORD)).call();
            logger.info("Pushed from repository {} to remote repository at {}", result.getRepository().getDirectory(), REMOTE_URL);

            Location location = new Location(nameLocation);
            if(!locationRepository.existsByLocationName(location.getLocationName())) {
                locationRepository.save(location);
            }
        } finally {
            FileUtils.deleteDirectory(localPath.toFile());
        }
    }
}
