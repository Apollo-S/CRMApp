package crmapp.app.controllers;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.entities.OurCompany;
import crmapp.app.services.OurCompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/our-companies")
public class OurCompanyController extends BaseController<OurCompany, OurCompanyService> {

    @GetMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<List<OurCompany>> getAllOurCompanies() {
        return super.getAllEntities();
    }

    @GetMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<OurCompany> getOurCompanyById(@PathVariable(PARAM_ID) int id) {
        return super.getEntityBy(id);
    }

    @PostMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<OurCompany> addOurCompany(@RequestBody OurCompany company) {
        return super.addEntity(company);
    }

    @PutMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> updateOurCompany(@PathVariable(PARAM_ID) int id,
                                                 @RequestBody OurCompany company) {
        return super.updateEntity(id, company);
    }

    @DeleteMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> deleteOurCompany(@PathVariable(PARAM_ID) int id) {
        return super.deleteEntityById(id);
    }

}
