package crmapp.app.controllers;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.entities.MailInput;
import crmapp.app.services.MailInputService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/mail-inputs")
public class MailInputController extends BaseController<MailInput, MailInputService> {

    @GetMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<List<MailInput>> getAllMailInputs() {
        return super.getAllEntities();
    }

    @GetMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<MailInput> getMailInputById(@PathVariable(PARAM_ID) int id) {
        return super.getEntityBy(id);
    }

    @PostMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<MailInput> addMailInput(@RequestBody MailInput input) {
        return super.addEntity(input);
    }

    @PutMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> updateMailInput(@PathVariable(PARAM_ID) int id,
                                                @RequestBody MailInput input) {
        return super.updateEntity(id, input);
    }

    @DeleteMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> deleteMailInput(@PathVariable(PARAM_ID) int id) {
        return super.deleteEntityById(id);
    }

}
