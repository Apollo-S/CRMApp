package crmapp.app.entities.dto;

import crmapp.app.entities.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeePostDTO extends BaseModelDTO {

    private boolean isActive;
    private Post post;
    private Date dateStart;

}
