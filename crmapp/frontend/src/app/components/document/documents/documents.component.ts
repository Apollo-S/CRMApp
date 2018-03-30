import { Component, OnInit } from '@angular/core';
import { DocumentService } from '../../../services/document.service';
import { DocumentTypeService } from '../../../services/document-type.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Document } from '../../../models/Document';
import { DocumentStatus } from '../../../models/DocumentStatus';
import { DocumentType } from '../../../models/DocumentType';
import { DocumentStatusService } from '../../../services/document-status.service';
import { ClientService } from '../../../services/client.service';
import { MenuItem } from 'primeng/api';
import { Client } from '../../../models/Client';

@Component({
  selector: 'app-documents',
  templateUrl: './documents.component.html',
  styleUrls: ['./documents.component.css']
})
export class DocumentsComponent implements OnInit {
  documents: Document[] = [];
  docTypes: DocumentType[] = [];
  docStatuses: DocumentStatus[] = [];
  clients: Client[] = [];
  columns: any[] = [];
  items: MenuItem[] = [];
  showFilter: boolean = false;
  selectedDocTypes: DocumentType[] = [];
  selectedDocStatuses: DocumentStatus[] = [];
  selectedClients: Client[] = [];

  constructor(private docService: DocumentService,
              private docTypeService: DocumentTypeService,
              private docStatusService: DocumentStatusService,
              private clientService: ClientService,
              private router: Router) { }

  ngOnInit() {
    this.initColumns();
    this.getDocumentTypes();
    this.getDocumentStatuses();
    this.getClients();
    this.getDocuments();
  }

  private getDocuments() {
    this.docService.getDocuments()
      .subscribe(
        documents => this.documents = documents
      );
  }

  getDocumentsAccordingFilter() {
    if (this.selectedDocTypes.length == 0 && this.selectedDocStatuses.length == 0 && this.selectedClients.length == 0) {
      this.getDocuments();
    } else {
      var docTypeIDs: number[] = this.getIDs(this.selectedDocTypes);
      var docStatusIDs: number[] = this.getIDs(this.selectedDocStatuses);
      var clientIDs: number[] = this.getIDs(this.selectedClients);
      this.docService.getDocumentsAccordingFilter(docTypeIDs, docStatusIDs, clientIDs)
        .subscribe(
          documents => this.documents = documents
        );
    }
  }

  private getIDs(sourceArray: any[]): number[] {
    let arrLength: number = sourceArray.length;
    let result: number[] = [arrLength];
    for (let index = 0; index < arrLength; index++) {
      result[index] = sourceArray[index].id;
    }
    return result;
  }

  private getDocumentTypes() {
    this.docTypeService.getDocumentTypes()
      .subscribe(
        docTypes => this.docTypes = docTypes
      );
  }

  private getDocumentStatuses() {
    this.docStatusService.getDocumentStatuses()
      .subscribe(
        docStatuses => this.docStatuses = docStatuses
      );
  }

  private getClients() {
    this.clientService.getClients()
      .subscribe(
        clients => this.clients = clients
      );
  }

  private initColumns() {
    this.columns = [
      { field: 'docTypeShortTitle', header: 'Тип документа', colStyle: 'text-align:center' },
      { field: 'number', header: '№', colStyle: 'text-align:center' },
      { field: 'amount', header: 'Сумма', colStyle: 'text-align:center' },
      { field: 'dated', header: 'Дата', colStyle: 'text-align:center' },
      { field: 'paymentDate', header: 'Дата оплаты', colStyle: 'paid' },
      { field: 'docStatus', header: 'Статус', colStyle: 'text-align:center' }
    ];
  }

  private initMenu(id: any) {
    let url = '/employees/' + id + '/vacations';
    this.items = [];
  }

  goToEntry(url: string) {
    this.router.navigate([url]);
  }

}
