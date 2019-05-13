package crmapp.app.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class DocumentFilter {

    private List<Integer> docStatuses;
    private List<Integer> docTypes;
    private List<Integer> clients;
    private String sortType;
    private String sortField;
    private Date datedStart;
    private Date datedFinal;
    private Date paymentDateStart;
    private Date paymentDateFinal;
    private Date passingDateStart;
    private Date passingDateFinal;

}
