import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { ClientAddress } from '../../../../../models/ClientAddress';
import { ClientService } from '../../../../../services/client.service';
import { ConfirmationService, Message } from 'primeng/api';

@Component({
  selector: 'app-edit-address',
  templateUrl: './edit-address.component.html',
  styleUrls: ['./edit-address.component.css']
})
export class EditAddressComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  msgs: Message[] = [];
  address: ClientAddress = {};
  addressId: number;
  clientId: number;  

  constructor(private service: ClientService, 
              private router: Router,
              private route: ActivatedRoute,
              private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.route.params
    .subscribe(
      (params: Params) => {
        this.addressId = +params['id'];
        }
      )
    this._propertySubscribtion = this.service.property$
      .subscribe(p => {
        this.clientId = p;
        }
      );
    this.getClientAddressById(this.addressId, this.clientId);
  }

  onSubmit() {
    this.update();
  }

  delete() {
    this.service.deleteAddress(this.addressId, this.clientId)
      .subscribe(response => {
        this.goBackToAddresses();
      })
  }  

  ngOnDestroy(): void {
    this._propertySubscribtion.unsubscribe();
  }

  private update(): void {
    this.service.updateAddress(this.address, this.clientId)
      .subscribe(response => {
        this.goBackToAddresses();
      })
  }

  private getClientAddressById(addressId: number, clientId: number) {
    this.service.getAddressById(addressId, clientId)
      .subscribe(address => {
        this.address = address;
        this.address.dateStart = new Date(this.address.dateStart);
      })
  }
  
  private goBackToAddresses() {
    this.router.navigate(['/clients', this.clientId, 'addresses']);
  } 

  confirmDeleting() {
    let msg  = 'Адрес с ID=' + this.addressId + ' был успешно удален';
    this.confirmationService.confirm({
        message: 'Действительно удалить адрес?',
        header: 'Удаление адреса',
        icon: 'fa fa-trash',
        accept: () => {
          this.delete();
          this.msgs = [{severity:'success', summary:'Успешно', detail: msg}];
        },
        reject: () => {}
    });
  }

}
