import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-client-details-template-table',
  templateUrl: './client-details-template-table.component.html',
  styleUrls: ['./client-details-template-table.component.css']
})
export class ClientDetailsTemplateTableComponent implements OnInit {

  @Input ('addButtonTitle') addButtonTitle: string;
  @Input ('editButtonTitle') editButtonTitle: string;
  @Input ('columns') columns: any[];
  @Input ('datasource') datasource: any[];
  @Input ('responsive') responsive: boolean;
  @Input ('reorderableColumns') reorderableColumns: boolean; 
  @Input ('rowHover') rowHover: boolean; 
  @Input ('paginator') paginator: boolean; 
  @Input ('rows') rows: number; 
  @Input ('loading') loading: boolean; 
  @Input ('sortField') sortField: string;
  @Input ('autoLayout') autoLayout: boolean;
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

}
