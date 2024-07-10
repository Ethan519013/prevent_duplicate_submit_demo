package com.ethan.controller;

import com.ethan.annotation.PreventDuplicateSubmit;
import com.ethan.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Johnson
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @PreventDuplicateSubmit
    @GetMapping("/submit")
    @ResponseBody
    public String submit(@RequestParam("id") String id) {
        return "ok";
    }

    @PreventDuplicateSubmit(interval = 5)
    @PostMapping("/submit2")
    @ResponseBody
    public User submit(@RequestBody() User user) {
        return user;
    }

}
