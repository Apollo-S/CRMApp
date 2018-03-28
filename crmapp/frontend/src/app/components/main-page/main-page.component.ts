import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../models/Category';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  categories: MenuItem[] = [];
  
  constructor(private service: CategoryService) { }

  ngOnInit() {
    this.initCategories();
  }

  private initCategories() {
    this.service.getCategories()
      .subscribe(
        categories => this.categories = categories
      );
  }

}
