import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Client } from '../../../../models/Client';
import { ClientService } from '../../../../services/client.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-client-details-main-tab',
  templateUrl: './client-details-main-tab.component.html',
  styleUrls: ['./client-details-main-tab.component.css']
})
export class ClientDetailsMainTabComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  client: Client = {};
  clientId: number;

  constructor(private service: ClientService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this._propertySubscribtion = this.service.property$
    .subscribe(p => {
      this.clientId = p;
    });
    this.getClientById(this.clientId);
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  getClientById(clientId: number) {
    this.service.getClientById(clientId)
      .subscribe(client => this.client = client);
  }

  delete(id: number): void {
    this.service.deleteClient(this.clientId)
      .then(() => this.goBackToClients());
  }

  goBackToClients(): void {
      this.router.navigate(['/clients']);
  }

}
