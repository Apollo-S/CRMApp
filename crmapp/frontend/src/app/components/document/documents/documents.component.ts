import { Component, OnInit } from '@angular/core';
import { DocumentService } from '../../../services/document.service';
import { DocumentTypeService } from '../../../services/document-type.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Document } from '../../../models/Document';
import { DocumentStatus } from '../../../models/DocumentStatus';
import { DocumentType } from '../../../models/DocumentType';
import { DocumentStatusService } from '../../../services/document-status.service';
import { ClientService } from '../../../services/client.service';
import { MenuItem, SortEvent, SelectItem } from 'primeng/api';
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
  loadingCheck: boolean = false;
  defaultSortField: string = "id";
  defaultSortType: string = "asc";
  sortTypes: SelectItem[] = [];
  selectedSortType: string;
  selectedSortField: any;

  constructor(private docService: DocumentService,
              private docTypeService: DocumentTypeService,
              private docStatusService: DocumentStatusService,
              private clientService: ClientService,
              private router: Router) { }

  ngOnInit() {
    this.loadingCheck = true;
    this.initColumns();
    this.getDocumentTypes();
    this.getDocumentStatuses();
    this.getClients();
    // this.getDocuments();
    this.useFilter(this.defaultSortField, this.defaultSortType);
    this.loadingCheck = true;
    this.initSortTypes();
  }

  private getDocuments() {
    this.docService.getDocuments()
      .subscribe(
        documents => this.documents = documents
      );
  }

  useFilter(sortField: string, sortType: string) {
      var docTypeIDs: number[] = this.getIDs(this.selectedDocTypes);
      var docStatusIDs: number[] = this.getIDs(this.selectedDocStatuses);
      var clientIDs: number[] = this.getIDs(this.selectedClients);
      this.docService.getDocumentsAccordingFilter(docTypeIDs, docStatusIDs, clientIDs, sortField, sortType)
        .subscribe(
          documents => this.documents = documents
        );
  }

  useSorting() {
    var docTypeIDs: number[] = this.getIDs(this.selectedDocTypes);
      var docStatusIDs: number[] = this.getIDs(this.selectedDocStatuses);
      var clientIDs: number[] = this.getIDs(this.selectedClients);
      console.log("this.selectedSortField = " + this.selectedSortField.field);
      console.log("this.selectedSortType = " + this.selectedSortType);
      this.docService.getDocumentsAccordingFilter(docTypeIDs, docStatusIDs, clientIDs, this.selectedSortField.field, this.selectedSortType)
        .subscribe(
          documents => this.documents = documents
        );
  }

  customSort(event: SortEvent) {
    // event.data.sort((data1, data2) => {
        // let value1 = data1[event.field];
        // console.log("value1 = " + value1);
        // let value2 = data2[event.field];
        // console.log("column = " + event.field)
        // console.log("sort = " + event.order)
        var sortField = event.field;
        var sortType = (event.order == 1 ? "asc" : "desc");
        this.useFilter(sortField, sortType) 
        // let result = null;
        // return 0;
        // if (value1 == null && value2 != null)
        //     result = -1;
        // else if (value1 != null && value2 == null)
        //     result = 1;
        // else if (value1 == null && value2 == null)
        //     result = 0;
        // else if (typeof value1 === 'string' && typeof value2 === 'string')
        //     result = value1.localeCompare(value2);
        // else
        //     result = (value1 < value2) ? -1 : (value1 > value2) ? 1 : 0;

        // return (event.order * result);
    // });
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
    this.clientService.getClientsList()
  }

  private initColumns() {
    this.columns = [
      { field: 'id', header: 'ID', colStyle: 'text-align:center' },
      { field: 'docType.shortTitle', header: 'Тип документа', colStyle: 'text-align:center' },
      { field: 'number', header: '№', colStyle: 'text-align:center' },
      { field: 'amount', header: 'Сумма', colStyle: 'text-align:center' },
      { field: 'dated', header: 'Дата', colStyle: 'text-align:center' },
      { field: 'paymentDate', header: 'Дата оплаты', colStyle: 'paid' },
      { field: 'status.status', header: 'Статус', colStyle: 'text-align:center' }
    ];
    this.selectedSortField = this.columns[0];
  }

  private initSortTypes() {
    this.sortTypes = [
      { label:'Возрастание', value: 'asc'},
      { label:'Убывание', value: 'desc'}
    ];
    this.selectedSortType = 'asc';
  }
  
  private initMenu(id: any) {
    this.items = [];
  }

  goToEntry(url: string) {
    this.router.navigate([url]);
  }

  clearFilter() {
    this.selectedClients = [];
    this.selectedDocTypes = [];
    this.selectedDocStatuses = [];
  }

}
