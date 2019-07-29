package crmapp.app.entities.dto;

import crmapp.app.entities.experimental.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeAddressDTO extends BaseModelDTO {

    private Address address;
    private Date dateStart;

}
