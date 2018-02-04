import { Component, OnInit } from '@angular/core';
import { Category } from '../../models/Category';
import { CategoryService } from '../../services/category.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Client } from '../../models/Client';
import { ClientService } from '../../services/client.service';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  categories: Category[];
  items: MenuItem[];
  clients: Client[];
  
  constructor(private service: CategoryService,
              private clientService: ClientService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.getCategories();
    this.getClients();
    this.items = [
        {
            label: 'Клиенты',
            icon: 'fa-file-o',
            routerLink: '/clients'
        },
        {
            label: 'Договорные отношения',
            icon: 'fa-file-o',
            routerLink: '/agreements'
        },
        {
            label: 'Документооборот',
            icon: 'fa-file-o',
            routerLink: '/documents'
        },
        {
            label: 'Сотрудники',
            icon: 'fa-file-o',
            routerLink: '/employees'
        },
        {
            label: 'Отпуски',
            icon: 'fa-file-o',
            routerLink: '/vacations'
        },
        {
            label: 'Больничные листы',
            icon: 'fa-file-o',
            routerLink: '/vacations'
        },
        {
            label: 'Исходящий номер',
            icon: 'fa-file-o',
            routerLink: '/outputs',

        }
      
     ];

  }

  private getCategories() {
    this.service.getCategories()
      .subscribe(categories => this.categories = categories);
  }

  private getClients() {
    this.clientService.getClients()
      .subscribe(clients => this.clients = clients);
  }

  
 
}
