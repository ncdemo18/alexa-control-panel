package com.netcracker.alexa.controlpanel.service;

import com.netcracker.alexa.controlpanel.exception.InvalidLocationDateException;
import org.springframework.stereotype.Service;

@Service
public class NameLocationResolverService {
    private static final String FIRST_PAGE_NAME = "1 Lock Screen.png";
    private static final String SECOND_PAGE_NAME = "2 Lock Screen.png";
    private static final String THIRD_PAGE_NAME = "3 Media Center.png";
    private static final String FOURTH_PAGE_NAME = "5 Match page.png";

    public String resolveName(int numberPage) {
        switch (numberPage) {
            case 1 : return FIRST_PAGE_NAME;
            case 2 : return SECOND_PAGE_NAME;
            case 3 : return THIRD_PAGE_NAME;
            case 4 : return FOURTH_PAGE_NAME;
            default: throw new InvalidLocationDateException("Incorrect file name");
        }
    }
}
