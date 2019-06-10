package crmapp.app.services;

import crmapp.app.entities.CurrencyType;
import crmapp.app.repositories.CurrencyTypeRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CurrencyTypeService extends BaseServiceImpl<CurrencyType, CurrencyTypeRepository> {
}
