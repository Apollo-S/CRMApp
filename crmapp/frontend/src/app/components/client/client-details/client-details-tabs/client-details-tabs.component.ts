import { Component, OnInit, Input } from '@angular/core';
import { Client } from '../../../../models/Client';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-client-details-tabs',
  templateUrl: './client-details-tabs.component.html',
  styleUrls: ['./client-details-tabs.component.css']
})
export class ClientDetailsTabsComponent implements OnInit {

  items: MenuItem[];

  constructor() { }

  ngOnInit() {
    this.items = [
      {label: 'Основные данные', icon: 'fa-address-card-o', routerLink: 'main'},
      {label: 'Адресы', icon: 'fa-building-o', routerLink: 'addresses'},
      {label: 'Банковские реквизиты', icon: 'fa-bank', routerLink: 'accounts'},
      {label: 'Руководители', icon: 'fa-user-o', routerLink: 'directors'},
      {label: 'Договоры', icon: 'fa-file-text-o', routerLink: 'agreements'}
    ];
  }

}
