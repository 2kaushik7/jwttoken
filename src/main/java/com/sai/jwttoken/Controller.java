package com.sai.jwttoken;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class Controller {

    @GetMapping("/get")
    public String getJwtTokeb(){
        return "jwt token";
    }
}
