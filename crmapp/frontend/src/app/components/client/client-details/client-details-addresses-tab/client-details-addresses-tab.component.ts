import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ClientService } from '../../../../services/client.service';
import { ClientAddress } from '../../../../models/ClientAddress';
import { Client } from '../../../../models/Client';

@Component({
  selector: 'app-client-details-addresses-tab',
  templateUrl: './client-details-addresses-tab.component.html',
  styleUrls: ['./client-details-addresses-tab.component.css']
})
export class ClientDetailsAddressesTabComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  columns: any[] = [];
  addresses: ClientAddress[] = [];
  client: Client = {};
  
  constructor(private service: ClientService) { }

  ngOnInit() { 
    this._propertySubscribtion = this.service.property$
      .subscribe(
        p => {
          this.client = p;
          this.getAddressesByClientId(p.id);
        }
      );    
    this.initColumns();
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  private getAddressesByClientId(id: number) {
    this.service.getAddressesByClientId(id)
      .subscribe(
        addresses => this.addresses = addresses
      );
  }

  private initColumns(): void {
    this.columns = [
      { field: 'id', header: 'ID' },
      { field: 'presentation', header: 'Адрес' },
      { field: 'dateStart', header: 'Действует с' }      
    ];
  }

}
