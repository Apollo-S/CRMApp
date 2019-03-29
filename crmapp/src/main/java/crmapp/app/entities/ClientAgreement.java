package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = Tables.CLIENT_AGREEMENTS)
@Getter
@Setter
public class ClientAgreement extends AbstractAgreement {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = Tables.CLIENT_ID)
    @JsonBackReference(value = Tables.CLIENT_AGREEMENTS)
    private Client client;

    public ClientAgreement() {
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("ClientAgreement [")
                .append("client=" + client.getCode()).append(", ")
                .append(super.toString()).append("]")
                .toString();
    }
}
