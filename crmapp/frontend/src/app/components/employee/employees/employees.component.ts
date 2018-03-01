import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  loading: boolean;
  items: MenuItem[];
  
  constructor(private service: EmployeeService,
              private router: Router) { }
  
  ngOnInit() {
    this.getEmployees();
    this.initColumns();
    this.loading = true;
    setTimeout(() => {
      this.loading = false;
    }, 500);
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

  goToEmployee(url: string) {
    this.router.navigate([url]);
  }

  initMenu(employeeId: string) {
    this.items = [
      { label: 'Договоры', icon: 'fa-file-text-o', routerLink: [employeeId, 'documents'] },
      { label: 'Адресы', icon: 'fa-building-o', routerLink: [employeeId, 'addresses'] },
      { label: 'Отпуск/Больн. листы', icon: 'fa-user-o', routerLink: [employeeId, 'vacations-sicks'] },
      { label: 'Банк. реквизиты', icon: 'fa-bank', routerLink: [employeeId, 'accounts'] }
    ];
  }

}
