package crmapp.app.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "countries")
@Getter
@Setter
public class Country extends BaseEntity {

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "code", length = 3)
    private String code;

    public Country() {
    }

    public Country(String title, String code) {
        this.title = title;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(getTitle(), country.getTitle()) &&
                Objects.equals(getCode(), country.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getCode());
    }

    @Override
    public String toString() {
        return super.toString();
        // TODO: finish toString()
    }

    @Override
    public String getUrl() {
        return new StringBuilder()
                .append("countries/")
                .append(getId())
                .toString();
    }

}
