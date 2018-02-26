import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ClientService } from '../../../services/client.service';
import { Client } from '../../../models/Client';
import { Message, MenuItem } from 'primeng/api';

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css']
})
export class AddClientComponent implements OnInit {
  tabs: MenuItem[];
  msgs: Message[] = [];
  userform: FormGroup;
  client: Client = {};

  constructor(private service: ClientService, 
              private fb: FormBuilder,
              private router: Router) { }

  ngOnInit() {
    this.initUserForm();
    this.initTabs();
  }

  onSubmit() {
    this.save();
    this.goBackToClient(1500);
  }

  private initUserForm() {
    this.userform = this.fb.group({
      'title': new FormControl('', Validators.compose([
        Validators.required,
        Validators.minLength(2)
      ])),
      'alias': new FormControl('', Validators.compose([
        Validators.required,
        Validators.minLength(2)
      ])),
      'edrpou': new FormControl('', Validators.compose([
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(14)
      ])),
      'inn': new FormControl('', Validators.compose([
        Validators.maxLength(14)
      ])),
      'vatCertificate': new FormControl('')
    });
  }

  private initTabs() {
    this.tabs = [
      {label: 'Основные данные', icon: 'fa-address-card-o', disabled: true},
      {label: 'Адресы', icon: 'fa-building-o', disabled: true},
      {label: 'Банковские реквизиты', icon: 'fa-bank', disabled: true},
      {label: 'Руководители', icon: 'fa-user-o', disabled: true},
      {label: 'Договоры', icon: 'fa-file-text-o', disabled: true}
    ];
  }

  private save(): void {
    this.service.addClient(this.client)
      .subscribe(
        response => {
          this.client = response;
          let msg = 'Клиент ' + this.client.alias +  ' успешно добавлен (ID=' + response.id + ')';
          this.msgs = [{severity:'success', summary:'Успешно!', detail: msg}];
        }
      );
  }

  private goBackToClient(timeMillis: number) {
    setTimeout(
      (router) => {
        this.router.navigate([this.client.url]);
      }, timeMillis);
  } 

}
