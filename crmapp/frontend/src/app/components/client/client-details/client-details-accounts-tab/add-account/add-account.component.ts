import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { UtilService } from '../../../../../services/util.service';
import { ClientService } from '../../../../../services/client.service';
import { ClientAccount } from '../../../../../models/ClientAccount';
import { Client } from '../../../../../models/Client';
import { Message } from 'primeng/api';

@Component({
  selector: 'app-add-account',
  templateUrl: './add-account.component.html',
  styleUrls: ['./add-account.component.css']
})
export class AddAccountComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  msgs: Message[] = [];
  client: Client = {};
  account: ClientAccount = {};
  years: string;
  ru: any;

  constructor(private service: ClientService, 
              private router: Router) { }

  ngOnInit() {
    this._propertySubscribtion = this.service.property$
      .subscribe(
        p => this.client = p
    );
    this.ru = UtilService.getCalendarLocalSet();
    this.years = UtilService.getCalendarYears(5);
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
    this.service.addAccount(this.account, this.client)
      .subscribe(
        response => {
          msg = 'Счет для ' + this.client.alias + ' успешно добавлен (ID=' + response.id + ')';
        this.msgs = [{severity:'success', summary:'Успешно', detail: msg}];
        }
      );
  }

  private goBackToAccounts() {
    setTimeout(
      (router) => {
        this.router.navigate([this.client.url, 'accounts']);
    }, 1500);
  } 

}
