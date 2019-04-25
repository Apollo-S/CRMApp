package crmapp.app.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class AbstractAccount extends BaseEntity {

    @Column(name = "number", length = 255)
    private String number;

    @Column(name = "bank_name", length = 200)
    private String bankName;

    @Column(name = "mfo", length = 7)
    private String mfo;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_start")
    private Date dateStart;

    public AbstractAccount() {
    }

    public AbstractAccount(String number, String bankName, String mfo, Date dateStart) {
        this.number = number;
        this.bankName = bankName;
        this.mfo = mfo;
        this.dateStart = dateStart;
    }

    @JsonInclude
    public String getPresentation() {
        StringBuilder accountBuilder = new StringBuilder();
        accountBuilder.append(getNumber()).append(", ");
        accountBuilder.append(getBankName()).append(" (МФО ").append(getMfo()).append(")");
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
                .append("bankName=" + bankName).append(", ")
                .append("mfo=" + mfo).append(", ")
                .append("dateStart=" + dateStart)
                .toString();
    }

}
