package com.example.demo2.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Test
    public void user_not_found_with_exception() {
        given(userRepository.findById(1))
                .willReturn(Optional.empty());
        UserService userService = new UserService();
        userService.setRepository(userRepository);

        Assertions.assertThrows(RuntimeException.class, () -> {
            userService.getData(1);
        });
    }

    @Test
    public void found_getData_by_id_1() {
        User user = new User();
        user.setId(1);
        user.setFname("Demo");
        user.setLname("xxx");
        given(userRepository.findById(1))
                .willReturn(Optional.of(user));

        UserService userService = new UserService();
        userService.setRepository(userRepository);
        UserDomain userDomain = userService.getData(1);

        assertEquals(1, userDomain.getId());
        assertEquals("Demo xxx", userDomain.getName());
    }


}

