package crmapp.app.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "category")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class Category extends UrlBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "label", length = 100)
	private String label;

	@Column(name = "router_link", length = 255)
	private String routerLink;
	
	@Column(name = "comment", length = 255)
	private String comment;
	
//	private List<MenuItemClient> menuItemClient;

	public Category() {
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getRouterLink() {
		return routerLink;
	}

	public void setRouterLink(String routerLink) {
		this.routerLink = routerLink;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

//	public List<MenuItemClient> getMenuItemClient() {
//		return menuItemClient;
//	}
//
//	public void setMenuItemClient(List<MenuItemClient> menuItemClient) {
//		this.menuItemClient = menuItemClient;
//	}

}
