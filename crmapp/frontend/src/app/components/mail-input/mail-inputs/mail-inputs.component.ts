import { Component, OnInit } from '@angular/core';
import { MenuItem, Message, ConfirmationService } from 'primeng/api';
import { MailInput } from '../../../models/MailInput';
import { MailDocumentType } from '../../../models/MailDocumentType';
import { MailInputService } from '../../../services/mail-input.service';
import { MailDocumentTypeService } from '../../../services/mail-document-type.service';
import { UtilService } from '../../../services/util.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-mail-inputs',
  templateUrl: './mail-inputs.component.html',
  styleUrls: ['./mail-inputs.component.css']
})
export class MailInputsComponent implements OnInit {
  columns: any[] = [];
  items: MenuItem[] = [];
  inputs: MailInput[] = [];
  docTypes: MailDocumentType[] = [];
  mailInput: MailInput = {};
  selectedMailInput: MailInput;
  newMailInput: boolean = false;
  displayDialog: boolean = false;
  years: string;
  ru: any;
  disabledMailInputNumber: boolean = false;
  headerText: string;
  msgs: Message[] = [];
  
  constructor(private inputService: MailInputService,
              private docTypeService: MailDocumentTypeService,
              private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.initColumns();
    this.getInputs();
    this.getDocTypes();
    this.setCalendarSettings(5);
  }

  private initColumns() {
    this.columns = [
      { field: 'id', header: 'ID' },
      { field: 'number', header: 'Номер' },
      { field: 'docTypeTitle', header: 'Тип' },
      { field: 'sender', header: 'Отправитель' },
      { field: 'dated', header: 'Дата' },
    ];
  }

  private getInputs() {
    this.inputService.getMailInputs()
      .subscribe(
        inputs => this.inputs = inputs
      );
  }

  private getDocTypes() {
    this.docTypeService.getMailDocumentTypes()
      .subscribe(
        docTypes => this.docTypes = docTypes
      );
  }

  private setCalendarSettings(years: number) {
    this.ru = UtilService.getCalendarLocalSet();
    this.years = UtilService.getCalendarYears(years);
  }

  showDialog(mailIn: MailInput) {
    this.newMailInput = false;
    this.disabledMailInputNumber = false;
    this.mailInput = this.cloneMailInput(mailIn);
    this.mailInput.dated = new Date(this.mailInput.dated);
    this.headerText = "Вход. документ № " + this.mailInput.number + " от " + 
      (this.mailInput.dated == null ? '-' : new DatePipe('en-US').transform(this.mailInput.dated, 'dd.MM.y'));
    this.displayDialog = true;
  }

  private cloneMailInput(mailIn: MailInput): MailInput {
    let mailInput = {};
    for (let prop in mailIn) {
        mailInput[prop] = mailIn[prop];
    }
    return mailInput;
  }

  showDialogToAdd() {
    this.headerText = "Новый вход. документ";
    this.newMailInput = true;
    this.mailInput = {};
    this.mailInput.dated = new Date();
    this.displayDialog = true;
  }

  saveOrUpdate() {
    let msg = "Вход. документ ";
    if (this.newMailInput) {
      msg = msg + " введен";
      this.inputService.addMailInput(this.mailInput)
        .subscribe(() => {
          this.getInputs();
        }
      );
    } else {
      msg = msg + this.mailInput.number + " обновлен";
      this.inputService.updateMailInput(this.mailInput)
        .subscribe(() => {
          this.getInputs();
        }
      );
    }
    this.displayDialog = false;
    this.msgs = [{severity:'success', summary:'Успешно', detail: msg}];
  }

  confirmDeleting() {
    let msg  = 'Вход. документ \"' + this.mailInput.number +  
      '(ID=' + this.mailInput.id + ')\" успешно удален';
    this.confirmationService.confirm({
      message: 'Действительно удалить вход. документ?',
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
    this.inputService.deleteMailInput(this.mailInput)
      .subscribe(
        () => {
          this.getInputs();
          this.displayDialog = false;
        }
      );
  }

}
