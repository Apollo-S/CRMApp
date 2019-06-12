package crmapp.app.entities;

import crmapp.app.entities.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = Tables.CURRENCY_TYPES)
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyType extends BaseEntity {

    @Column(name = "curr_code", length = 3)
    private String currCode;

    @Column(name = "curr_name", length = 75)
    private String currName;

    @Column(name = "curr_short_name", length = 20)
    private String currShortName;

    @Override
    public String getUrl() {
        return "currency-types/" + this.getId();
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(super.toString()).append(", ")
                .append("currCode=" + currCode).append(", ")
                .append("currName=" + currName).append(", ")
                .append("currShortName=" + currShortName)
                .toString();
    }

}
