import { Component, OnInit, OnDestroy } from '@angular/core';
import { Client } from '../../../models/Client';
import { ClientService } from '../../../services/client.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { DatePipe } from '@angular/common';
import { ClientAddress } from '../../../models/ClientAddress';
import { ClientAccount } from '../../../models/ClientAccount';

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.css']
})
export class ClientDetailsComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  client: Client;
  clientId: number;

  constructor(private service: ClientService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.clientId = +params['id'];
          console.log("client id (client-details) = " + this.clientId);
        }
      )
    this.service.property = this.clientId;
    this._propertySubscribtion = this.service.property$
      .subscribe(
        p => {
        this.clientId = p;
        }
      )
    this.getClientById(this.clientId);
  }
  
  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  private getClientById(id: number) {
    this.service.getClientById(id)
      .then(client => this.client = client);
  }

}
