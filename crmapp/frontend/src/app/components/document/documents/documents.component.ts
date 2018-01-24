import { Component, OnInit } from '@angular/core';
import { DocumentService } from '../../../services/document.service';
import { DocumentTypeService } from '../../../services/document-type.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Document } from '../../../models/Document';
import { DocumentStatus } from '../../../models/DocumentStatus';
import { DocumentType } from '../../../models/DocumentType';
import { DocumentStatusService } from '../../../services/document-status.service';

@Component({
  selector: 'app-documents',
  templateUrl: './documents.component.html',
  styleUrls: ['./documents.component.css']
})
export class DocumentsComponent implements OnInit {
  documents: Document[];
  docTypes: DocumentType[];
  docStatuses: DocumentStatus[];
  sortF: string;
  key: string = 'dated';
  reverse: boolean = true;

  constructor(private docService: DocumentService,
              private docTypeService: DocumentTypeService,
              private docStatusService: DocumentStatusService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.getDocumentTypes();
    this.getDocumentStatuses();
    this.getDocuments();
  }

  private getDocuments() {
    this.docService.getDocuments()
      .subscribe(documents => this.documents = documents);
  }

  private getDocumentTypes() {
    this.docTypeService.getDocumentTypes()
      .subscribe(docTypes => this.docTypes = docTypes);
  }

  private getDocumentStatuses() {
    this.docStatusService.getDocumentStatuses()
      .subscribe(docStatuses => this.docStatuses = docStatuses);
  }

  sort(key){
    console.log(key);
    this.key = key;
    this.reverse = !this.reverse;
  }

  changeSort(event) {
    if (!event.order) {
      this.sortF = 'dated';
    } else {
      this.sortF = event.field;
    }
  }

}
