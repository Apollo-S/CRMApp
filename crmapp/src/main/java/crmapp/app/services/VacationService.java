package crmapp.app.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import crmapp.app.entities.Vacation;
import crmapp.app.repositories.VacationRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VacationService extends AbstractService<Vacation, VacationRepository> {

	private static final Logger logger = LoggerFactory.getLogger(VacationService.class);

	@Transactional(readOnly = true)
	public List<Vacation> getAllByEmployeeId(int id) {
		logger.info("OK: VacationService.getAllByEmployeeId()");
		List<Vacation> vacations = repository.findAllVacationsByEmployeeId(id);
		logger.info("OK: Count of vacations equals " + vacations.size());
		return vacations;
	}

}
