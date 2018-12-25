package crmapp.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "country")
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
