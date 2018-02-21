import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { ClientService } from '../../../services/client.service';
import { Client } from '../../../models/Client';

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.css']
})
export class ClientDetailsComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  client: Client = {};

  constructor(private service: ClientService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    let clientId: number;
    this.route.params
      .subscribe(
        (params: Params) => {
          clientId = +params['id'];
          this.getClientById(clientId);
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
              p => this.client = p
            );
        }
      );
  }

}
