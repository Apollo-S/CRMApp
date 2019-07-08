package crmapp.app.entities.experimental;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crmapp.app.entities.Tables;
import crmapp.app.entities.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Tables.CONTRACTOR_ADDRESSES)
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ContractorAddress extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Tables.CONTRACTOR_ID)
    private Contractor contractor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Tables.ADDRESS_ID)
    private Address address;

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name = "date_start")
    private Date dateStart;

    @Override
    public String getUrl() {
        return contractor.getContractorType().getCode() + "/" + contractor.getId() + "/addresses/" + getId();
    }

}
