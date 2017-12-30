import { Component, OnInit } from '@angular/core';
import { DocumentService } from '../../../services/document.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Document } from '../../../models/Document';

@Component({
  selector: 'app-documents',
  templateUrl: './documents.component.html',
  styleUrls: ['./documents.component.css']
})
export class DocumentsComponent implements OnInit {
  documents: Document[];
  key: string = 'dated';
  reverse: boolean = true;

  constructor(private service: DocumentService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.getDocuments();
  }

  private getDocuments() {
    this.service.getDocuments()
      .subscribe(documents => {
        this.documents = documents
      }
    );
  }

  sort(key){
    console.log(key);
    this.key = key;
    this.reverse = !this.reverse;
  }

}
