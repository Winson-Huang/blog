package org.winson.blog.restapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class BlogUserForm {
    
    private final String username;

    private final String password;

    private final String fullname;

}
