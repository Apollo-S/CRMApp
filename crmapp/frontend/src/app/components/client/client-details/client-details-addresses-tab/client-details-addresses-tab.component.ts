import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { ClientAddress } from '../../../../models/ClientAddress';
import { Subscription } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import { ClientService } from '../../../../services/client.service';

@Component({
  selector: 'app-client-details-addresses-tab',
  templateUrl: './client-details-addresses-tab.component.html',
  styleUrls: ['./client-details-addresses-tab.component.css']
})
export class ClientDetailsAddressesTabComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  addresses: ClientAddress[];
  clientId: number;
  
  constructor(private service: ClientService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() { 
    this._propertySubscribtion = this.service.property$
    .subscribe(p => {
      this.clientId = p;
    });
    this.getAddressesByClientId(this.clientId);
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  getAddressesByClientId(clientId: number) {
    this.service.getAddressesByClientId(clientId)
      .then(addresses => this.addresses = addresses);
  }

}
