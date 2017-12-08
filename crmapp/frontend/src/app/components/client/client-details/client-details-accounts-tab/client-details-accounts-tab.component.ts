import { Component, OnInit, Input } from '@angular/core';
import { ClientAccount } from '../../../../models/ClientAccount';
import { Subscription } from 'rxjs';
import { ClientService } from '../../../../services/client.service';
import { Router, ActivatedRoute } from '@angular/router';
import { OnDestroy } from '@angular/core/src/metadata/lifecycle_hooks';

@Component({
  selector: 'app-client-details-accounts-tab',
  templateUrl: './client-details-accounts-tab.component.html',
  styleUrls: ['./client-details-accounts-tab.component.css']
})
export class ClientDetailsAccountsTabComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  accounts: ClientAccount[];
  clientId: number;

  constructor(private service: ClientService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() { 
    this._propertySubscribtion = this.service.property$
      .subscribe(p => {
        this.clientId = p;
        }
      );
    this.getAccountsByClientId(this.clientId);
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  private getAccountsByClientId(clientId: number) {
    this.service.getAccountsByClientId(clientId)
      .subscribe(accounts => this.accounts = accounts);
  }

}
