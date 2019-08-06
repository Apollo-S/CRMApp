package crmapp.app.controllers.experimental;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.entities.experimental.ContractorAddress;
import crmapp.app.services.experimental.ContractorAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/contractors")
public class AddressController extends BaseController<ContractorAddress, ContractorAddressService> {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

//    @GetMapping(value = "/{contractorType}/{contractorId}/addresses", headers = HEADER_JSON)
//    public ResponseEntity<List<Address>> getAddressesByContractorId(
//            @PathVariable("contractorId") Integer contractorId,
//            @PathVariable("contractorType") String contractorType) {
//        List<Address> addresses = super.service.findAllByContractorId(contractorId);
//        if (addresses.size() == 0) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(addresses, HttpStatus.OK);
//    }

}
