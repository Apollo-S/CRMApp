package crmapp.app.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import crmapp.app.entities.experimental.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeAddressDTO extends BaseModelDTO {

    @JsonProperty(value = "address")
    private Address employeeAddress;
    private Date dateStart;

}
