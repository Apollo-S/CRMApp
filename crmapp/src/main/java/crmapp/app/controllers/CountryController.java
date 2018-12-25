package crmapp.app.controllers;

import crmapp.app.entities.Country;
import crmapp.app.services.CountryService;
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
@RequestMapping(value = "/api/countries")
public class CountryController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private CountryService countryService;

    @GetMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<List<Country>> getAllCountries() {
        logger.info(LOG_ENTER_METHOD + "getAllCountries()" + LOG_CLOSE);
        List<Country> countries = countryService.findAll();
        if (countries.size() == 0) {
            logger.info(LOG_ERROR + "Countries were not found" + LOG_CLOSE);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info(LOG_TEXT + "Count of Countries: " + countries.size() + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "getAllCountries()" + LOG_CLOSE);
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Country> getCountryById(@PathVariable int id) {
        logger.info(LOG_ENTER_METHOD + "getCountryById()" + LOG_CLOSE);
        Country country = countryService.findById(id);
        if (country == null) {
            logger.info(LOG_ERROR + "Country with ID=" + id + "wasn't found" + LOG_CLOSE);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info(LOG_TEXT + "Country with ID=" + id + " was found: " + country + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "getCountryById()" + LOG_CLOSE);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

}
