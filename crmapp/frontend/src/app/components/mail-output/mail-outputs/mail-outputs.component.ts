import { Component, OnInit } from '@angular/core';
import { MailOutputService } from '../../../services/mail-output.service';
import { MailOutput } from '../../../models/MailOutput';
import { MenuItem } from 'primeng/api';
import { MailDocumentTypeService } from '../../../services/mail-document-type.service';
import { MailDocumentType } from '../../../models/MailDocumentType';
import { UtilService } from '../../../services/util.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-mail-outputs',
  templateUrl: './mail-outputs.component.html',
  styleUrls: ['./mail-outputs.component.css']
})
export class MailOutputsComponent implements OnInit {
  columns: any[] = [];
  items: MenuItem[] = [];
  outputs: MailOutput[] = [];
  docTypes: MailDocumentType[] = [];
  mailOutput: MailOutput = {};
  selectedMailOutput: MailOutput = {};
  newMailOutput: boolean = false;
  displayDialog: boolean = false;
  years: string;
  ru: any;
  disabledMailOutputNumber: boolean = false;
  headerText: string = "";

  constructor(private outputService: MailOutputService,
              private docTypeService: MailDocumentTypeService,
              private utilService: UtilService) { }

  ngOnInit() {
    this.initColumns();
    this.getOutputs();
    this.initMenu();
    this.getDocTypes();
    this.setCalendarSettings(5);
  }

  private setCalendarSettings(years: number) {
    this.ru = this.utilService.getCalendarLocalSet();
    this.years = this.utilService.getCalendarYears(years);
  }

  private getOutputs() {
    this.outputService.getMailOutputs()
      .subscribe(
        outputs => this.outputs = outputs
      );
  }

  private getDocTypes() {
    this.docTypeService.getMailDocumentTypes()
      .subscribe(
        docTypes => this.docTypes = docTypes
      );
  }

  private initColumns() {
    this.columns = [
      { field: 'id', header: 'ID' },
      { field: 'number', header: 'Номер' },
      { field: 'docTypeTitle', header: 'Тип' },
      { field: 'receiver', header: 'Получатель' },
      { field: 'dated', header: 'Дата' },
    ];
  }

  private initMenu() {
    this.items = [];
  }

  onRowSelect(event) {
    this.newMailOutput = false;
    this.disabledMailOutputNumber = false;
    this.mailOutput = this.cloneMailOutput(event.data);
    this.mailOutput.dated = new Date(this.mailOutput.dated);
    this.headerText = "Исх. документ № " + this.mailOutput.number + " от " + 
      (this.mailOutput.dated == null ? '-' : new DatePipe('en-US').transform(this.mailOutput.dated, 'dd.MM.y'));
    this.displayDialog = true;
  } 

  cloneMailOutput(mailOut: MailOutput): MailOutput {
    let mailOutput = {};
    for (let prop in mailOut) {
        mailOutput[prop] = mailOut[prop];
    }
    return mailOutput;
  }

  showDialogToAdd() {
    this.headerText = "Новый исх. документ";
    this.newMailOutput = true;
    this.mailOutput = {};
    this.mailOutput.dated = new Date();
    this.displayDialog = true;
  }

}