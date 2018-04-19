import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../../services/client.service';
import { Client } from '../../../models/Client';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {
  clients: Client[] = [];
  columns: any[];
  items: MenuItem[];
  
  constructor(private service: ClientService) { }

  ngOnInit() {
    this.initColumns();
    this.getClients();
    this.initMenu();
  } 

  private getClients(): any {
    this.service.getClients()
      .subscribe(
        clients => this.clients = clients
      );
  }

  private initColumns() {
    this.columns = [
      { field: 'id', header: 'ID' },
      { field: 'alias', header: 'Наименование' },
      { field: 'title', header: 'Полное наименование' },
      { field: 'edrpou', header: 'ЕГРПОУ' }
    ];
  }

  private initMenu() {
    this.items = [
      { label: 'Договоры', icon: 'fa-file-text-o', title: 'agreements' },
      { label: 'Адресы', icon: 'fa-building-o', title: 'addresses' },
      { label: 'Руководители', icon: 'fa-user-o', title: 'directors' },
      { label: 'Банк. реквизиты', icon: 'fa-bank', title: 'accounts' }
    ];
  }

}
