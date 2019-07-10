package crmapp.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO extends BaseModelDTO {

    private String title;
    private String code;
    private String edrpou;
    private String inn;
    private String vatCertificate;

}
