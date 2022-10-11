package com.example.demospringboot.controllers;


import com.example.demospringboot.jason.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloRestControllerFunctionalTest {

    @Autowired
    private TestRestTemplate template;
    @Test
    public void greetWithName() {
        Greeting response = template.getForObject("/rest?name=Dolly", Greeting.class);
        assertEquals("hello,Dolly!", response.getMessage());
    }

    @Test
    public void greetWithoutName() {
        ResponseEntity<Greeting> entity = template.getForEntity("/rest", Greeting.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, entity.getHeaders().getContentType());
        Greeting response = entity.getBody();
        if (response != null) {
            assertEquals("hello,World!", response.getMessage());
        }
    }
}
