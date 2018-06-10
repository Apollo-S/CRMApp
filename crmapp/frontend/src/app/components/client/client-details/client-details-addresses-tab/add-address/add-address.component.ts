import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ClientService } from '../../../../../services/client.service';
import { UtilService } from '../../../../../services/util.service';
import { ClientAddress } from '../../../../../models/ClientAddress';
import { Message } from 'primeng/api';
import { Client } from '../../../../../models/Client';

@Component({
  selector: 'app-add-address',
  templateUrl: './add-address.component.html',
  styleUrls: ['./add-address.component.css']
})
export class AddAddressComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  msgs: Message[] = [];
  address: ClientAddress = {};
  client: Client = {};  
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
    this.goBackToAddresses();
  }

  ngOnDestroy(): void {
    this._propertySubscribtion.unsubscribe();
  }

  private save(): void {
    let msg  = '';
    this.service.addAddress(this.address, this.client)
      .subscribe(
        response => {
          msg = 'Адрес для ' + this.client.alias +  ' успешно добавлен (ID=' + response.id + ')';
          this.msgs = [{severity:'success', summary:'Успешно', detail: msg}];
        }
      );
  }

  private goBackToAddresses() {
    setTimeout(
      (router) => {
        this.router.navigate([this.client.url, 'addresses']);
      }, 1500);
  }

}
