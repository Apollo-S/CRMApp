package crmapp.app.entities.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import crmapp.app.entities.Bank;
import crmapp.app.entities.CurrencyType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class AbstractAccount extends BaseEntity {

    @Column(name = "number", length = 255)
    private String number;

    @OneToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToOne
    @JoinColumn(name = "currency_type_id")
    private CurrencyType currencyType;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_start")
    private Date dateStart;

    public AbstractAccount() {
    }

    public AbstractAccount(String number, Bank bank, Date dateStart) {
        this.number = number;
        this.bank = bank;
        this.dateStart = dateStart;
    }

    @JsonInclude
    public String getPresentation() {
        StringBuilder accountBuilder = new StringBuilder();
        accountBuilder.append(getNumber()).append(" (").append(currencyType.getCurrShortName()).append("),");
        accountBuilder.append(bank.getTitle()).append(" (МФО ").append(bank.getMfo()).append(")");
        return accountBuilder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getPresentation(), this.getDateStart());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        AbstractAccount that = (AbstractAccount) obj;
        return Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.getPresentation(), that.getPresentation()) &&
                Objects.equals(this.getDateStart(), that.getDateStart());
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(super.toString()).append(", ")
                .append("number=" + number).append(", ")
                .append("bank=" + bank).append(", ")
                .append("dateStart=" + dateStart)
                .toString();
    }

}
