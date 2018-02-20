import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
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
  
  constructor(private clientService: ClientService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.clientService.getClients()
      .subscribe(
        clients => this.clients = clients
      );
    this.columns = [
      { field: 'alias', header: 'Наименование' },
      { field: 'title', header: 'Полное наименование' },
      { field: 'edrpou', header: 'ЕГРПОУ' }
    ];
  }  

}
