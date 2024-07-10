package com.ethan.controller;

import com.ethan.annotation.PreventDuplicateSubmit;
import com.ethan.domain.User;
import com.ethan.service.UserService;
import com.ethan.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Johnson
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @PreventDuplicateSubmit
    @GetMapping("/submit")
    public Result submit(@RequestParam("id") String id) {
        return new Result("ok");
    }

    @PreventDuplicateSubmit(interval = 5)
    @PostMapping("/submit2")
    public Result submit(@RequestBody() User user) {
        return new Result(user);
    }

    @PreventDuplicateSubmit(interval = 5)
    @PostMapping("/insert")
    public Result insert(@RequestBody() User user) {
        userService.insert(user);
        return new Result();
    }



}
