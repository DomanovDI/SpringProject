package com.demo.firstProject.repo;

import com.demo.firstProject.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post,Long> {
}
