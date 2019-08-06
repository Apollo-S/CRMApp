package crmapp.app.controllers.base;

import crmapp.app.entities.base.BaseEntity;
import crmapp.app.entities.dto.BaseModelDTO;
import crmapp.app.services.Utils;
import crmapp.app.services.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.MappedSuperclass;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import static crmapp.app.services.Utils.convertEntityToDTO;

@MappedSuperclass
public abstract class BaseController<T extends BaseEntity, S extends BaseServiceImpl> {

    protected static final String PARAM_ID = "id";
    protected static final String HEADER_JSON = "Accept=application/json";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static final String NUMBER_SIGNS = "###";
    public static final String LOG_ENTER_METHOD = ANSI_BLUE + NUMBER_SIGNS + " Enter to ";
    public static final String LOG_OUT_OF_METHOD = ANSI_YELLOW + NUMBER_SIGNS + " Out of ";
    public static final String LOG_TEXT = ANSI_GREEN + NUMBER_SIGNS + " ";
    public static final String LOG_ERROR = ANSI_RED + NUMBER_SIGNS + " ";
    public static final String LOG_CLOSE = " " + ANSI_RESET;

    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected S service;

    protected final Class<T> genericType;

    @SuppressWarnings("unchecked")
    public BaseController() {
        this.genericType = ((Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    protected ResponseEntity<List<T>> getAllEntities() {
        logger.info(LOG_ENTER_METHOD + "getAllEntities (type: " + genericType.getSimpleName() + ")" + LOG_CLOSE);
        List<T> entities = this.service.findAll();
        if (entities.size() == 0) {
            logger.info(LOG_ERROR + "Entities (type: " + genericType.getSimpleName() + ") were not found" + LOG_CLOSE);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info(LOG_TEXT + "Count of '" + genericType.getSimpleName() + "' entities = " + entities.size() + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "getAll" + genericType.getSimpleName() + "Entities()" + LOG_CLOSE);
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    protected <U extends BaseModelDTO> ResponseEntity<List<U>> getAllEntities(Class<U> objDTO) {
        logger.info(LOG_ENTER_METHOD + "getAll" + genericType.getSimpleName() + "Entities()" + LOG_CLOSE);
        List<T> entities = this.service.findAll();
        if (entities.size() == 0) {
            logger.info(LOG_ERROR + "Entities (type: " + genericType.getSimpleName() + ") were not found" + LOG_CLOSE);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<U> entitiesDTO = convertEntityToDTO(entities, objDTO);
        logger.info(LOG_TEXT + "Count of '" + genericType.getSimpleName() + "' entities = " + entities.size() + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "getAll" + genericType.getSimpleName() + "Entities()" + LOG_CLOSE);
        return new ResponseEntity<>(entitiesDTO, HttpStatus.OK);
    }

    protected ResponseEntity<T> getEntityBy(int id) {
        logger.info(LOG_ENTER_METHOD + "get" + genericType.getSimpleName() + "EntityById()" + LOG_CLOSE);
        T entity = (T) this.service.findById(id);
        if (entity == null) {
            logger.info(LOG_ERROR + genericType.getSimpleName() + " Entity with ID=" + id + " wasn't found" + LOG_CLOSE);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info(LOG_TEXT + genericType.getSimpleName() + " Entity with ID=" + id + " was found: " + entity + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "get" + genericType.getSimpleName() + "EntityById()" + LOG_CLOSE);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    protected <U extends BaseModelDTO> ResponseEntity<U> getEntityBy(int id, Class<U> objDTO) {
        logger.info(LOG_ENTER_METHOD + "get" + genericType.getSimpleName() + "EntityById()" + LOG_CLOSE);
        T entity = (T) this.service.findById(id);
        if (entity == null) {
            logger.error(LOG_ERROR + genericType.getSimpleName() + " Entity with ID=" + id + " wasn't found" + LOG_CLOSE);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        U entityDTO = convertEntityToDTO(entity, objDTO);
        logger.info(LOG_TEXT + genericType.getSimpleName() + " Entity with ID=" + id + " was found: " + entity + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "get" + genericType.getSimpleName() + "EntityById()" + LOG_CLOSE);
        return new ResponseEntity<>(entityDTO, HttpStatus.OK);
    }

    protected ResponseEntity<T> addEntity(T entity) {
        logger.info(LOG_ENTER_METHOD + "add" + genericType.getSimpleName() + "Entity()" + LOG_CLOSE);
        T savedEntity = (T) this.service.save(entity);
        logger.info(LOG_TEXT + genericType.getSimpleName() + " Entity added with ID=" + savedEntity.getId() + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "add" + genericType.getSimpleName() + "Entity()" + LOG_CLOSE);
        return new ResponseEntity<>(savedEntity, new HttpHeaders(), HttpStatus.CREATED);
    }

    protected <U extends BaseModelDTO> ResponseEntity<U> addEntity(U entityDTO) {
        logger.info(LOG_ENTER_METHOD + "add" + genericType.getSimpleName() + "EntityDTO()" + LOG_CLOSE);
        T entity = Utils.convertDTOToEntity(entityDTO, genericType);
        T savedEntity = (T) this.service.save(entity);
        U savedEntityDTO = (U) convertEntityToDTO(savedEntity, entityDTO.getClass());
        BeanUtils.copyProperties(savedEntity, savedEntityDTO);
        logger.info(LOG_TEXT + genericType.getSimpleName() + " Entity added with ID=" + savedEntity.getId() + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "add" + genericType.getSimpleName() + "EntityDTO()" + LOG_CLOSE);
        return new ResponseEntity<>(savedEntityDTO, new HttpHeaders(), HttpStatus.CREATED);
    }

    protected ResponseEntity<Void> updateEntity(T entity) {
        logger.info(LOG_ENTER_METHOD + "update" + genericType.getSimpleName() + "Entity()" + LOG_CLOSE);
        this.service.update(entity);
        logger.info(LOG_TEXT + genericType.getSimpleName() + " Entity with ID=" + entity.getId() + " was updated: " + entity + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "update" + genericType.getSimpleName() + "Entity()" + LOG_CLOSE);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    protected <U extends BaseModelDTO> ResponseEntity<Void> updateEntity(U entityDTO) {
        logger.info(LOG_ENTER_METHOD + "update" + genericType.getSimpleName() + "Entity()" + LOG_CLOSE);
        T entity = Utils.convertDTOToEntity(entityDTO, genericType);
        this.service.update(entity);
        logger.info(LOG_TEXT + genericType.getSimpleName() + " Entity with ID=" + entityDTO.getId() + " was updated: " + entityDTO + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "update" + genericType.getSimpleName() + "Entity()" + LOG_CLOSE);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    protected ResponseEntity<Void> updateEntity(int id, T entity) {
        logger.info(LOG_ENTER_METHOD + "update" + genericType.getSimpleName() + "Entity()" + LOG_CLOSE);
        this.service.update(id, entity);
        logger.info(LOG_TEXT + genericType.getSimpleName() + " Entity with ID=" + id + " was updated: " + entity + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "update" + genericType.getSimpleName() + "Entity()" + LOG_CLOSE);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    protected ResponseEntity<Void> deleteEntityById(int id) {
        logger.info(LOG_ENTER_METHOD + "delete" + genericType.getSimpleName() + "EntityById()" + LOG_CLOSE);
        this.service.delete(id);
        logger.info(LOG_TEXT + genericType.getSimpleName() + " Entity with ID=" + id + " was deleted" + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "delete" + genericType.getSimpleName() + "EntityById()" + LOG_CLOSE);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
    }

    protected ResponseEntity<List<T>> getAllByNamedQuery(String queryName, Object... params) {
        logger.info(LOG_ENTER_METHOD + "getAllByNamedQuery method (type: " + genericType.getSimpleName() + ")" + LOG_CLOSE);
        List<T> entities = this.service.findAllByNamedQuery(queryName, genericType, params);
        if (entities.size() == 0) {
            logger.info(LOG_ERROR + genericType.getSimpleName() + " Entities were not found" + LOG_CLOSE);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info(LOG_TEXT + "Count of '" + genericType.getSimpleName() + "' entities = " + entities.size() + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "getAllByNamedQuery method | " + genericType.getSimpleName() + " | Entities()" + LOG_CLOSE);
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    protected <U extends BaseModelDTO> ResponseEntity<List<U>> getAllByNamedQuery(String queryName, Class<U> entityDTO,
                                                                                  Object... params) {
        logger.info(LOG_ENTER_METHOD + "getAllByNamedQuery method (type: " + entityDTO.getSimpleName() + ")" + LOG_CLOSE);
        List<T> entities = this.service.findAllByNamedQuery(queryName, genericType, params);
        if (entities.size() == 0) {
            logger.info(LOG_ERROR + genericType.getSimpleName() + " Entities were not found" + LOG_CLOSE);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<U> entitiesDTO = convertEntityToDTO(entities, entityDTO);
        return new ResponseEntity<>(entitiesDTO, HttpStatus.OK);
    }

}
