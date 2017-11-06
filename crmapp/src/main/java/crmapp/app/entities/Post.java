package crmapp.app.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post extends UrlBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "title", length = 100)
	private String title;
	
	public Post() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
