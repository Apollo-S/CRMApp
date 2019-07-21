package crmapp.app.entities.dto;

import crmapp.app.entities.Employee;
import crmapp.app.entities.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeePostFullDTO extends BaseModelDTO {

    private Employee employee;
    private boolean isActive;
    private Post post;
    private Date dateStart;

}
