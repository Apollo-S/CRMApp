import { Component, OnInit, OnDestroy } from '@angular/core';
import { ClientAgreement } from '../../../models/ClientAgreement';
import { Message } from 'primeng/api';
import { Subscription } from 'rxjs';
import { AgreementService } from '../../../services/agreement.service';
import { Router } from '@angular/router';
import { ClientService } from '../../../services/client.service';
import { UtilService } from '../../../services/util.service';
import { Client } from '../../../models/Client';
import { FormControl, Validators, FormBuilder, FormGroup } from '@angular/forms';

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
  userform: FormGroup;
  years: string;
  ru: any;

  constructor(private clientService: ClientService,
              private agreementService: AgreementService,
              private fb: FormBuilder,
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
    this.initUserForm();
    this.getClients();
    this.initCalendarSettings();
  }

  onSubmit() {
    this.update();
    this.goBackToAgreement(1500);
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  private initUserForm() {
    this.userform = this.fb.group({
      'number': new FormControl('', Validators.compose([
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

  private getClients() {
    this.clientService.getClientsList()
  }

  private initCalendarSettings() {
    this.ru = UtilService.getCalendarLocalSet();
    this.years = UtilService.getCalendarYears(5);
  }

  private update(): void {
    this.agreementService.updateAgreement(this.agreement)
      .subscribe(
        response => {
          this.agreement = response;
          let msg = 'Договор №' + this.agreement.number +  ' успешно изменен (ID=' + response.id + ')';
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
