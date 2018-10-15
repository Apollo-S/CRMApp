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
  columns = [];
  items: MenuItem[] = [];
  
  constructor(public clientService: ClientService) {
  }

  ngOnInit() {
    debugger
    this.getClients();
    this.initColumns();
    this.initMenu();
  } 

  private getClients() {
    this.clientService.emitterClients
      .subscribe(
        clients => this.clients = clients
      )
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
      { label: 'Договоры', icon: 'fa fa-file-text-o', title: 'agreements' },
      { label: 'Адресы', icon: 'fa fa-building-o', title: 'addresses' },
      { label: 'Руководители', icon: 'fa fa-user-o', title: 'directors' },
      { label: 'Банк. реквизиты', icon: 'fa fa-bank', title: 'accounts' }
    ];
  }

}
