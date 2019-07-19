package crmapp.app.controllers;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.entities.dto.ClientDTO;
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
    public ResponseEntity<ClientDTO> addClient(@RequestBody ClientDTO client) {
        return super.addEntity(client);
    }

    @PutMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> updateEntity(@PathVariable(PARAM_ID) int id, @RequestBody ClientDTO client) {
        return super.updateEntity(client);
    }

    @DeleteMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> deleteClient(@PathVariable(PARAM_ID) int id) {
        return super.deleteEntityById(id);
    }

}
