import { Component, OnInit } from '@angular/core';
import { ClientAgreement } from '../../../models/ClientAgreement';
import { AgreementService } from '../../../services/agreement.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { OnDestroy } from '@angular/core/src/metadata/lifecycle_hooks';

@Component({
  selector: 'app-agreement-details',
  templateUrl: './agreement-details.component.html',
  styleUrls: ['./agreement-details.component.css']
})
export class AgreementDetailsComponent implements OnInit, OnDestroy {
  private _agreementIdSubscribtion: Subscription;
  agreement: ClientAgreement = {};
  agreementId: number;

  constructor(private service: AgreementService,
              private router: Router,
              private route: ActivatedRoute)  { }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.agreementId = +params['id'];
        }
      )
    this.service.property = this.agreementId;
    this._agreementIdSubscribtion = this.service.property$
      .subscribe(
        p => {
        this.agreementId = p;
        }
      )
    this.getAgreementById(this.agreementId);
  }

  private getAgreementById(id: number) {
    this.service.getAgreementById(id)
      .subscribe(agreement => this.agreement = agreement);
  }

  ngOnDestroy() {
    this._agreementIdSubscribtion.unsubscribe();
  }

}
