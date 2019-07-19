package crmapp.app.entities.dto;

import crmapp.app.entities.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeDTO extends BaseModelDTO {

    private Date firedDate;
    private Date hireDate;
    private boolean isEntrepreneur;
    private Person person;

}
