import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Message, ConfirmationService } from 'primeng/api';
import { Client } from '../../../../models/Client';
import { AgreementService } from '../../../../services/agreement.service';
import { Router } from '@angular/router';
import { ClientAgreement } from '../../../../models/ClientAgreement';
import { ClientService } from '../../../../services/client.service';

@Component({
  selector: 'app-agreement-details-main-tab',
  templateUrl: './agreement-details-main-tab.component.html',
  styleUrls: ['./agreement-details-main-tab.component.css']
})
export class AgreementDetailsMainTabComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  msgs: Message[] = [];
  agreement: ClientAgreement = {};

  constructor(private service: AgreementService,
              private clientService: ClientService,
              private router: Router,
              private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this._propertySubscribtion = this.service.property$
      .subscribe(
        p => {
          this.agreement = p;
          this.getClientById(p.clientId);
        }
      );
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  confirmDeleting() {
    let msg  = 'Договор \"' + this.agreement.number + '(ID=' + this.agreement.id + ')\" успешно удален';
    this.confirmationService.confirm({
      message: 'Действительно удалить договор?',
      header: 'Удаление объекта',
      icon: 'fa fa-trash',
      accept: () => {
        this.delete();
        this.msgs = [{severity:'success', summary:'Успешно', detail: msg}];
      },
      reject: () => {}
    });
  }

  private getClientById(clientId: number) {
    this.clientService.getClientById(clientId)
      .subscribe(
        client => this.agreement.client = client
      );
  }

  private delete(): void {
    this.service.deleteAgreement(this.agreement)
      .subscribe(
        () => this.goBackToAgreements()
      );
  }

  private goBackToAgreements(): void {
    setTimeout(
      () => {
        this.router.navigate(['/agreements']);
      }, 1500);
  }

}
