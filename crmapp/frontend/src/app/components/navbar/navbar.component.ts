import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { CategoryService } from '../../services/category.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
    isShown = true;
    menubarItems: Array<MenuItem> = [];
    panelMenuItems: Array<MenuItem> = [];
    menuItems: Array<MenuItem> = [];

    constructor(private categoryService: CategoryService) { }

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
        this.categoryService.getCategories()
            .subscribe(
                panelMenuItems => this.panelMenuItems = panelMenuItems
            );
    }
    
    private initCategories() {
        this.panelMenuItems = [
        { label: 'Клиенты', icon: 'fa fa-folder-o', expanded: false, visible: true, items: 
            [
                { label: 'Все клиенты', icon: 'fa fa-asterisk', routerLink: '/clients', visible: true },
            ]
        },
        { label: 'Документооборот', icon: 'fa fa-folder', expanded: false, visible: true, items: 
            [
                { label: 'Договоры с клиентами', icon: 'fa fa-asterisk', routerLink: '/agreements', visible: true },
                { label: 'Документы', icon: 'fa fa-asterisk', routerLink: '/documents', visible: true },
            ]
        },
        { label: 'Кадровый учет', icon: 'fa fa-navicon', expanded: false, visible: true, items: 
            [
                { label: 'Сотрудники', icon: 'fa fa-asterisk', routerLink: '/employees', visible: true },
                { label: 'Отпуски', icon: 'fa fa-asterisk', routerLink: '/vacations', visible: true },
                { label: 'Больничные листы', icon: 'fa fa-asterisk', routerLink: '/sick-lists', visible: true },
            ]
        },
        { label: 'Корреспонденция', icon: 'fa fa-navicon', expanded: false, visible: true, items: 
            [
                { label: 'Исходящие номера', icon: 'fa fa-asterisk', routerLink: '/mail-outputs', visible: true },
                { label: 'Входящие номера', icon: 'fa fa-asterisk', routerLink: '/mail-inputs', visible: true },
            ]
        },
        ];
    }

    private initMenues() {
        this.menubarItems = [
            { title: 'Open/Hide', label: '', icon: 'fa fa-bars', disabled: false,
                command: () => this.showHideMenu(this.isShown)
            },
            { label: 'Главная', icon: 'fa fa-home', routerLink: [''] },
            { label: 'Добавить', icon: 'fa fa-plus', items: [
                { label: 'Новый клиент', routerLink: ['clients/add'] },
                { label: 'Новый сотрудник', routerLink: ['employees/add'] },
                { label: 'Новый договор', routerLink: ['agreements/add'] } ]
            },
            { label: 'Справочники', icon: 'fa fa-list', items: [
                { label: 'Физические лица', routerLink: ['persons'] },
                { label: 'Типы документов', routerLink: ['document-types'] } ]
            },
            { label: 'Инфо', icon: 'fa fa-info-circle', routerLink: ['about'] }
        ];
        this.menuItems = [
            { label: 'Развернуть', icon: 'fa fa-plus-square', 
                command: () => this.expandAll()
            },
            { label: 'Свернуть', icon: 'fa fa-minus-square', 
                command: () => this.collapseAll()
            }
        ]
    }

    private showHideMenu(value: boolean): any {
        this.isShown = !this.isShown;
    }

}
