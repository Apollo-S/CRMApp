import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../../services/client.service';
import { Client } from '../../../models/Client';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {
  clients: Client[];
  columns: any[];
  loading: boolean;
  items: MenuItem[];
  
  constructor(private service: ClientService,
              private router: Router) { }

  ngOnInit() {
    this.getClients();
    this.initColumns();
    this.loading = true;
    setTimeout(() => {
      this.loading = false;
    }, 500);
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

  goToClient(url: string) {
    this.router.navigate([url]);
  }

  initMenu(clientId: string) {
    this.items = [
      { label: 'Договоры', icon: 'fa-file-text-o', routerLink: [clientId, 'agreements'] },
      { label: 'Адресы', icon: 'fa-building-o', routerLink: [clientId, 'addresses'] },
      { label: 'Руководители', icon: 'fa-user-o', routerLink: [clientId, 'directors'] },
      { label: 'Банк. реквизиты', icon: 'fa-bank', routerLink: [clientId, 'accounts'] }
    ];
  }

}
