import { Component, OnInit, OnDestroy } from '@angular/core';
import { ClientAgreement } from '../../../models/ClientAgreement';
import { AgreementService } from '../../../services/agreement.service';
import { ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-agreement-details',
  templateUrl: './agreement-details.component.html',
  styleUrls: ['./agreement-details.component.css']
})
export class AgreementDetailsComponent implements OnInit, OnDestroy {
  private _agreementSubscribtion: Subscription;
  agreement: ClientAgreement = {};

  constructor(private service: AgreementService,
              private route: ActivatedRoute)  { }

  ngOnInit() {
    let agreementId: number;
    this.route.params
      .subscribe(
        (params: Params) => {
          agreementId = +params['id'];
          this.getAgreementById(agreementId);
        }
      );
  }

  private getAgreementById(id: number) {
    this.service.getAgreementById(id)
      .subscribe(
        agreement => {
          this.agreement = agreement;
          this.service.property = this.agreement;
          this._agreementSubscribtion = this.service.property$
            .subscribe(
              p => this.agreement = p
            );
        }
      );
  }

  ngOnDestroy() {
    this._agreementSubscribtion.unsubscribe();
  }

}
