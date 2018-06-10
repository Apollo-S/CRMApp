import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { ClientAddress } from '../../../../../models/ClientAddress';
import { ClientService } from '../../../../../services/client.service';
import { UtilService } from '../../../../../services/util.service';
import { ConfirmationService, Message } from 'primeng/api';
import { Client } from '../../../../../models/Client';

@Component({
  selector: 'app-edit-address',
  templateUrl: './edit-address.component.html',
  styleUrls: ['./edit-address.component.css']
})
export class EditAddressComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  msgs: Message[] = [];
  address: ClientAddress = {};
  client: Client = {};  
  years: string;
  ru: any;

  constructor(private service: ClientService, 
              private router: Router,
              private route: ActivatedRoute,
              private confirmationService: ConfirmationService) { }

  ngOnInit() {
    let addressId: number;
    this._propertySubscribtion = this.service.property$
      .subscribe(
        p => this.client = p
      );
    this.route.params
      .subscribe(
        (params: Params) => {
          addressId = +params['id'];
          this.getClientAddressById(addressId, this.client);
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
    this.goBackToAddresses();
  }

  confirmDeleting() {
    this.confirmationService.confirm({
      message: 'Действительно удалить адрес?',
      header: 'Удаление адреса',
      icon: 'fa fa-trash',
      accept: () => {
        let msg  = 'Адрес успешно удален (ID=' + this.address.id + ')';
        this.delete(msg);
        this.goBackToAddresses();
      },
      reject: () => {}
    });
  }

  private delete(msg: string) {
    this.service.deleteAddress(this.address.id, this.client)
      .subscribe(
        response => {
          this.msgs = [{severity:'success', summary:'Успешно', detail: msg}];
        }
      );
  }  

  private update(): void {
    this.service.updateAddress(this.address, this.client)
      .subscribe(
        response => {
          let msg = 'Адрес для ' + this.client.alias +  ' успешно обновлен (ID=' + response.id + ')';
          this.msgs = [{severity:'success', summary:'Успешно', detail: msg}];
        }
      );
  }

  private getClientAddressById(addressId: number, client: Client) {
    this.service.getAddressById(addressId, client)
      .subscribe(
        address => {
          this.address = address;
          this.address.dateStart = new Date(this.address.dateStart);
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
