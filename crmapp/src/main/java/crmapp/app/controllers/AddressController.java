package crmapp.app.controllers;

import crmapp.app.entities.ContractorAddress;
import crmapp.app.services.ContractorAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/contractors")
public class AddressController extends BaseController<ContractorAddress, ContractorAddressService> {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @GetMapping(value = "/clients/{contractorId}/addresses", headers = HEADER_JSON)
    public ResponseEntity<List<ContractorAddress>> getAllAddressesByContractorId(
            @PathVariable("contractorId") Integer contractorId) {
        List<ContractorAddress> addresses = super.service.findAllByContractorId(contractorId);
        if (addresses.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

}
