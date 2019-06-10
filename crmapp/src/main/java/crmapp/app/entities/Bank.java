package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crmapp.app.entities.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = Tables.BANKS)
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Bank extends BaseEntity {

    @Column(name = "title", length = 200)
    private String title;

    @Column(name = "mfo", length = 7)
    private String mfo;

    public Bank() {
    }

    public Bank(String title, String mfo) {
        this.title = title;
        this.mfo = mfo;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(super.toString()).append(", ")
                .append("title=" + title).append(", ")
                .append("mfo=" + mfo).append("]")
                .toString();
    }

}
