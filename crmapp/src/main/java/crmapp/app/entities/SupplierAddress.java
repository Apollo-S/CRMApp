package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Tables.SUPPLIER_ADDRESSES)
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true,
        value = {"hibernateLazyInitializer", "handler"})
public class SupplierAddress extends AbstractAddress {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Tables.SUPPLIER_ID)
    @JsonBackReference(value = Tables.SUPPLIER_ADDRESSES)
    private Supplier supplier;

    public SupplierAddress() {
    }

    public SupplierAddress(Supplier supplier, Date dateStart) {
        this.supplier = supplier;
        this.setDateStart(dateStart);
    }

    @JsonInclude
    public Supplier getSupplierInfo() {
        return supplier;
    }

    @Override
    public String getUrl() {
        return supplier.getUrl() + "/addresses/" + this.getId();
    }

    @Override
    public String toString() {
        return new StringBuilder("SupplierAddress [")
                .append(super.toString()).append(", ")
                .append("supplier=" + supplier).append("]")
                .toString();
    }

}
