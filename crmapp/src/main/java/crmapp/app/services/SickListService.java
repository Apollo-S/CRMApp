package crmapp.app.services;

import crmapp.app.entities.SickList;
import crmapp.app.repositories.SickListRepository;
import crmapp.app.services.base.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class SickListService extends AbstractService<SickList, SickListRepository> {
}
