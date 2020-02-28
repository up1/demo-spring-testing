package com.example.demo2.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void found_user_1() {
        User demo = new User();
        demo.setFname("Demo");
        demo.setLname("name");
        userRepository.save(demo);

        Optional<User> user = userRepository.findById(1);
        assertTrue(user.isPresent());
        assertEquals(1, user.get().getId());
        assertEquals("Demo", user.get().getFname());
        assertEquals("name", user.get().getLname());
    }

}