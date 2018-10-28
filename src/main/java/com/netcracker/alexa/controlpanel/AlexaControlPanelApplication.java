package com.netcracker.alexa.controlpanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlexaControlPanelApplication {
    private static final Logger logger = LoggerFactory.getLogger(AlexaControlPanelApplication.class);

    public static void main(String[] args) {
        logger.info("start main");
        SpringApplication.run(AlexaControlPanelApplication.class, args);
        logger.info("Spring context was initialized");
    }
}
