package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crmapp.app.entities.base.AbstractAccount;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Tables.SUPPLIER_ACCOUNTS)
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
public class SupplierAccount extends AbstractAccount {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Tables.SUPPLIER_ID)
    @JsonBackReference(value = Tables.SUPPLIER_ACCOUNTS)
    private Supplier supplier;

    public SupplierAccount() {
    }

    public SupplierAccount(String number, Bank bank, Date dateStart, Supplier supplier) {
        super(number, bank, dateStart);
        this.supplier = supplier;
    }

    public SupplierAccount(Supplier supplier) {
        super();
        this.supplier = supplier;
    }

    @Override
    public String getUrl() {
        return supplier.getUrl() + "/accounts/" + this.getId();
    }

    @Override
    public String toString() {
        return new StringBuilder("SupplierAccount [")
                .append(super.toString()).append(", ")
                .append("supplier=" + supplier.getCode()).append("]")
                .toString();
    }

}
