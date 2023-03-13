package org.winson.blog.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.winson.blog.BlogUser;

public interface BlogUserRepo extends JpaRepository<BlogUser, Long> {
    
}
