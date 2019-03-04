package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "agreements")
@Getter
@Setter
public class Agreement extends BaseEntity {

    @Column(name = "number")
    private String number;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference(value = "client-agreement")
    private Client client;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agreement", orphanRemoval = true)
    @OrderBy("id ASC")
    @JsonManagedReference(value = "agreement-document")
    private Set<Document> documents = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Agreement that = (Agreement) obj;
        return Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.getNumber(), that.getNumber()) &&
                Objects.equals(this.getDateStart(), that.getDateStart()) &&
                Objects.equals(this.getVersion(), that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getDateStart(), getComment(), getClient(), getDocuments());
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(super.toString()).append(", ")
                .append("number=" + number).append(", ")
                .append("dateStart=" + dateStart)
                .toString();
    }

}