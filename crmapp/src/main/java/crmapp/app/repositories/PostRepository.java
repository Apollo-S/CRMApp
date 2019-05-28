package crmapp.app.repositories;

import crmapp.app.entities.Post;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends BaseRepository<Post, Integer> {

}
