package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static crmapp.app.entities.Tables.SUPPLIER_AGREEMENTS;
import static crmapp.app.entities.Tables.SUPPLIER_ID;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = SUPPLIER_AGREEMENTS)
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
public class SupplierAgreement extends AbstractAgreement {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = SUPPLIER_ID)
    @JsonBackReference(value = SUPPLIER_AGREEMENTS)
    private Supplier supplier;

    public SupplierAgreement() {
    }

    @Override
    public String getUrl() {
        return this.supplier.getUrl() + "/agreements/" + this.getId();
    }

    @JsonInclude
    public Supplier getSupplierInfo() {
        return this.supplier;
    }

    @JsonInclude
    public String getSupplierCode() {
        return this.supplier.getCode();
    }

    @JsonInclude
    public String getSupplierUrl() {
        return this.supplier.getUrl();
    }

    @Override
    public String toString() {
        return new StringBuilder("SupplierAgreement [")
                .append("supplier=" + supplier.getCode()).append(", ")
                .append(super.toString()).append("]")
                .toString();
    }

}
