import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { MailOutputService } from '../../../services/mail-output.service';
import { MailOutput } from '../../../models/MailOutput';
import { MenuItem, ConfirmationService, Message } from 'primeng/api';
import { MailDocumentTypeService } from '../../../services/mail-document-type.service';
import { MailDocumentType } from '../../../models/MailDocumentType';
import { UtilService } from '../../../services/util.service';

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
  headerText: string;
  msgs: Message[] = [];
  numberSecondPart: string;

  constructor(private outputService: MailOutputService,
              private docTypeService: MailDocumentTypeService,
              private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.initColumns();
    this.getOutputs();
    this.initMenu();
    this.getDocTypes();
    this.setCalendarSettings(5);
  }

  private setCalendarSettings(years: number) {
    this.ru = UtilService.getCalendarLocalSet();
    this.years = UtilService.getCalendarYears(years);
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
    this.numberSecondPart = "/" + new DatePipe('en-US').transform(this.mailOutput.dated, 'MM') + "-" + 
      new DatePipe('en-US').transform(this.mailOutput.dated, 'y');
    this.headerText = "Исх. документ № " + this.mailOutput.number + this.numberSecondPart + " от " + 
      (this.mailOutput.dated == null ? '-' : new DatePipe('en-US').transform(this.mailOutput.dated, 'dd.MM.y'));
    this.displayDialog = true;
  } 

  private cloneMailOutput(mailOut: MailOutput): MailOutput {
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

  saveOrUpdate() {
    let msg = "Исх. документ ";
    if (this.newMailOutput) {
      msg = msg + " введен";
      this.outputService.addMailOutput(this.mailOutput)
        .subscribe(() => {
          this.getOutputs();
        }
      );
    } else {
      msg = msg + this.mailOutput.number + this.numberSecondPart + " обновлен";
      this.outputService.updateMailOutput(this.mailOutput)
        .subscribe(() => {
          this.getOutputs();
        }
      );
    }
    this.displayDialog = false;
    this.msgs = [{severity:'success', summary:'Успешно', detail: msg}];
  }

  confirmDeleting() {
    let msg  = 'Исх. документ \"' + this.mailOutput.number + this.numberSecondPart + 
      '(ID=' + this.mailOutput.id + ')\" успешно удален';
    this.confirmationService.confirm({
      message: 'Действительно удалить исх. документ?',
      header: 'Удаление объекта',
      icon: 'fa fa-trash',
      accept: () => {
        this.delete();
        this.msgs = [{severity:'success', summary:'Успешно', detail: msg}];
      },
      reject: () => {}
    });
  }

  private delete(): void {
    this.outputService.deleteMailOutput(this.mailOutput)
      .subscribe(
        () => {
          this.getOutputs();
          this.displayDialog = false;
        }
      );
  }

}
