package com.example.demo2.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostGatewayTest {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void getPostById() {
        PostResponse postResponse = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/1", PostResponse.class);
        assertEquals(1, postResponse.getId());
        assertEquals(1, postResponse.getUserId());
    }
}