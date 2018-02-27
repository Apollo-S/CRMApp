package crmapp.app.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "category")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class Category extends UrlBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "label", length = 100)
	private String label;
	
	@Column(name = "icon", length = 50)
	private String icon;

	@Column(name = "router_link", length = 255)
	private String routerLink;
	
	@Column(name = "expanded", length = 255)
	private boolean expanded = false;
	
	@JsonInclude(Include.NON_NULL)
	@Column(name = "items", length = 255)
	private String items;
	
	public Category() {
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getRouterLink() {
		return routerLink;
	}

	public void setRouterLink(String routerLink) {
		this.routerLink = routerLink;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	@Override
	public String getUrl() {
		return "categories/" + getId();
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

}
