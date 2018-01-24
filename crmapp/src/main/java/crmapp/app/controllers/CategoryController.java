package crmapp.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crmapp.app.entities.Category;
import crmapp.app.entities.Client;
import crmapp.app.entities.MenuItemClient;
import crmapp.app.entities.TreeItem;
import crmapp.app.entities.TreeNode;
import crmapp.app.repositories.CategoryRepository;
import crmapp.app.repositories.ClientRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/categories")
public class CategoryController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ClientRepository clientRepository;

//	@GetMapping(value = "", headers = HEADER_JSON)
//	public ResponseEntity<List<Category>> getAllCategorys() {
//		List<Category> categories = categoryRepository.findAll();
//		if (categories.size() == 0) {
//			logger.info("<==/////////// There are no any Category ... ///////////==>");
//			return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
//	}
	
	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<TreeNode> getAllCategories() {
		String expandedIcon = "fa-folder-open";
		String collapsedIcon = "fa-folder";
		
		TreeNode root = new TreeNode("root");
		root.setLabel("root");
		
		TreeNode nodeClients = new TreeNode("Клиенты");
		nodeClients.setLabel("Клиенты");
		nodeClients.setExpandedIcon(expandedIcon);
		nodeClients.setCollapsedIcon(collapsedIcon);
		List<String> aliases = clientRepository.findAllClientsAliases();
		System.out.println(aliases);
		for(String alias : aliases) {
			TreeNode child = new TreeNode(alias);
			child.setLabel(alias);
			nodeClients.addChild(child);
		}
		root.addChild(nodeClients);
		
		TreeNode nodeAgreements = new TreeNode("Договоры");
		nodeAgreements.setLabel("Договоры");
		nodeAgreements.setExpandedIcon(expandedIcon);
		nodeAgreements.setCollapsedIcon(collapsedIcon);
		root.addChild(nodeAgreements);
		
		TreeNode nodeDocuments = new TreeNode("Документооборот");
		nodeDocuments.setLabel("Документооборот");
		nodeDocuments.setExpandedIcon(expandedIcon);
		nodeDocuments.setCollapsedIcon(collapsedIcon);
		root.addChild(nodeDocuments);
		
		return new ResponseEntity<TreeNode>(root, HttpStatus.OK);
	}

}
