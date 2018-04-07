package crmapp.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crmapp.app.entities.Category;
import crmapp.app.repositories.CategoryRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/categories")
public class CategoryController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<Category>> getAllCategories() {
		logger.info(LOG_ENTER_METHOD + "getAllCategories()" + LOG_CLOSE);
		List<Category> categories = categoryRepository.findAll();
		if (categories.size() == 0) {
			logger.info(LOG_ERROR + "Categories were not found" + LOG_CLOSE);
			return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of Categories: " + categories.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllCategories()" + LOG_CLOSE);
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}

}
