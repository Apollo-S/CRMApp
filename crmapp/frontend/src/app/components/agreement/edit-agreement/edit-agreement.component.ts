import { Component, OnInit, OnDestroy } from '@angular/core';
import { ClientAgreement } from '../../../models/ClientAgreement';
import { Message } from 'primeng/api';
import { Subscription } from 'rxjs';
import { AgreementService } from '../../../services/agreement.service';
import { Router } from '@angular/router';
import { ClientService } from '../../../services/client.service';
import { UtilService } from '../../../services/util.service';
import { Client } from '../../../models/Client';

@Component({
  selector: 'app-edit-agreement',
  templateUrl: './edit-agreement.component.html',
  styleUrls: ['./edit-agreement.component.css']
})
export class EditAgreementComponent implements OnInit , OnDestroy {
  private _propertySubscribtion: Subscription;
  msgs: Message[] = [];
  clients: Client[] = [];
  agreement: ClientAgreement = {};
  years: string;
  ru: any;

  constructor(private utilService: UtilService,
              private clientService: ClientService,
              private agreementService: AgreementService,
              private router: Router) { }

  ngOnInit() {
    this._propertySubscribtion = this.agreementService.property$
      .subscribe(
        p => {
          this.agreement = p;
          this.agreement.dateStart = new Date(this.agreement.dateStart);
          this.clientService.getClientById(this.agreement.clientId)
            .subscribe(
              client => this.agreement.client = client
            );
        }
      );
    this.getClients();
    this.initCalendarSettings();
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  private getClients() {
    this.clientService.getClients()
      .subscribe(
        clients => this.clients = clients
      );
  }

  private initCalendarSettings() {
    this.ru = this.utilService.getCalendarLocalSet();
    this.years = this.utilService.getCalendarYears(5);
  }

}
