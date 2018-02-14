import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { ClientService } from '../../../services/client.service';
import { Client } from '../../../models/Client';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.css']
})
export class ClientDetailsComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  client: Client = {};
  clientId: number;

  constructor(private service: ClientService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.clientId = +params['id'];
          this.getClientById(this.clientId);
        }
      );
  }
  
  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  private getClientById(id: number) {
    this.service.getClientById(id)
      .subscribe(
        client => {
          this.client = client;
          this.service.property = this.client;
          this._propertySubscribtion = this.service.property$
            .subscribe(
              p => {
                this.client = p;
              }
            );
        }
      );
  }

}
