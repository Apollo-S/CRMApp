import { Component, OnInit } from '@angular/core';
import { Employee } from '../../../../models/Employee';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-employee-details-tabs',
  templateUrl: './employee-details-tabs.component.html',
  styleUrls: ['./employee-details-tabs.component.css']
})
export class EmployeeDetailsTabsComponent implements OnInit {
  tabs: MenuItem[];
  
  constructor() { }

  ngOnInit() {
    this.initTabs();
  }

  private initTabs(): any {
    this.tabs = [
      {label: 'Основные данные', icon: 'fa fa-address-card-o', routerLink: 'main'},
      {label: 'Адресы', icon: 'fa fa-building-o', routerLink: 'addresses'},
      {label: 'Банковские реквизиты', icon: 'fa fa-bank', routerLink: 'accounts'},
      {label: 'Отпуски', icon: 'fa fa-user-o', routerLink: 'vacations'},
      {label: 'Больничные листы', icon: 'fa fa-user-o', routerLink: 'sicks'},
      // {label: 'Договоры', icon: 'fa fa-file-text-o', routerLink: 'documents'}
    ];
  }

}
