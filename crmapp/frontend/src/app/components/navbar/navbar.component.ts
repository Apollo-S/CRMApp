import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { CategoryService } from '../../services/category.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
    isShown: boolean = true;
    menubarItems: MenuItem[];
    panelMenuItems: MenuItem[];
    menuItems: MenuItem[];
    navbarWidth: string = 'navbar-collapsed';

    constructor(private service: CategoryService) { }

    ngOnInit() {
        this.initCategories();
        this.initMenues();
    }
  
    expandAll() {
        this.panelMenuItems.forEach(
            (item) => item.expanded = true
        );
    }
    
    collapseAll() {
        this.panelMenuItems.forEach(
            (item) => item.expanded = false
        );
    }
    
    private getCategories() {
        this.service.getCategories()
            .subscribe(
                panelMenuItems => this.panelMenuItems = panelMenuItems
            );
    }
    
    private initCategories() {
        this.panelMenuItems = [
        { "label": "Клиенты", "icon": "fa-folder-o", "expanded": false, "visible": true, "items": 
            [
                { "label": "Все клиенты", "icon": "fa-asterisk", "routerLink": "/clients", "visible": true },
            ]
        },
        { "label": "Документооборот", "icon": "fa-folder", "expanded": false, "visible": true, "items": 
            [
                { "label": "Договоры с клиентами", "icon": "fa-asterisk", "routerLink": "/agreements", "visible": true },
                { "label": "Документы", "icon": "fa-asterisk", "routerLink": "/documents", "visible": true },
            ]
        },
        { "label": "Кадровый учет", "icon": "fa-navicon", "expanded": false, "visible": true, "items": 
            [
                { "label": "Сотрудники", "icon": "fa-asterisk", "routerLink": "/employees", "visible": true },
                { "label": "Отпуски", "icon": "fa-asterisk", "routerLink": "/vacations", "visible": true },
                { "label": "Больничные листы", "icon": "fa-asterisk", "routerLink": "/sick-lists", "visible": true },
            ]
        },
        { "label": "Корреспонденция", "icon": "fa-navicon", "expanded": false, "visible": true, "items": 
            [
                { "label": "Исходящие номера", "icon": "fa-asterisk", "routerLink": "/mail-outputs", "visible": true },
                { "label": "Входящие номера", "icon": "fa-asterisk", "routerLink": "/mail-inputs", "visible": true },
            ]
        },
        ];
    }

    private initMenues() {
        this.menubarItems = [
            { title: 'Open/Hide', label: '', icon: 'fa-bars', disabled: false,
                command: (event) => this.showHideMenu(this.isShown)
            },
            { label: 'Главная', icon: 'fa-home', routerLink: [''] },
            { label: 'Добавить', icon: 'fa-plus', items: [
                { label: 'Новый клиент', routerLink: ['clients/add'] },
                { label: 'Новый сотрудник', routerLink: ['employees/add'] },
                { label: 'Новый договор', routerLink: ['agreements/add'] } ]
            },
            { label: 'Справочники', icon: 'fa-list', items: [
                { label: "Физические лица", routerLink: ['persons'] },
                { label: 'Типы документов', routerLink: ['document-types'] } ]
            },
            { label: 'Инфо', icon: 'fa-info-circle', routerLink: ['about'] }
        ];
        this.menuItems = [
            { label: 'Развернуть', icon: 'fa-plus-square', 
                command: (event) => this.expandAll()
            },
            { label: 'Свернуть', icon: 'fa-minus-square', 
                command: (event) => this.collapseAll()
            }
        ]
    }

    private showHideMenu(value: boolean): any {
        this.isShown = value ? false : true;
        this.navbarWidth = (this.isShown) ? 'navbar-collapsed' : 'navbar-expanded';
    }

}
