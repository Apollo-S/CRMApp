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
      { label: 'Договоры', icon: 'fa-file-text-o', title: 'documents' },
      { label: 'Адресы', icon: 'fa-building-o', title: 'addresses' },
      { label: 'Отпуск/Больн. листы', icon: 'fa-user-o', title: 'vacations-sicks' },
      { label: 'Банк. реквизиты', icon: 'fa-bank', title: 'accounts' }
    ];
  }

}
