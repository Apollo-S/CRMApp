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
          routerLink: '/clients',
          items: [
              {label: ''},
              {label: 'this.clients[1].alias'},
              {label: 'this.clients[2].alias'}
          ]
      },
      {
          label: 'Договорные отношения',
          routerLink: '/agreements',
          icon: 'fa-edit',
          items: [
              {label: 'Undo', icon: 'fa-mail-forward'},
              {label: 'Redo', icon: 'fa-mail-reply'}
          ]
      },
      {
          label: 'Help',
          icon: 'fa-question',
          items: [
              {
                  label: 'Contents'
              },
              {
                  label: 'Search', 
                  icon: 'fa-search', 
                  items: [
                      {
                          label: 'Text', 
                          items: [
                              {
                                  label: 'Workspace'
                              }
                          ]
                      },
                      {
                          label: 'File'
                      }
              ]}
          ]
      },
      {
          label: 'Actions',
          icon: 'fa-gear',
          items: [
              {
                  label: 'Edit',
                  icon: 'fa-refresh',
                  items: [
                      {label: 'Save', icon: 'fa-save'},
                      {label: 'Update', icon: 'fa-save'},
                  ]
              },
              {
                  label: 'Other',
                  icon: 'fa-phone',
                  items: [
                      {label: 'Delete', icon: 'fa-minus'}
                  ]
              }
          ]
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
