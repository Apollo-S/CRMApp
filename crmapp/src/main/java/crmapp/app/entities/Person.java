package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crmapp.app.entities.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Tables.PERSONS)
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(
        ignoreUnknown = true,
        value = {"hibernateLazyInitializer", "handler",
                "surname", "firstname", "lastname"})
public class Person extends BaseEntity {

    @Column(name = "surname", length = 50)
    private String surname;

    @Column(name = "firstname", length = 50)
    private String firstname;

    @Column(name = "lastname", length = 50)
    private String lastname;

    @Column(name = "inn", length = 20)
    private String inn;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    public Person() {
    }

    public String getFullName() {
        StringBuilder builder = new StringBuilder();
        if (getSurname() != null && !getSurname().isEmpty()) builder.append(getSurname());
        if (getFirstname() != null && !getFirstname().isEmpty()) builder.append(" " + getFirstname());
        if (getLastname() != null && !getLastname().isEmpty()) builder.append(" " + getLastname());
        return builder.toString();
    }

    public String getShortName() {
        StringBuilder builder = new StringBuilder();
        if (getSurname() != null && !getSurname().isEmpty()) builder.append(getSurname());
        if (getFirstname() != null && !getFirstname().isEmpty()) builder.append(" " +
                getFirstname().substring(0, 1).toUpperCase() + ".");
        if (getLastname() != null && !getLastname().isEmpty()) builder.append(
                getLastname().substring(0, 1).toUpperCase() + ".");
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Person [");
        builder.append(super.toString()).append(", ");
        builder.append("surname=" + surname).append(", ");
        builder.append("firstname=" + firstname).append(", ");
        builder.append("lastname=" + lastname).append(", ");
        builder.append("shortName=" + getShortName()).append(", ");
        builder.append("inn=" + inn).append(", ");
        builder.append("birthDate=" + birthDate).append("]");
        return builder.toString();
    }

}