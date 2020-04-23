package com.learning.BlogAGD.controller;

import com.learning.BlogAGD.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class MainController implements ErrorController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "main";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }


    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";


            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500";
            }

            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error-403";}
        }
        return "error-404";
    }
}

