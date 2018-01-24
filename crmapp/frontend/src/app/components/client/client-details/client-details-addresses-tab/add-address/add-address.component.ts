import { Component, OnInit, OnDestroy } from '@angular/core';
import { ClientService } from '../../../../../services/client.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ClientAddress } from '../../../../../models/ClientAddress';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-add-address',
  templateUrl: './add-address.component.html',
  styleUrls: ['./add-address.component.css']
})
export class AddAddressComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
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
  }

  ngOnDestroy(): void {
    this._propertySubscribtion.unsubscribe();
  }

  private save(): void {
    this.service.addAddress(this.address, this.clientId)
      .subscribe(response => {
          this.router.navigate(['/clients', this.clientId, 'addresses']);
        }
      );
  }

}
