package crmapp.app.controllers;

import crmapp.app.entities.BaseEntity;
import crmapp.app.services.base.ExtAbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.MappedSuperclass;
import java.util.List;

@MappedSuperclass
public abstract class ExtBaseController<T extends BaseEntity, S extends ExtAbstractService> extends BaseController<T, S> {

        protected ResponseEntity<List<T>> getAllEntitiesByMainEntityId(Integer id) {
            logger.info(LOG_ENTER_METHOD + "getAll" + genericType.getSimpleName() + "Entities()" + LOG_CLOSE);
            List<T> entities = this.service.getAllEntitiesByMainEntityId(id);
            if (entities.size() == 0) {
                logger.info(LOG_ERROR + genericType.getSimpleName() + " Entities were not found" + LOG_CLOSE);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.info(LOG_TEXT + "Count of '" + genericType.getSimpleName() + "' entities = " + entities.size() + LOG_CLOSE);
            logger.info(LOG_OUT_OF_METHOD + "getAll" + genericType.getSimpleName() + "Entities()" + LOG_CLOSE);
            return new ResponseEntity<>(entities, HttpStatus.OK);
        }


}
