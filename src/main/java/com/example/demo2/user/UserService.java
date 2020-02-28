package com.example.demo2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDomain getData(int id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            throw new RuntimeException(String.format("User id=%d not found", id));
        }

        UserDomain userDomain = new UserDomain();
        userDomain.setId(id);
        String name = String.format(
                "%s %s", user.get().getFname(), user.get().getLname());
        userDomain.setName(name);
        return userDomain;
    }

    public void setRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
