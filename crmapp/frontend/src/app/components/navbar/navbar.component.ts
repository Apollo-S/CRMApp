import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

    items: MenuItem[];

    constructor() { }

    ngOnInit() {
        this.items = 
        [
            {
                title: 'CRMApp',
                label: 'CRMApp',
                icon: 'fa-home',
                disabled: true
            },
            {
                label: 'Файл',
                icon: 'fa-home',
                items: [
                    {
                        label: 'Новый клиент', 
                        routerLink: ['clients/add']
                    },
                    {
                        label: 'Новый сотрудник', 
                        routerLink: ['employees/add']
                    }
                ]
            },
            {
                label: 'Справочники',
                icon: 'fa-list',
                items: [
                    {
                        label: 'Отпуски', 
                        routerLink: ['vacations']
                    },
                    {
                        label: 'empty', 
                        routerLink: ['']
                    }
                ]
            },
            {
                label: 'Клиенты',
                icon: 'fa-users',
                routerLink: ['clients']
            },
            {
                label: 'Сотрудники',
                icon: 'fa-users',
                routerLink: ['employees']
            },
            {
                label: 'Инфо', 
                icon: 'fa-info-circle',
                routerLink: ['about']
            }
        ]
    }
  
}
