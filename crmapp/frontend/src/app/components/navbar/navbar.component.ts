import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/primeng';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  items: MenuItem[];

  constructor() { }

  ngOnInit() {
    this.items = [
    {
        title: 'CRMApp',
        label: 'CRMApp',
        icon: 'fa-home',
        disabled: true
    },
    {
        label: 'Справочники',
        icon: 'fa-list',
        items: [
        {
            label: 'Отпуски', 
            routerLink: ['vacations']
        }
        ]
    },
    {
        label: 'Клиенты',
        icon: 'fa-users',
        routerLink: 'clients'
    },
    {
        label: 'Сотрудники',
        icon: 'fa-users',
        routerLink: 'employees'
    },
    {
        label: 'Инфо', 
        icon: 'fa-info-circle',
        routerLink: 'about'
    }
  ];
}
  

}
