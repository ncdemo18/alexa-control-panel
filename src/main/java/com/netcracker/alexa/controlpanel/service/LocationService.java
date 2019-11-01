package com.netcracker.alexa.controlpanel.service;

import com.netcracker.alexa.controlpanel.model.db.entity.userpage.Location;
import com.netcracker.alexa.controlpanel.model.db.entity.userpage.User;
import com.netcracker.alexa.controlpanel.repository.LocationRepository;
import com.netcracker.alexa.controlpanel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean setLocation(String nameLocation, String login){
        Location location = locationRepository.getFirstByLocationName(nameLocation);
        if(location != null) {
            User user = userRepository.findByLogin(login);
            if (user != null) {
                user.setLocation(location);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }
}
