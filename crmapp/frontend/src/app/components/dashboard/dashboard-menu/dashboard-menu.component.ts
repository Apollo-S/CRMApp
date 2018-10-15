import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../../services/category.service';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-dashboard-menu',
  templateUrl: './dashboard-menu.component.html',
  styleUrls: ['./dashboard-menu.component.css']
})
export class DashboardMenuComponent implements OnInit {
  items: MenuItem[];
  
  constructor(private service: CategoryService) { }

  ngOnInit() {
    this.initCategories();
  }

  private getCategories() {
    this.service.getCategories()
      .subscribe(
        items => this.items = items
      );
  }

  private initCategories() {
    this.items = [
      {"label": "Клиенты", "icon": "fa fa-folder-o", "expanded": false, "visible": true, "items": 
        [
          {"label": "Все клиенты", "icon": "fa fa-asterisk", "routerLink": "/clients", "visible": true },
        ]
      },
      {"label": "Документооборот", "icon": "fa fa-folder", "expanded": false, "visible": true, "items": 
        [
          {"label": "Договоры с клиентами", "icon": "fa fa-asterisk", "routerLink": "/agreements", "visible": true },
        ]
      },
      {"label": "Кадровый учет", "icon": "fa fa-navicon", "expanded": false, "visible": true, "items": 
        [
          {"label": "Сотрудники", "icon": "fa fa-asterisk", "routerLink": "/employees", "visible": true },
          {"label": "Отпуски", "icon": "fa fa-asterisk", "routerLink": "/vacations", "visible": true },
          {"label": "Больничные листы", "icon": "fa fa-asterisk", "routerLink": "/sick-lists", "visible": true },
        ]
      },
      {"label": "Корреспонденция", "icon": "fa fa-navicon", "expanded": false, "visible": true, "items": 
        [
          {"label": "Исходящие номера", "icon": "fa fa-asterisk", "routerLink": "/output", "visible": true },
          {"label": "Входящие номера", "icon": "fa fa-asterisk", "routerLink": "/input", "visible": true },
        ]
      },
    ];
  }

  expandAll() {
    this.items.forEach(
      (item) => item.expanded = true
    );
  }

  collapseAll() {
    this.items.forEach(
      (item) => item.expanded = false
    );
  }

}