package crmapp.app.services;

import crmapp.app.entities.EmployeePost;
import crmapp.app.repositories.EmployeePostRepository;
import crmapp.app.services.base.ExtendedBaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeePostService extends ExtendedBaseServiceImpl<EmployeePost, EmployeePostRepository> {
}
