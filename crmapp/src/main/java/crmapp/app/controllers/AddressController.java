package crmapp.app.controllers;

import java.util.List;

import crmapp.app.entities.Address;
import crmapp.app.entities.Owner;
import crmapp.app.services.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class AddressController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // getAllAddresses
    @GetMapping(value = "/clients/{clientId}/addresses", headers = HEADER_JSON)
    public ResponseEntity<List<Address>> getAllAddressesByClientId(
            @PathVariable("clientId") Integer clientId) {
        return getAllAddressesByOwnerId(clientId, Owner.CLIENT);
    }

    @GetMapping(value = "/employees/{employeeId}/addresses", headers = HEADER_JSON)
    public ResponseEntity<List<Address>> getAllAddressesByEmployeeId(
            @PathVariable("employeeId") Integer employeeId) {
        return getAllAddressesByOwnerId(employeeId, Owner.EMPLOYEE);
    }

    @GetMapping(value = "/our-companies/{companyId}/addresses", headers = HEADER_JSON)
    public ResponseEntity<List<Address>> getAllAddressesByCompanyId(
            @PathVariable("companyId") Integer companyId) {
        return getAllAddressesByOwnerId(companyId, Owner.OUR_COMPANY);
    }

    private ResponseEntity<List<Address>> getAllAddressesByOwnerId(Integer id, Owner owner) {
        logger.info(LOG_ENTER_METHOD + "getAllAddressesByOwnerId(" + owner.toString().toLowerCase() + ")" + LOG_CLOSE);
        List<Address> addresses = addressService.findAllByOwnerId(id, owner);
        if (addresses.size() == 0) {
            logger.info(LOG_ERROR + "Addresses for " + owner.toString().toLowerCase() + " were not found" + LOG_CLOSE);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info(LOG_TEXT + "Count of Addresses: " + addresses.size() + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "getAllAddressesByOwnerId(" + owner.toString().toLowerCase() + ")" + LOG_CLOSE);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // getAddressById
    @GetMapping(value = "/clients/{clientId}/addresses/{id}", headers = HEADER_JSON)
    public ResponseEntity<Address> getClientAddressById(@PathVariable(PARAM_ID) int id) {
        return getAddressById(id);
    }

    @GetMapping(value = "/employees/{employeeId}/addresses/{id}", headers = HEADER_JSON)
    public ResponseEntity<Address> getEmployeeAddressById(@PathVariable(PARAM_ID) int id) {
        return getAddressById(id);
    }

    @GetMapping(value = "/our-companies/{companyId}/addresses/{id}", headers = HEADER_JSON)
    public ResponseEntity<Address> getOurCompanyAddressById(@PathVariable(PARAM_ID) int id) {
        return getAddressById(id);
    }

    private ResponseEntity<Address> getAddressById(Integer id) {
        logger.info(LOG_ENTER_METHOD + "getAddressById()" + LOG_CLOSE);
        Address address = addressService.findById(id);
        if (address == null) {
            logger.info(LOG_ERROR + "Address with ID=" + id + "wasn't found" + LOG_CLOSE);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info(LOG_OUT_OF_METHOD + "getAddressById()" + LOG_CLOSE);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    // postAddress
    @PostMapping(value = "/clients/{clientId}/addresses", headers = HEADER_JSON)
    public ResponseEntity<Address> addClientAddress(
            @PathVariable("clientId") int clientId, @RequestBody Address address) {
        return postAddress(clientId, address, Owner.CLIENT);
    }

    @PostMapping(value = "/employees/{employeeId}/addresses", headers = HEADER_JSON)
    public ResponseEntity<Address> addEmployeeAddress(
            @PathVariable("employeeId") int employeeId, @RequestBody Address address) {
        return postAddress(employeeId, address, Owner.EMPLOYEE);
    }

    @PostMapping(value = "/our-companies/{companyId}/addresses", headers = HEADER_JSON)
    public ResponseEntity<Address> addCompanyAddress(
            @PathVariable("companyId") int companyId, @RequestBody Address address) {
        return postAddress(companyId, address, Owner.OUR_COMPANY);
    }

    private ResponseEntity<Address> postAddress(Integer id, Address address, Owner owner) {
        logger.info(LOG_ENTER_METHOD + "postAddress(" + owner.toString().toLowerCase() + ")" + LOG_CLOSE);
        Address savedAddress = addressService.save(id, address, owner);
        logger.info(LOG_TEXT + "Address added with ID=" + savedAddress.getId() + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "postAddress(" + owner.toString().toLowerCase() + ")" + LOG_CLOSE);
        return new ResponseEntity<>(savedAddress, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping(value = "/clients/{clientId}/addresses/{id}", headers = HEADER_JSON)
    public ResponseEntity<Address> updateClientAddress(
            @PathVariable("clientId") int clientId, @RequestBody Address address) {
        return putAddress(clientId, address, Owner.CLIENT);
    }

    private ResponseEntity<Address> putAddress(Integer id, Address address, Owner owner) {
        logger.info(LOG_ENTER_METHOD + "putAddress()" + LOG_CLOSE);
        Address updatedAddress = addressService.update(id, address, owner);
        logger.info(LOG_TEXT + "Address was updated: " + updatedAddress + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "putAddress()" + LOG_CLOSE);
        return new ResponseEntity<>(updatedAddress, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/clients/{clientId}/addresses/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> deleteAddress(@PathVariable(PARAM_ID) int id) {
        logger.info(LOG_ENTER_METHOD + "deleteAddress()" + LOG_CLOSE);
        addressService.delete(id);
        logger.info(LOG_TEXT + "ClientAccount with ID=" + id + " was deleted" + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "deleteAddress()" + LOG_CLOSE);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
    }

}
