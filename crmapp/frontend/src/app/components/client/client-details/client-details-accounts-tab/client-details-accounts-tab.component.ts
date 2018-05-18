import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ClientAccount } from '../../../../models/ClientAccount';
import { ClientService } from '../../../../services/client.service';
import { Client } from '../../../../models/Client';

@Component({
  selector: 'app-client-details-accounts-tab',
  templateUrl: './client-details-accounts-tab.component.html',
  styleUrls: ['./client-details-accounts-tab.component.css']
})
export class ClientDetailsAccountsTabComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  columns: any[];
  accounts: ClientAccount[] = [];
  client: Client = {};

  constructor(private service: ClientService) { }

  ngOnInit() { 
    this._propertySubscribtion = this.service.property$
      .subscribe(
        p => {
          this.client = p;
          this.getAccountsByClientId(p.id);
        }
      );
    this.initColumns();
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  private getAccountsByClientId(id: number) {
    this.service.getAccountsByClientId(id)
      .subscribe(
        accounts => this.accounts = accounts
      );
  }

  private initColumns(): void {
    this.columns = [
      { field: 'id', header: 'ID' },
      { field: 'presentation', header: 'Представление' },
      { field: 'dateStart', header: 'Действует с' }      
    ];
  }

}
