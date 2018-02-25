import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../../services/client.service';
import { Client } from '../../../models/Client';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {
  clients: Client[];
  columns: any[];
  
  constructor(private service: ClientService) { }

  ngOnInit() {
    this.getClients();
    this.initColumns();
  } 

  private getClients(): any {
    this.service.getClients()
      .subscribe(
        clients => this.clients = clients
      );
  }

  private initColumns() {
    this.columns = [
      { field: 'alias', header: 'Наименование' },
      { field: 'title', header: 'Полное наименование' },
      { field: 'edrpou', header: 'ЕГРПОУ' }
    ];
  }

}
