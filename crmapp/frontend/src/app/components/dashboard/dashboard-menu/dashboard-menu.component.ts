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
      {"label": "Клиенты", "icon": "fa-folder", "expanded": false, "items" : 
        [
          {"label": "Все клиенты", "icon": "fa-minus", "routerLink": "/clients"},
        ]
      },
      {"label": "Документооборот", "icon": "fa-folder", "expanded": false, "items": 
        [
          {"label": "Договоры с клиентами", "icon": "fa-minus", "routerLink": "/agreements"},
        ]
      },
      {"label": "Кадровый учет", "icon": "fa-folder", "expanded": false, "items": 
        [
          {"label": "Физические лица", "icon": "fa-minus", "routerLink": "/persons"},
          {"label": "Сотрудники", "icon": "fa-minus", "routerLink": "/employees"},
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