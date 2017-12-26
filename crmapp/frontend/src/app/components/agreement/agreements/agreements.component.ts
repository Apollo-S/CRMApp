import { Component, OnInit } from '@angular/core';
import { AgreementService } from '../../../services/agreement.service';
import { ClientAgreement } from '../../../models/ClientAgreement';

@Component({
  selector: 'app-agreements',
  templateUrl: './agreements.component.html',
  styleUrls: ['./agreements.component.css']
})
export class AgreementsComponent implements OnInit {

  agreements: ClientAgreement[];

  constructor(private agreementService: AgreementService) { }

  ngOnInit() {
    this.getAgreements();
  }

  getAgreements() {
    this.agreementService.getAgreements()
      .subscribe(agreements => {
        this.agreements = agreements
      });
  }

}
