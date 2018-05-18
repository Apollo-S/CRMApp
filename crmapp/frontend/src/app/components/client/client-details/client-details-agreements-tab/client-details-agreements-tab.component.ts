import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ClientService } from '../../../../services/client.service';
import { Client } from '../../../../models/Client';
import { ClientAgreement } from '../../../../models/ClientAgreement';

@Component({
  selector: 'app-client-details-agreements-tab',
  templateUrl: './client-details-agreements-tab.component.html',
  styleUrls: ['./client-details-agreements-tab.component.css']
})
export class ClientDetailsAgreementsTabComponent implements OnInit, OnDestroy {
  private _propertySubscribtion: Subscription;
  columns: any[];
  agreements: ClientAgreement[] = [];
  client: Client = {};
  
  constructor(private service: ClientService) { }

  ngOnInit() { 
    this._propertySubscribtion = this.service.property$
      .subscribe(
        p => {
          this.client = p;
          this.getAgreementsByClientId(p.id);
        }
      );
    this.initColumns();
  }

  ngOnDestroy() {
    this._propertySubscribtion.unsubscribe();
  }

  private getAgreementsByClientId(id: number) {
    this.service.getAgreementsByClientId(id)
      .subscribe(
        agreements => this.agreements = agreements
      );
  }

  private initColumns(): void {
    this.columns = [
      { field: 'id', header: 'ID' },
      { field: 'number', header: 'Номер' },
      { field: 'dateStart', header: 'Актуален с' }      
    ];
  }

}
