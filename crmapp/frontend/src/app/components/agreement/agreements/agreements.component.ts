import { Component, OnInit } from '@angular/core';
import { AgreementService } from '../../../services/agreement.service';
import { Agreement } from '../../../models/Agreement';

@Component({
  selector: 'app-agreements',
  templateUrl: './agreements.component.html',
  styleUrls: ['./agreements.component.css']
})
export class AgreementsComponent implements OnInit {

  agreements: Agreement[];

    constructor(private agreementService: AgreementService) { }
  
    ngOnInit() {
      this.getAgreements();
    }
  
    getAgreements() {
      this.agreementService.getAgreements().then(agreements => this.agreements = agreements);
    }

}
