import { Component, OnInit, OnDestroy } from '@angular/core';
import { ClientService } from '../../../../../services/client.service';
import { FlashMessagesService } from 'angular2-flash-messages';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { ClientAccount } from '../../../../../models/ClientAccount';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-edit-account',
  templateUrl: './edit-account.component.html',
  styleUrls: ['./edit-account.component.css']
})
export class EditAccountComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  account: ClientAccount = {};
  accountId: number;
  clientId: number; 

  constructor(private service: ClientService, 
              private flashMessagesService: FlashMessagesService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.accountId = +params['id'];
        }
      )
    this._propertySubscribtion = this.service.property$
      .subscribe(p => {
        this.clientId = p;
      }
    );
    this.getClientAccountById(this.accountId, this.clientId);
  }

  delete() {
    if (confirm("Удалить текущий счет?")) {
      this.service.deleteAddress(this.accountId, this.clientId)
        .subscribe(response => {
          this.goBackToAccounts();
      });
    }; 
  }  

  ngOnDestroy(): void {
    this._propertySubscribtion.unsubscribe();
  }

  private getClientAccountById(accountId: number, clientId: number) {
    this.service.getAccountById(accountId, clientId)
      .subscribe(account => this.account = account);
  }

  private goBackToAccounts() {
    this.router.navigate(['/clients', this.clientId, 'accounts']);
  } 

}
