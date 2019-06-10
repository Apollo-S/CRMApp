package crmapp.app.controllers;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.entities.Bank;
import crmapp.app.services.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/banks")
public class BankController extends BaseController<Bank, BankService> {

    @GetMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<List<Bank>> getAllBanks() {
        return super.getAllEntities();
    }

    @GetMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Bank> getBankById(@PathVariable(PARAM_ID) int id) {
        return super.getEntityBy(id);
    }

    @PostMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<Bank> addBank(@RequestBody Bank bank) {
        return super.addEntity(bank);
    }

    @PutMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Bank> updateBank(@PathVariable(PARAM_ID) int id, @RequestBody Bank bank) {
        return super.updateEntity(id, bank);
    }

    @DeleteMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> deleteBank(@PathVariable(PARAM_ID) int id) {
        return super.deleteEntityById(id);
    }


}
