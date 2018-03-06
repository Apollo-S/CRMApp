import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

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
  constructor(private router: Router) { }

  ngOnInit() {
  }

  goToEntry(url: string) {
    this.router.navigate([url]);
  }

}
