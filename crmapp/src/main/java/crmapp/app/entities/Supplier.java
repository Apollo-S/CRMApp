package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import crmapp.app.entities.base.AbstractCompany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Tables.SUPPLIERS)
@Getter
@Setter
@ToString()
@JsonIgnoreProperties(ignoreUnknown = true,
        value = { "hibernateLazyInitializer", "handler",
                "agreements", "addresses", "directors", "accounts" })
public class Supplier extends AbstractCompany {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier", orphanRemoval = true)
    @OrderBy("id ASC")
    @JsonManagedReference(value = Tables.SUPPLIER_AGREEMENTS)
    private Set<SupplierAgreement> agreements = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier", orphanRemoval = true)
    @OrderBy("id ASC")
    @JsonManagedReference(value = Tables.SUPPLIER_ADDRESSES)
    private Set<SupplierAddress> addresses = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier", orphanRemoval = true)
    @OrderBy("id ASC")
    @JsonManagedReference(value = Tables.SUPPLIER_DIRECTORS)
    private Set<SupplierDirector> directors = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier", orphanRemoval = true)
    @OrderBy("id ASC")
    @JsonManagedReference(value = Tables.SUPPLIER_ACCOUNTS)
    private Set<SupplierAccount> accounts = new HashSet<>();

    public Supplier() {
    }



}
