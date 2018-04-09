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
    gridWidth: string = '9';

    constructor(private service: CategoryService) { }

    ngOnInit() {
        this.initCategories();
        this.initMenubar();
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
                { "label": "Физические лица", "icon": "fa-asterisk", "routerLink": "/persons", "visible": true },
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

    private initMenubar() {
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
                { label: 'Отпуски', routerLink: ['vacations'] },
                { label: 'empty', routerLink: [''] } ]
            },
            { label: 'Инфо', icon: 'fa-info-circle', routerLink: ['about'] }
        ]
    }

    private showHideMenu(value: boolean): any {
        this.isShown = value ? false : true;
        this.gridWidth = (this.isShown) ? '9' : '12';
    }

}
