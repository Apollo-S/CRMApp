import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../services/employee.service';
import { Employee } from '../../models/Employee';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {
  employees: Array<Employee> = [];

  constructor(private employeeService: EmployeeService) { }
  
    ngOnInit() {
      this.getEmployees();
    }
  
    getEmployees() {
      this.employeeService.getEmployees()
        .then(employees => this.employees = employees);
    }

}
