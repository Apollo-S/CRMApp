import { Component, OnInit, OnDestroy } from '@angular/core';
import { ClientService } from '../../../../../services/client.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ClientAddress } from '../../../../../models/ClientAddress';
import { Subscription } from 'rxjs';
import { Message } from 'primeng/api';

@Component({
  selector: 'app-add-address',
  templateUrl: './add-address.component.html',
  styleUrls: ['./add-address.component.css']
})
export class AddAddressComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  msgs: Message[] = [];
  address: ClientAddress = {};
  clientId: number;  

  constructor(private service: ClientService, 
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this._propertySubscribtion = this.service.property$
      .subscribe(p => {
        this.clientId = p;
        }
      );
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
    this.service.addAddress(this.address, this.clientId)
      .subscribe(response => {
        msg = 'Адрес успешно добавлен (ID=' + response.id + ')';
        this.msgs = [{severity:'success', summary:'Успешно', detail: msg}];
      });
  }

  private goBackToAddresses() {
    setTimeout((router) => {
      this.router.navigate(['/clients', this.clientId, 'addresses']);
    }, 1500);
  }

}
