package org.winson.blog.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.winson.blog.BlogUser;
import org.winson.blog.data.BlogUserRepo;

@Service
public class BlogUserUserDetailsService implements UserDetailsService {

    private BlogUserRepo blogUserRepo;

    public BlogUserUserDetailsService(BlogUserRepo blogUserRepo) {
        this.blogUserRepo = blogUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BlogUser user = blogUserRepo.getByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException(
                "User: " + username + " not found."
            );
        }
    }
}
