package crmapp.app.services;

import crmapp.app.entities.Post;
import crmapp.app.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService extends AbstractService<Post, PostRepository> {
}
