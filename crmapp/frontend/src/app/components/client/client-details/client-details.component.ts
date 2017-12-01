import { Component, OnInit, } from '@angular/core';
import { Client } from '../../../models/Client';
import { ClientService } from '../../../services/client.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Observable } from 'rxjs';
import { DatePipe } from '@angular/common';
import { ClientAddress } from '../../../models/ClientAddress';
import { ClientAccount } from '../../../models/ClientAccount';

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.css']
})
export class ClientDetailsComponent implements OnInit {
  client: Client;
  id: number;
  addresses: ClientAddress[];
  accounts: ClientAccount[];

  constructor(
    private clientService: ClientService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    // this.id = parseInt(this.route.snapshot.params['id']);
    this.route.params
    .subscribe(
      (params: Params) => {
        this.id = +params['id'];
        console.log("id = " + this.id);
      }
    )
    this.getClientById(this.id);
    this.getAddressesByClientId(this.id);
    this.getAccountsByClientId(this.id);
  }

  getClientById(id: number) {
    this.clientService.getClientById(id)
      .then(client => this.client = client);
  }

  getAddressesByClientId(clientId: number) {
    this.clientService.getAddressesByClientId(clientId)
      .then(addresses => this.addresses = addresses);
  }

  getAccountsByClientId(clientId: number) {
    this.clientService.getAccountsByClientId(clientId)
      .then(accounts => this.accounts = accounts);
  }

}
