import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { ClientAddress } from '../../../../../models/ClientAddress';
import { ClientService } from '../../../../../services/client.service';

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

  private getClientAddressById(addressId: number, clientId: number) {
    this.service.getAddressById(addressId, clientId)
      .subscribe(address => this.address = address);
  }
  
  private goBackToAddresses() {
    this.router.navigate(['/clients', this.clientId, 'addresses']);
  } 

}
