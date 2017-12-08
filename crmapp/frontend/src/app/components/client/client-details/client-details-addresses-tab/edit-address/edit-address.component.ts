import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ClientAddress } from '../../../../../models/ClientAddress';
import { ClientService } from '../../../../../services/client.service';
import { FlashMessagesService } from 'angular2-flash-messages';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-edit-address',
  templateUrl: './edit-address.component.html',
  styleUrls: ['./edit-address.component.css']
})
export class EditAddressComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  address: ClientAddress = {};
  addressId: number;
  clientId: number;  

  constructor(private service: ClientService, 
              private flashMessagesService: FlashMessagesService,
              private router: Router,
              private route: ActivatedRoute) { }

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
    this.showMessage();
  }

  delete() {
    if (confirm("Удалить текущий адрес?")) {
      this.service.deleteAddress(this.addressId, this.clientId)
        .subscribe(response => {
          this.goBackToAddresses();
        });
    }; 
  }  

  ngOnDestroy(): void {
    this._propertySubscribtion.unsubscribe();
  }

  private update(): void {
    this.service.updateAddress(this.address, this.clientId)
      .subscribe(response => {
        this.router.navigate(['/clients', this.clientId, 'addresses']);
      })
  }

  private showMessage(): void {
    this.flashMessagesService.grayOut(true);
    this.flashMessagesService.show('Aдрес успешно обновлен', 
      {cssClass: 'alert-success text-center', timeout: 1500});
  }

  private getClientAddressById(addressId: number, clientId: number) {
    this.service.getAddressById(addressId, clientId)
      .then(address => this.address = address);
  }
  
  private goBackToAddresses() {
    this.router.navigate(['/clients', this.clientId, 'addresses']);
  } 

}
