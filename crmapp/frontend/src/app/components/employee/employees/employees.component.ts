import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../../services/employee.service';
import { Employee } from '../../../models/Employee';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {
  employees: Employee[] = [];
  columns: any[];
  items: MenuItem[];
  
  constructor(private service: EmployeeService) { }
  
  ngOnInit() {
    this.initColumns();
    this.getEmployees();
    this.initMenu();
  }
  
  private getEmployees(): any {
    this.service.getEmployees()
      .subscribe(
        employees => this.employees = employees
      );
  }

  private initColumns() {
    this.columns = [
      { field: 'personShortName', header: 'ФИО' },
      { field: 'personInn', header: 'ИНН' },
      { field: 'postTitle', header: 'Должность' }
    ];
  }

  private initMenu() {
    this.items = [
      { label: 'Отпуски', icon: 'fa-user-o', title: 'vacations' },
      { label: 'Больн. листы', icon: 'fa-user-o', title: 'sicks' },
      { label: 'Адресы', icon: 'fa-building-o', title: 'addresses' },
      { label: 'Банк. реквизиты', icon: 'fa-bank', title: 'accounts' }
    ];
  }

}
