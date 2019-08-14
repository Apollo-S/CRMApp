package crmapp.app.controllers;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.entities.MailDocumentType;
import crmapp.app.services.MailDocumentTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/mail-document-types")
public class MailDocumentTypeController extends BaseController<MailDocumentType, MailDocumentTypeService> {

    @GetMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<List<MailDocumentType>> getAllMailDocumentTypes() {
        return super.getAllEntities();
    }

    @GetMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<MailDocumentType> getMailDocumentTypeById(@PathVariable(PARAM_ID) int id) {
        return super.getEntityBy(id);
    }

}
