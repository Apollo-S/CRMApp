import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../../services/employee.service';
import { Employee } from '../../../models/Employee';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {
  employees: Array<Employee> = [];
  columns: any[];
  
  constructor(private service: EmployeeService) { }
  
  ngOnInit() {
    this.getEmployees();
    this.initColumns();
  }
  
  private getEmployees() {
    this.service.getEmployees()
      .subscribe(
        employees => this.employees = employees
      );
  }

  private initColumns(): any {
    this.columns = [
      { field: 'personShortName', header: 'ФИО' },
      { field: 'personInn', header: 'ИНН' },
      { field: 'postTitle', header: 'Должность' }
    ];
  }

}
