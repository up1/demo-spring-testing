package com.example.demo2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public UserResponse getUserById(@PathVariable String id) {
        // Call service
        UserDomain userDomain
                = userService.getData(Integer.parseInt(id));

        // Convert to response
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userDomain.getId());
        userResponse.setName(userDomain.getName());
        return userResponse;
    }

}
