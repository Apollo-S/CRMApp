package crmapp.app.controllers;

import crmapp.app.controllers.base.ExtendedBaseController;
import crmapp.app.entities.ClientAccount;
import crmapp.app.services.ClientAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/api/clients/{clientId}/accounts")
public class ClientAccountController extends ExtendedBaseController<ClientAccount, ClientAccountService> {

    @GetMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<List<ClientAccount>> getAllClientAccountsByClientId(
            @PathVariable("clientId") Integer clientId) {
        return super.getAllFilterBy("client", clientId);
    }

    @GetMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<ClientAccount> getClientAccountById(@PathVariable(PARAM_ID) int id) {
        return super.getEntityBy(id);
    }

    @PostMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<ClientAccount> addClientAccount(@PathVariable("clientId") int clientId,
                                                          @RequestBody ClientAccount account) {
        return super.addEntity(account);
    }

    @PutMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<ClientAccount> updateClientAccount(@PathVariable(PARAM_ID) int clientId,
                                                             @NotNull @RequestBody ClientAccount account) {
        return super.updateEntity(account);
    }

    @DeleteMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> deleteClientAccount(@PathVariable(PARAM_ID) int id) {
        return super.deleteEntityById(id);
    }

}
