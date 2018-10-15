import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-dashboard-table',
  templateUrl: './dashboard-table.component.html',
  styleUrls: ['./dashboard-table.component.css']
})
export class DashboardTableComponent implements OnInit {
  
  @Input() title: string;
  @Input() columns: Array<any>;
  @Input() datasource: Array<any>;
  @Input() responsive: boolean;
  @Input() reorderableColumns: boolean; 
  @Input() rowHover: boolean; 
  @Input() paginator: boolean; 
  @Input() rows: number; 
  @Input() loading: boolean; 
  @Input() menuModel: Array<MenuItem>; 
  @Input() sortField: string;
  @Input() autoLayout: boolean;
  @Input() addButtonTitle: string;
  @Input() editButtonTitle: string;
  @Input() additionalButtonTitle: string;
  selectedItem: any;

  constructor(private router: Router) { }

  ngOnInit() {
    setTimeout(() => {
      this.loading = false;
    }, 700);
  }

  goToEntry(url: string) {
    this.router.navigate([url]);
  }

  initMenu(routerLinkUrl) {
    this.menuModel.forEach(
      (menu) => menu.routerLink = [routerLinkUrl, menu.title]
    );
  }

}
