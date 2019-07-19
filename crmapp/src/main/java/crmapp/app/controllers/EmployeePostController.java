package crmapp.app.controllers;

import crmapp.app.controllers.base.ExtendedBaseController;
import crmapp.app.entities.Employee;
import crmapp.app.entities.EmployeePost;
import crmapp.app.entities.dto.EmployeePostDTO;
import crmapp.app.entities.dto.EmployeePostFullDTO;
import crmapp.app.services.EmployeePostService;
import crmapp.app.services.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/employees/{employeeId}/posts")
public class EmployeePostController extends ExtendedBaseController<EmployeePost, EmployeePostService> {

    @GetMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<List<EmployeePostFullDTO>> getAllEmployeePostsByEmployeeId(
            @PathVariable("employeeId") Integer employeeId) {
        return super.getAllFilterBy(Utils.getEntityName(Employee.class), employeeId, EmployeePostFullDTO.class);
    }

    @GetMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<EmployeePostDTO> getEmployeePostById(@PathVariable(PARAM_ID) int id) {
        return super.getEntityBy(id, EmployeePostDTO.class);
    }

    @PostMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<EmployeePost> addEmployeePost(@PathVariable("employeeId") int employeeId,
                                                        @RequestBody EmployeePost post) {
        return super.addEntity(post);
    }

    @PutMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> updateEmployeePost(@PathVariable("employeeId") int employeeId,
                                                   @RequestBody EmployeePost post) {
        return super.updateEntity(post);
    }

    @DeleteMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> deleteEmployeePost(@PathVariable(PARAM_ID) int id) {
        return super.deleteEntityById(id);
    }

}
