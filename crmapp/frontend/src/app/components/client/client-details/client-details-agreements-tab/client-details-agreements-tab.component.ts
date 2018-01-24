import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ClientAgreement } from '../../../../models/ClientAgreement';
import { ClientService } from '../../../../services/client.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-client-details-agreements-tab',
  templateUrl: './client-details-agreements-tab.component.html',
  styleUrls: ['./client-details-agreements-tab.component.css']
})
export class ClientDetailsAgreementsTabComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  agreements: ClientAgreement[];
  clientId: number;
  
  constructor(private service: ClientService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() { 
    this._propertySubscribtion = this.service.property$
    .subscribe(p => {
      this.clientId = p;
    });
    this.getAgreementsByClientId(this.clientId);
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  getAgreementsByClientId(clientId: number) {
    this.service.getAgreementsByClientId(clientId)
      .subscribe(agreements => this.agreements = agreements);
  }

}
