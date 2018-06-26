import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-dashboard-table',
  templateUrl: './dashboard-table.component.html',
  styleUrls: ['./dashboard-table.component.css']
})
export class DashboardTableComponent implements OnInit {
  
  @Input ('title') title: string;
  @Input ('columns') columns: any[];
  @Input ('datasource') datasource: any[];
  @Input ('responsive') responsive: boolean;
  @Input ('reorderableColumns') reorderableColumns: boolean; 
  @Input ('rowHover') rowHover: boolean; 
  @Input ('paginator') paginator: boolean; 
  @Input ('rows') rows: number; 
  @Input ('loading') loading: boolean; 
  @Input ('menuModel') menuModel: MenuItem[]; 
  @Input ('sortField') sortField: string;
  @Input ('autoLayout') autoLayout: boolean;
  @Input ('addButtonTitle') addButtonTitle: string;
  @Input ('editButtonTitle') editButtonTitle: string;
  @Input ('additionalButtonTitle') additionalButtonTitle: string;
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

  initMenu(routerLinkUrl: any) {
    this.menuModel.forEach(
      (menu) => menu.routerLink = [routerLinkUrl, menu.title]
    );
  }

}
