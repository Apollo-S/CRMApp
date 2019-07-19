package crmapp.app.controllers;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.entities.ClientAgreement;
import crmapp.app.services.ClientAgreementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ClientAgreementController extends BaseController<ClientAgreement, ClientAgreementService> {

    private static final Logger logger = LoggerFactory.getLogger(ClientAgreementController.class);

    @GetMapping(value = "/agreements", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientAgreement>> getAllClientAgreements() {
        return super.getAllEntities();
    }

    @GetMapping(value = "/clients/{clientId}/agreements", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientAgreement>> getAllClientAgreementsByClientId(@PathVariable("clientId") int clientId) {
        logger.info(LOG_ENTER_METHOD + "getAllClientAgreementsByClientId()" + LOG_CLOSE);
        List<ClientAgreement> agreements = super.service.findAllByClientId(clientId);
        if (agreements.size() == 0) {
            logger.info(LOG_ERROR + "ClientAgreements by clientId were not found" + LOG_CLOSE);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info(LOG_TEXT + "Count of ClientAgreements by clientId: " + agreements.size() + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "getAllClientAgreementsByClientId()" + LOG_CLOSE);
        return new ResponseEntity<>(agreements, HttpStatus.OK);
    }

    @GetMapping(value = {"/agreements/{id}", "/clients/{clientId}/agreements/{id}"}, headers = HEADER_JSON)
    public ResponseEntity<ClientAgreement> getClientAgreementById(@PathVariable(PARAM_ID) int id) {
        logger.info(LOG_ENTER_METHOD + "getClientAgreementById()" + LOG_CLOSE);
        ResponseEntity<ClientAgreement> agreement = super.getEntityBy(id);
        logger.info(LOG_OUT_OF_METHOD + "getClientAgreementById()" + LOG_CLOSE);
        return agreement;
    }

    @PostMapping(value = {"/agreements", "/clients/{clientId}/agreements"}, headers = HEADER_JSON)
    public ResponseEntity<ClientAgreement> addClientAgreement(@RequestBody ClientAgreement agreement) {
        return super.addEntity(agreement);
    }

    @PutMapping(value = {"/agreements/{id}", "/clients/{clientId}/agreements"}, headers = HEADER_JSON)
    public ResponseEntity<Void> updateClientAgreement(@PathVariable(PARAM_ID) int id,
                                                                 @RequestBody ClientAgreement agreement) {
        logger.info(LOG_ENTER_METHOD + "updateClientAgreement()" + LOG_CLOSE);
        super.service.update(id, agreement);
        logger.info(LOG_TEXT + "ClientAgreement with ID=" + id + " was updated: " + agreement + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "updateClientAgreement()" + LOG_CLOSE);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/agreements/{id}", "/clients/{clientId}/agreements/{id}"}, headers = HEADER_JSON)
    public ResponseEntity<Void> deleteClientAgreement(@PathVariable(PARAM_ID) int id) {
        return super.deleteEntityById(id);
    }

}