package com.ktw.duan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/provider")
public class ProviderController {

    @RequestMapping("/test")
    public String test(){
        return null;
    }
}
