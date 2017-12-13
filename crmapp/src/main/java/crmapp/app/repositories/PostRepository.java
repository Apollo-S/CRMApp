package crmapp.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import crmapp.app.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
