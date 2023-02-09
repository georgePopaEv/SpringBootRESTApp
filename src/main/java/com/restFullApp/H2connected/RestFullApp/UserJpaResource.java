package com.restFullApp.H2connected.RestFullApp;

import com.restFullApp.H2connected.RestFullApp.post.Post;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJpaResource extends JpaRepository<User, Long> {

    List<Post> findAllById(long id);

}
