package crmapp.app.entities.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public abstract class BaseModelDTO implements Serializable {

    private Integer id;
    private String url;
    private Date created;
    private Date lastModified;
    private Integer version;

}
