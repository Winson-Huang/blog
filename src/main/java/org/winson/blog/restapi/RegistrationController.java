package org.winson.blog.restapi;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.winson.blog.data.BlogUserRepo;
import org.winson.blog.domain.BlogUser;

@RestController
@RequestMapping(path = "/register")
@CrossOrigin(origins = "*")
public class RegistrationController {

    private BlogUserRepo blogUserRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController (
        BlogUserRepo userRepo, PasswordEncoder passwordEncoder
    ) {
        this.blogUserRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    
    @GetMapping
    public String registrationHint() {
        return "Use get request with parameter to register.";
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public BlogUserForm processRegistration(@RequestBody BlogUserForm userForm) {
        BlogUser user = new BlogUser(
            userForm.getUsername(), passwordEncoder.encode(userForm.getPassword()), 
            userForm.getFullname(), null
        );
        blogUserRepo.save(user);
        return userForm;
    }
    
}
