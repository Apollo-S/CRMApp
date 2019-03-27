package crmapp.app.entities;

import crmapp.app.entities.base.TypeBaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "contractor_types")
@Getter
@Setter
public class ContractorType extends TypeBaseEntity {

    public final static String CLIENT = "client";
    public final static String SUPPLIER = "supplier";

    @Override
    public String getUrl() {
        return "contractor-types/" + this.getId();
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("ContractorType [")
                .append(super.toString())
                .append("]").toString();
    }
}
