import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../../services/category.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Client } from '../../../models/Client';
import { ClientService } from '../../../services/client.service';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-dashboard-menu',
  templateUrl: './dashboard-menu.component.html',
  styleUrls: ['./dashboard-menu.component.css']
})
export class DashboardMenuComponent implements OnInit {
  items: MenuItem[];
  clients: Client[];
  
  constructor(private service: CategoryService,
              private clientService: ClientService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.getCategories();
    this.getClients();
  }

  private getCategories() {
    this.service.getCategories()
      .subscribe(items => this.items = items);
  }

  private getClients() {
    this.clientService.getClients()
      .subscribe(clients => this.clients = clients);
  }

}