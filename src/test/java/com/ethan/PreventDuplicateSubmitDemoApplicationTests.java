package com.ethan;

import com.ethan.domain.User;
import com.ethan.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PreventDuplicateSubmitDemoApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        User user = new User();
        user.setName("Ethan");
        user.setAge(18);
        userService.insert(user);
    }

}
