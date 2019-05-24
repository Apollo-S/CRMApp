package crmapp.app.controllers;

import crmapp.app.entities.Address;
import crmapp.app.services.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/contractors")
public class AddressController extends BaseController<Address, AddressService> {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(value = "/clients/{contractorId}/addresses", headers = HEADER_JSON)
    public ResponseEntity<List<Address>> getAllAddressesByContractorId(
            @PathVariable("contractorId") Integer contractorId) {
        List<Address> addresses = addressService.findAllByContractorId(contractorId);
        if (addresses.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

}
