package com.uninor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/error")
public class ErrorController {

    @RequestMapping(value = "/error-page")
    public String errorPage() {
        return "error-pages/common-error-page";
    }

    @RequestMapping(value = "/error-404")
    public String error404Page() {
        return "error-pages/404";
    }

}
