import { Component, OnInit } from '@angular/core';
import { MenuItem, Message } from 'primeng/api';
import { Client } from '../../../models/Client';
import { ClientAgreement } from '../../../models/ClientAgreement';
import { UtilService } from '../../../services/util.service';
import { ClientService } from '../../../services/client.service';
import { AgreementService } from '../../../services/agreement.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-agreement',
  templateUrl: './add-agreement.component.html',
  styleUrls: ['./add-agreement.component.css']
})
export class AddAgreementComponent implements OnInit {
  tabs: MenuItem[];
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
    this.initTabs();
    this.getClients();
    this.initCalendarSettings();
  }

  private initTabs(): any {
    this.tabs = [
      {label: 'Основные данные', icon: 'fa-address-card-o', disabled: true },
      {label: 'Связанные документы', icon: 'fa-file-text-o', disabled: true}
    ];
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
