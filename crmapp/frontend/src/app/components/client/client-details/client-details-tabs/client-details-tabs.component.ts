import { Component, OnInit } from '@angular/core';
import { Client } from '../../../../models/Client';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-client-details-tabs',
  templateUrl: './client-details-tabs.component.html',
  styleUrls: ['./client-details-tabs.component.css']
})
export class ClientDetailsTabsComponent implements OnInit {
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
      {label: 'Руководители', icon: 'fa fa-user-o', routerLink: 'directors'},
      {label: 'Договоры', icon: 'fa fa-file-text-o', routerLink: 'agreements'}
    ];
  }

}
