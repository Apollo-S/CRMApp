package crmapp.app.controllers;

import crmapp.app.entities.BaseEntity;
import crmapp.app.services.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.MappedSuperclass;
import java.util.List;

@MappedSuperclass
public abstract class BaseController<T extends BaseEntity> {

	static final String PARAM_ID = "id";
	static final String HEADER_JSON = "Accept=application/json";
	
	static final String REQUEST_MAPPING_EMPTY = "";
	static final String REQUEST_MAPPING_GET_ALL = "/";
	static final String REQUEST_MAPPING_BY_ID = "/{" + PARAM_ID + "}";
	static final String REQUEST_MAPPING_ADD = "/add/";
	static final String REQUEST_MAPPING_UPDATE = "/update/{id}";
	static final String REQUEST_MAPPING_DELETE = "/delete/{id}";

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	public static final String NUMBER_SIGNS = "###";
	public static final String LOG_ENTER_METHOD = ANSI_BLUE + NUMBER_SIGNS + " Enter to ";
	public static final String LOG_OUT_OF_METHOD = ANSI_YELLOW + NUMBER_SIGNS + " Out of ";
	public static final String LOG_TEXT = ANSI_GREEN + NUMBER_SIGNS + " ";
	public static final String LOG_ERROR = ANSI_RED + NUMBER_SIGNS + " ";
	public static final String LOG_CLOSE = " " + ANSI_RESET;

	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	public ResponseEntity<List<T>> getAllEntities(AbstractService service) {
        logger.info("  " + LOG_ENTER_METHOD + "getAllEntities()" + LOG_CLOSE);
        List<T> entities = service.findAll();
        if (entities.size() == 0) {
            logger.info("  " + LOG_ERROR + "Entities for were not found" + LOG_CLOSE);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("  " + LOG_TEXT + "Count of Entities: " + entities.size() + LOG_CLOSE);
        logger.info("  " + LOG_OUT_OF_METHOD + "getAllEntities()" + LOG_CLOSE);
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

	public ResponseEntity<T> getEntityById(int id, AbstractService service) {
		logger.info(LOG_ENTER_METHOD + "getEntityById()" + LOG_CLOSE);
		T entity = (T) service.findById(id);
		if (entity == null) {
			logger.info(LOG_ERROR + "Entity with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_TEXT + "Entity with ID=" + id + " was found: " + entity + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getEntityById()" + LOG_CLOSE);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
}
