import { Component, OnInit } from '@angular/core';
import { AgreementService } from '../../../services/agreement.service';
import { ClientAgreement } from '../../../models/ClientAgreement';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-agreements',
  templateUrl: './agreements.component.html',
  styleUrls: ['./agreements.component.css']
})
export class AgreementsComponent implements OnInit {
  agreements: ClientAgreement[] = [];
  columns: any[];
  items: MenuItem[];

  constructor(private service: AgreementService) { }

  ngOnInit() {
      this.initColumns();
      this.getAgreements();
      this.initMenu();
  }

  private getAgreements() {
    this.service.getAgreements()
      .subscribe(
        agreements => this.agreements = agreements
      );
  }

  private initColumns() {
    this.columns = [
      { field: 'clientAlias', header: 'Контрагент', filterBy: 'contains' },
      { field: 'number', header: 'Номер', filterBy: 'contains' },
      { field: "dateStart", header: 'Дата', filterBy: 'contains' }
    ];
  }

  private initMenu() {
    this.items = [
      { label: 'Связ. документы', icon: 'fa-file-text-o', title: 'documents' }
    ];
  }

}