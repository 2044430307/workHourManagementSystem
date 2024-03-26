package com.zhanhong.server.controller;

import com.zhanhong.server.service.WorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class testController {
    @Autowired
    private WorkTimeService workTimeService;

    @RequestMapping("/01")
    public String testServer(HttpServletRequest request){

        return null;
    }
}
