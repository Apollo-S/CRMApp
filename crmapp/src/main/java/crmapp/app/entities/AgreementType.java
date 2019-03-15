package crmapp.app.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "agreement_types")
@Getter
@Setter
@EqualsAndHashCode
public class AgreementType extends BaseEntity {

    @Column(name = "code", length = 20, nullable = false)
    private String code;

    @Column(name = "title", length = 30)
    private String title;

    @Override
    public String getUrl() {
        return "agreement-types/" + this.getId();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AgreementType [");
        builder.append(super.toString()).append(", ");
        builder.append("shortTitle=" + title).append(", ");
        builder.append("url=" + this.getUrl()).append("]");
        return builder.toString();
    }

}