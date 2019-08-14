package crmapp.app.controllers;

import crmapp.app.controllers.base.ExtendedBaseController;
import crmapp.app.entities.OurCompanyDirector;
import crmapp.app.services.OurCompanyDirectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/our-companies/{companyId}/directors")
public class OurCompanyDirectorController extends ExtendedBaseController<OurCompanyDirector, OurCompanyDirectorService> {

    @GetMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<List<OurCompanyDirector>> getAllOurCompanyDirectors(@PathVariable("companyId") Integer companyId) {
        return super.getAllFilterBy("ourCompany", companyId);
    }

    @GetMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<OurCompanyDirector> getOurCompanyDirectorById(@PathVariable(PARAM_ID) int id) {
        return super.getEntityBy(id);
    }

    @PostMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<OurCompanyDirector> addOurCompanyDirector(@PathVariable("companyId") int companyId,
                                                                    @RequestBody OurCompanyDirector director) {
        return super.addEntity(director);
    }

    @PutMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> updateOurCompanyDirector(@PathVariable("companyId") int companyId,
                                                         @RequestBody OurCompanyDirector director) {
        return super.updateEntity(director);
    }

    @DeleteMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> deleteOurCompanyDirector(@PathVariable(PARAM_ID) int id) {
        return super.deleteEntityById(id);
    }

}