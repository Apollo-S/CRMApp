package crmapp.app.controllers.base;

import crmapp.app.entities.base.BaseEntity;
import crmapp.app.services.base.ExtendedBaseServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.MappedSuperclass;
import java.util.List;

import static crmapp.app.services.Utils.convertEntityToDTO;

@MappedSuperclass
public abstract class ExtendedBaseController<T extends BaseEntity, S extends ExtendedBaseServiceImpl>
        extends BaseController<T, S> {

    protected ResponseEntity<List<T>> getAllFilterBy(String entityName, Integer entityId) {
        logger.info(LOG_ENTER_METHOD + "getAll" + genericType.getSimpleName() + "Entities()" + LOG_CLOSE);
        List<T> entities = this.service.findAllFilterBy(entityName, entityId);
        if (entities.size() == 0) {
            logger.info(LOG_ERROR + genericType.getSimpleName() + " Entities were not found" + LOG_CLOSE);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info(LOG_TEXT + "Count of '" + genericType.getSimpleName() + "' entities = " + entities.size() + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "getAll" + genericType.getSimpleName() + "Entities()" + LOG_CLOSE);
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    protected <U> ResponseEntity<List<U>> getAllFilterBy(String entityName, Integer entityId, Class<U> objDTO) {
        logger.info(LOG_ENTER_METHOD + "getAll" + genericType.getSimpleName() + "Entities()" + LOG_CLOSE);
        List<T> entities = this.service.findAllFilterBy(entityName, entityId);
        if (entities.size() == 0) {
            logger.info(LOG_ERROR + genericType.getSimpleName() + " Entities were not found" + LOG_CLOSE);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<U> entitiesDTO = convertEntityToDTO(entities, objDTO);
        logger.info(LOG_TEXT + "Count of '" + genericType.getSimpleName() + "' entities = " + entities.size() + LOG_CLOSE);
        logger.info(LOG_OUT_OF_METHOD + "getAll" + genericType.getSimpleName() + "Entities()" + LOG_CLOSE);
        return new ResponseEntity<>(entitiesDTO, HttpStatus.OK);
    }

}
