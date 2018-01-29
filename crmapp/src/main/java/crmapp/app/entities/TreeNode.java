package crmapp.app.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TreeNode implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String label;
	private String data;
	private String icon;
	private String expandedIcon;
	private String collapsedIcon;
	private List<TreeNode> children = null;
	private TreeNode parent;
	
	public TreeNode() {
		this.children = new ArrayList<TreeNode>();
	}

	public TreeNode(String label, String data, String icon, String expandedIcon, String collapsedIcon,
			List<TreeNode> children, TreeNode parent) {
		this.label = label;
		this.data = data;
		this.icon = icon;
		this.expandedIcon = expandedIcon;
		this.collapsedIcon = collapsedIcon;
		this.children = children;
		this.parent = parent;
	}

	public TreeNode(String data) {
		this.children = new ArrayList<TreeNode>();
		this.data = data;
	}

	public void addChild(TreeNode child) {
		children.add(child);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getExpandedIcon() {
		return expandedIcon;
	}

	public void setExpandedIcon(String expandedIcon) {
		this.expandedIcon = expandedIcon;
	}

	public String getCollapsedIcon() {
		return collapsedIcon;
	}

	public void setCollapsedIcon(String collapsedIcon) {
		this.collapsedIcon = collapsedIcon;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

}
