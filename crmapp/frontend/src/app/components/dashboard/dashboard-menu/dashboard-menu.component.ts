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
    this.getCategories();
  }

  private getCategories() {
    this.service.getCategories()
      .subscribe(
        items => this.items = items
      );
  }

}