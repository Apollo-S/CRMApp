package crmapp.app.controllers;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.entities.CurrencyType;
import crmapp.app.services.CurrencyTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/currency-types")
public class CurrencyTypeController extends BaseController<CurrencyType, CurrencyTypeService> {

    @GetMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<List<CurrencyType>> getAllCurrencyTypes() {
        return super.getAllEntities();
    }

    @GetMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<CurrencyType> getCurrencyTypeById(@PathVariable(PARAM_ID) int id) {
        return super.getEntityBy(id);
    }

    @PostMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<CurrencyType> addCurrencyType(@RequestBody CurrencyType currencyType) {
        return super.addEntity(currencyType);
    }

    @PutMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> updateCurrencyType(@PathVariable(PARAM_ID) int id, @RequestBody CurrencyType currencyType) {
        return super.updateEntity(id, currencyType);
    }

    @DeleteMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> deleteCurrencyType(@PathVariable(PARAM_ID) int id) {
        return super.deleteEntityById(id);
    }
    
}
