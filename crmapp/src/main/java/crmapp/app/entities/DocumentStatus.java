package crmapp.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = Tables.DOCUMENT_STATUSES)
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class DocumentStatus extends BaseEntity {

	@Column(name = "status")
	private String status;

	@Column(name = "background_color", length = 15)
	private String backgroundColor;

	@Column(name = "color", length = 15)
	private String color;

    @Column(name = "allow_payment_date")
    private boolean allowPaymentDate;

    @Column(name = "allow_passing_date")
    private boolean allowPassingDate;

	public DocumentStatus() {
	}

	public DocumentStatus(String status) {
		this.status = status;
	}

    @Override
	public String getUrl() {
		return "document-statuses/" + this.getId();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DocumentStatus [");
		builder.append(super.toString()).append(", ");
		builder.append("status=" + status).append(", ");
		builder.append("color=" + color).append(", ");
		builder.append("background-color=" + backgroundColor).append(", ");
		builder.append("url=" + this.getUrl()).append("]");
		return builder.toString();
	}

}
