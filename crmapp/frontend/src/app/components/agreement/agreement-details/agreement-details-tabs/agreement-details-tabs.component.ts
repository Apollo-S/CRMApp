import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-agreement-details-tabs',
  templateUrl: './agreement-details-tabs.component.html',
  styleUrls: ['./agreement-details-tabs.component.css']
})
export class AgreementDetailsTabsComponent implements OnInit {
  tabs: MenuItem[];

  constructor() { }

  ngOnInit() {
    this.initTabs();
  }

  private initTabs(): any {
    this.tabs = [
      {label: 'Основные данные', icon: 'fa-address-card-o', routerLink: 'main'},
      {label: 'Связанные документы', icon: 'fa-file-text-o', routerLink: 'documents'}
    ];
  }

}