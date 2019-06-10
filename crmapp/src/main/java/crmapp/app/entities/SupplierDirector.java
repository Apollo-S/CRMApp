package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crmapp.app.entities.base.AbstractDirector;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

import static crmapp.app.entities.Tables.SUPPLIER_DIRECTORS;
import static crmapp.app.entities.Tables.SUPPLIER_ID;


@Entity
@Table(name = SUPPLIER_DIRECTORS)
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
public class SupplierDirector extends AbstractDirector {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = SUPPLIER_ID)
    @JsonBackReference(value = SUPPLIER_DIRECTORS)
    private Supplier supplier;

    public SupplierDirector() {
    }

    public SupplierDirector(Supplier supplier, Post post, String fullName, String shortName, Date dateStart) {
        this.supplier = supplier;
        this.setPost(post);
        this.setFullName(fullName);
        this.setShortName(shortName);
        this.setDateStart(dateStart);
    }

    @Override
    public String getUrl() {
        return this.supplier.getUrl() + "/directors/" + this.getId();
    }

    @Override
    public String toString() {
        return new StringBuilder("SupplierDirector [")
                .append(super.toString()).append(", ")
                .append("supplier=" + supplier.getTitle()).append("]")
                .toString();
    }

}
