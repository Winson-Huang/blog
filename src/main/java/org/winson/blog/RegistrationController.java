package org.winson.blog;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.winson.blog.data.BlogUserRepo;

@RestController
@RequestMapping(path = "/register")
public class RegistrationController {

    private BlogUserRepo blogUserRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController (
        BlogUserRepo userRepo, PasswordEncoder passwordEncoder
    ) {
        this.blogUserRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    

    @PostMapping
    public BlogUser processRegistration(BlogUser user) {
        return blogUserRepo.save(user);
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
        BlogUser user = new BlogUser(username, passwordEncoder.encode(password), fullname, null);

        RestTemplate rest = new RestTemplate();
        rest.postForObject(
            "http://localhost:8080/register", 
            user,
            BlogUser.class
        );
        return username + "successfully created.";
    }
    
}
