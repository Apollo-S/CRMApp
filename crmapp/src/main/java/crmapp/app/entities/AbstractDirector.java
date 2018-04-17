package crmapp.app.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public abstract class AbstractDirector extends BaseEntity {

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	@Column(name = "full_name", length = 255)
	private String fullName;

	@Column(name = "short_name", length = 100)
	private String shortName;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_start")
	private Date dateStart;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.getFullName(), this.getShortName(), this.getDateStart());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractDirector that = (AbstractDirector) obj;
		return Objects.equals(this.getId(), that.getId()) && 
				Objects.equals(this.getFullName(), that.getFullName()) && 
				Objects.equals(this.getShortName(), that.getShortName()) && 
				Objects.equals(this.getDateStart(), that.getDateStart());
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append(super.toString()).append(", ")
			.append("post=" + post.getTitle()).append(", ")
			.append("fullName=" + fullName).append(", ")
			.append("shortName=" + shortName).append(", ")
			.append("dateStart=" + dateStart)
			.toString();
	}

}
