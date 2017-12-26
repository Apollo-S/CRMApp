import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ClientAgreement } from '../../../../models/ClientAgreement';
import { AgreementService } from '../../../../services/agreement.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Document } from '../../../../models/Document';

@Component({
  selector: 'app-agreement-details-documents-tab',
  templateUrl: './agreement-details-documents-tab.component.html',
  styleUrls: ['./agreement-details-documents-tab.component.css']
})
export class AgreementDetailsDocumentsTabComponent implements OnInit {
  private _agreementIdSubscribtion: Subscription;
  // agreement: ClientAgreement = {};
  agreementId: number;
  documents: Document[];

  constructor(private service: AgreementService,
              private router: Router,
              private route: ActivatedRoute)  { }

    ngOnInit() { 
      this._agreementIdSubscribtion = this.service.property$
        .subscribe(p => {
          this.agreementId = p;
          console.log("Agreement ID = " + this.agreementId);
        }
      );
      this.getDocumentsByAgreementId(this.agreementId);
    }
  
    ngOnDestroy() {
      this._agreementIdSubscribtion.unsubscribe();
    }
  
    getDocumentsByAgreementId(agreementId: number) {
      this.service.getDocumentsByAgreementId(agreementId)
        .subscribe(documents => this.documents = documents);
    }

}
