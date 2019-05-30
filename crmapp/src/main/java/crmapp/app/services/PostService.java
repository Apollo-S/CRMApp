package crmapp.app.services;

import crmapp.app.entities.Post;
import crmapp.app.repositories.PostRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PostService extends BaseServiceImpl<Post, PostRepository> {
}
