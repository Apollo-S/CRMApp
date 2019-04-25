package crmapp.app.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractAgreement extends BaseEntity {

    @Column(name = "code")
    private String code = "";

    @Column(name = "number")
    private String number;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "comment")
    private String comment;

    public AbstractAgreement() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAgreement that = (AbstractAgreement) o;
        return Objects.equals(getCode(), that.getCode()) &&
                Objects.equals(getNumber(), that.getNumber()) &&
                Objects.equals(getDateStart(), that.getDateStart()) &&
                Objects.equals(getComment(), that.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getNumber(), getDateStart(), getComment());
    }

    @Override
    public String toString() {
        return "code='" + code + '\'' +
                ", number='" + number + '\'' +
                ", dateStart=" + dateStart +
                ", comment='" + comment + '}';
    }
}
