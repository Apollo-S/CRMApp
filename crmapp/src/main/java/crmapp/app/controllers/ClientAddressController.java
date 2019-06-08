package crmapp.app.controllers;

import crmapp.app.controllers.base.ExtendedBaseController;
import crmapp.app.entities.ClientAddress;
import crmapp.app.services.ClientAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/clients/{clientId}/addresses")
public class ClientAddressController extends ExtendedBaseController<ClientAddress, ClientAddressService> {

    @GetMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<List<ClientAddress>> getAllClientAddressesByClientId(
            @PathVariable("clientId") Integer clientId) {
        return super.getAllFilterBy("client", clientId);
    }

    @GetMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<ClientAddress> getClientAddressById(@PathVariable(PARAM_ID) int id) {
        return super.getEntityBy(id);
    }

    @PostMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<ClientAddress> addClientAddress(@PathVariable("clientId") int clientId,
                                                          @RequestBody ClientAddress address) {
        return super.addEntity(address);
    }

    @PutMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<ClientAddress> updateClientAddress(@PathVariable("clientId") int clientId,
                                                             @RequestBody ClientAddress address) {
        return super.updateEntity(address);
    }

    @DeleteMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> deleteClientAddress(@PathVariable(PARAM_ID) int id) {
        return super.deleteEntityById(id);
    }

}
