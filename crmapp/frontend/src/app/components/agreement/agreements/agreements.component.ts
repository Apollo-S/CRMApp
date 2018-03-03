import { Component, OnInit } from '@angular/core';
import { AgreementService } from '../../../services/agreement.service';
import { ClientAgreement } from '../../../models/ClientAgreement';
import { MenuItem } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-agreements',
  templateUrl: './agreements.component.html',
  styleUrls: ['./agreements.component.css']
})
export class AgreementsComponent implements OnInit {
  agreements: ClientAgreement[] = [];
  columns: any[];
  loading: boolean;
  items: MenuItem[];

  constructor(private service: AgreementService, 
              private router: Router) { }

  ngOnInit() {
    this.getAgreements();
    this.initColumns();
    this.loading = true;
    setTimeout(() => {
      this.loading = false;
    }, 500);
  }

  private getAgreements() {
    this.service.getAgreements()
      .subscribe(
        agreements => this.agreements = agreements
      );
  }

  private initColumns() {
    this.columns = [
      { field: 'clientAlias', header: 'Контрагент' },
      { field: 'number', header: 'Номер' },
      { field: "dateStart", header: 'Дата' }
    ];
  }

  goToAgreement(url: string) {
    this.router.navigate([url]);
  }

  initMenu(agreementId: string) {
    this.items = [
      { label: 'Связ. документы', icon: 'fa-file-text-o', routerLink: [agreementId, 'documents'] }
    ];
  }

}
