package com.example.demo2.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class PostGateway {

    private final RestTemplate restTemplate;
    private final String postApiUrl;

    @Autowired
    public PostGateway(final RestTemplate restTemplate,
                       @Value("${post.api.url}") final String postApiUrl) {
        this.restTemplate = restTemplate;
        this.postApiUrl = postApiUrl;
    }

    public Optional<PostResponse> getPostById(int id) {
        String url = String.format("%s/posts/%d", postApiUrl, id);

        try {
            return Optional.ofNullable(
                    restTemplate.getForObject(url, PostResponse.class));
        } catch (RestClientException e) {
            return Optional.empty();
        }
    }

}
