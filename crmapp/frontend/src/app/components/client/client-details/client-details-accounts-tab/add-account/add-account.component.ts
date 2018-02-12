import { Component, OnInit, OnDestroy } from '@angular/core';
import { ClientAccount } from '../../../../../models/ClientAccount';
import { Subscription } from 'rxjs';
import { ClientService } from '../../../../../services/client.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Message } from 'primeng/api';

@Component({
  selector: 'app-add-account',
  templateUrl: './add-account.component.html',
  styleUrls: ['./add-account.component.css']
})
export class AddAccountComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  msgs: Message[] = [];
  account: ClientAccount = {};
  clientId: number;  
  years: string;

  constructor(private service: ClientService, 
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this._propertySubscribtion = this.service.property$
    .subscribe(p => {
      this.clientId = p;
      }
    );
    let currentYear = new Date().getFullYear();
    this.years = ((currentYear - 5).toString()) + ':' + currentYear.toString();
  }

  onSubmit() {
    this.save();
    this.goBackToAccounts();
  }

  ngOnDestroy(): void {
    this._propertySubscribtion.unsubscribe();
  }

  private save(): void {
    let msg  = '';
    this.service.addAccount(this.account, this.clientId)
      .subscribe(response => {
        msg = 'Счет успешно добавлен (ID=' + response.id + ')';
        this.msgs = [{severity:'success', summary:'Успешно', detail: msg}];
      });
  }

  private goBackToAccounts() {
    setTimeout((router) => {
      this.router.navigate(['/clients', this.clientId, 'accounts']);
    }, 1500);
  } 

}
