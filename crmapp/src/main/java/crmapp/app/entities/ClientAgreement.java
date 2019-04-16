package crmapp.app.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.HashMap;
import java.util.Map;

import static crmapp.app.entities.Tables.CLIENT_AGREEMENTS;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = CLIENT_AGREEMENTS)
@Getter
@Setter
public class ClientAgreement extends AbstractAgreement {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = Tables.CLIENT_ID)
    @JsonBackReference(value = CLIENT_AGREEMENTS)
    private Client client;

    public ClientAgreement() {
    }

    @Override
    public String getUrl() {
        return "agreements/" + getId();
    }

    @JsonInclude
    public Client getClientInfo() {
        return this.client;
    }

    @JsonInclude
    public String getClientCode() {
        return this.client.getCode();
    }

    @JsonInclude
    public String getClientUrl() {
        return this.client.getUrl();
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
