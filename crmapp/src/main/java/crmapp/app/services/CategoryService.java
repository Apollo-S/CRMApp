package crmapp.app.services;

import crmapp.app.entities.Category;
import crmapp.app.repositories.CategoryRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseServiceImpl<Category, CategoryRepository> {
}
