package com.netcracker.alexa.controlpanel.controller;

import com.netcracker.alexa.controlpanel.exception.InvalidLocationDateException;
import com.netcracker.alexa.controlpanel.service.GitService;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class UploadController {

    @Autowired
    private GitService gitService;

    @GetMapping("/addLocationFiles")
    public String start() {
        return "add_location";
    }

    @PostMapping("/addLocationFiles")
    public String addLocationFiles(Model model,
                                   @RequestParam("name-location") String nameLocation,
                                   @RequestParam("files") List<MultipartFile> files) throws IOException, GitAPIException {
        try {
            gitService.addLocationOnRepository(nameLocation, files);
        } catch (InvalidLocationDateException ex) {
            model.addAttribute("textError", ex.getMessage());
            return "add_location_error";
        }
        return "redirect:/addLocationFiles";
    }
}
