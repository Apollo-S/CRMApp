import { Component, OnInit, OnDestroy } from '@angular/core';
import { ClientService } from '../../../../../services/client.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ClientAddress } from '../../../../../models/ClientAddress';
import { Subscription } from 'rxjs';
import { ConfirmationService, Message } from 'primeng/api';
import { MessageService } from 'primeng/components/common/messageservice';

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
              private route: ActivatedRoute,
              private messageService: MessageService) { }

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
          this.messageService.add({severity:'success', summary:'Service Message', detail:'Via MessageService'});
          this.goBackToAddresses();
      });
  }

  private goBackToAddresses() {
    setTimeout((router) => {
      this.router.navigate(['/clients', this.clientId, 'addresses']);
    }, 1500);
  }

}
