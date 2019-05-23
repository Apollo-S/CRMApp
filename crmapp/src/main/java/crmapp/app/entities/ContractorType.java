package crmapp.app.entities;

import crmapp.app.entities.base.TypeBaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = Tables.CONTRACTOR_TYPES)
@Getter
@Setter
public class ContractorType extends TypeBaseEntity {

    public final static String CLIENT = "clients";
    public final static String SUPPLIER = "suppliers";

    @Override
    public String getUrl() {
        return Tables.CONTRACTOR_TYPES + "/" + this.getId();
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("ContractorType [")
                .append(super.toString())
                .append("]").toString();
    }
}
