import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { ClientService } from '../../../../../services/client.service';
import { UtilService } from '../../../../../services/util.service';
import { Client } from '../../../../../models/Client';
import { ClientAccount } from '../../../../../models/ClientAccount';
import { ConfirmationService, Message } from 'primeng/api';

@Component({
  selector: 'app-edit-account',
  templateUrl: './edit-account.component.html',
  styleUrls: ['./edit-account.component.css']
})
export class EditAccountComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  msgs: Message[] = [];
  account: ClientAccount = {};
  client: Client = {}; 
  years: string;
  ru: any;

  constructor(private service: ClientService, 
              private router: Router,
              private route: ActivatedRoute,
              private confirmationService: ConfirmationService) { }

  ngOnInit() {
    let accountId: number;
    this._propertySubscribtion = this.service.property$
      .subscribe(
        p => this.client = p
      );
    this.route.params
      .subscribe(
        (params: Params) => {
          accountId = +params['id'];
          this.getClientAccountById(accountId, this.client);
        }
      );
    this.ru = UtilService.getCalendarLocalSet();
    this.years = UtilService.getCalendarYears(5);
  }
  
  ngOnDestroy(): void {
    this._propertySubscribtion.unsubscribe();
  }
  
  onSubmit() {
    this.update();
    this.goBackToAccounts();
  }

  confirmDeleting() {
    let msg  = 'Банковский счет клиента ' + this.client.alias + ' был успешно удален (ID=' + this.account.id + ')';
    this.confirmationService.confirm({
      message: 'Действительно удалить банк. счет?',
      header: 'Удаление банк. счета',
      icon: 'fa fa-trash',
      accept: () => {
        this.delete(msg);
        this.goBackToAccounts();
      },
      reject: () => {}
    });
  }

  private delete(msg: string) {
    this.service.deleteAccount(this.account.id, this.client)
      .subscribe(
        response => {
          this.msgs = [{severity:'success', summary:'Успешно', detail: msg}];
        }
      );
  }  

  private update(): void {
    let msg  = 'Банковский счет клиента ' + this.client.alias + ' успешно обновлен (ID=' + this.account.id + ')';
    this.service.updateAccount(this.account, this.client)
      .subscribe(
        response => {
          this.msgs = [{severity:'success', summary:'Успешно', detail: msg}];
        }
      );
  }

  private getClientAccountById(accountId: number, client: Client) {
    this.service.getAccountById(accountId, client)
      .subscribe(
        account => {
          this.account = account;
          this.account.dateStart = new Date(this.account.dateStart);
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
