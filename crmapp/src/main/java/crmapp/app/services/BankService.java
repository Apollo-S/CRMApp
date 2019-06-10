package crmapp.app.services;

import crmapp.app.entities.Bank;
import crmapp.app.repositories.BankRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BankService extends BaseServiceImpl<Bank, BankRepository> {
}
