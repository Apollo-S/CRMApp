import { Component, OnInit } from '@angular/core';
import { MenuItem, Message } from 'primeng/api';
import { Client } from '../../../models/Client';
import { ClientAgreement } from '../../../models/ClientAgreement';
import { UtilService } from '../../../services/util.service';
import { ClientService } from '../../../services/client.service';
import { AgreementService } from '../../../services/agreement.service';
import { Router } from '@angular/router';
import { FormControl, Validators, FormBuilder, FormGroup } from '@angular/forms';

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
  userform: FormGroup;
  years: string;
  ru: any;

  constructor(private clientService: ClientService,
              private agreementService: AgreementService,
              private fb: FormBuilder,
              private router: Router) { }

  ngOnInit() {
    this.initUserForm();
    this.initTabs();
    this.getClients();
    this.initCalendarSettings();
  }

  onSubmit() {
    this.save();
    this.goBackToAgreement(1500);
  }

  private initUserForm() {
    this.userform = this.fb.group({
      'number': new FormControl('eruteriutwieurw', Validators.compose([
        Validators.required,
        Validators.minLength(2)
      ])),
      'client': new FormControl('', Validators.compose([
        Validators.required
      ])),
      'dateStart': new FormControl('', Validators.compose([
        Validators.required,
      ])),
      'comment': new FormControl('')
    });
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
    this.ru = UtilService.getCalendarLocalSet();
    this.years = UtilService.getCalendarYears(5);
  }

  private save(): void {
    this.agreementService.addAgreement(this.agreement)
      .subscribe(
        response => {
          this.agreement = response;
          let msg = 'Договор №' + this.agreement.number +  ' успешно добавлен (ID=' + response.id + ')';
          this.msgs = [{severity:'success', summary:'Успешно!', detail: msg}];
        }
      );
  }

  private goBackToAgreement(timeMillis: number) {
    setTimeout(
      (router) => {
        this.router.navigate([this.agreement.url]);
      }, timeMillis);
  } 

}
