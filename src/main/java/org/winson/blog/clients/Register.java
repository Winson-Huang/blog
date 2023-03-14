package org.winson.blog.clients;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping(path = "/clients/register")
public class Register {
    
    private static final String registerUrl = "http://localhost:8080/register";

    private RestTemplate restTemplate;
    
    public Register() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping
    public String getDataToRegister(
        @RequestParam String username,
        @RequestParam String password,
        @RequestParam String fullname
    ) {
        if (username == null || password == null || fullname == null) {
            return "Use get request with parameter to register.";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        JSONObject userjson = new JSONObject();
        userjson.put("username", username);
        userjson.put("password", password);
        userjson.put("fullname", fullname);

        HttpEntity<String> request = 
        new HttpEntity<String>(userjson.toString(), headers);

        // BlogUserForm form = new BlogUserForm(username, password, fullname);
        // HttpEntity<BlogUserForm> request = new HttpEntity<>(form);
        
        restTemplate.postForObject(
            registerUrl, 
            request, 
            String.class 
        );

        return username + " successfully created.";
    }
}
