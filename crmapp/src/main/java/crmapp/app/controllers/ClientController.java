package crmapp.app.controllers;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.dto.ClientDTO;
import crmapp.app.entities.Client;
import crmapp.app.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/clients")
public class ClientController extends BaseController<Client, ClientService> {

    @GetMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return super.getAllEntities(ClientDTO.class);
    }

    @GetMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<ClientDTO> getClientById(@PathVariable(PARAM_ID) int id) {
        return super.getEntityBy(id, ClientDTO.class);
    }

    @PostMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        return super.addEntity(client);
    }

    @PutMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Client> updateEntity(@PathVariable(PARAM_ID) int id, @RequestBody Client client) {
        return super.updateEntity(id, client);
    }

    @DeleteMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> deleteClient(@PathVariable(PARAM_ID) int id) {
        return super.deleteEntityById(id);
    }

}
