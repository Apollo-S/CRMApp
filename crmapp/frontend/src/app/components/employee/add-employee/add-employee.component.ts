import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EmployeeService } from '../../../services/employee.service';
import { UtilService } from '../../../services/util.service';
import { Employee } from '../../../models/Employee';
import { Message, MenuItem } from 'primeng/api';
import { Person } from '../../../models/Person';
import { PersonService } from '../../../services/person.service';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {
  persons: Person[] = [];
  person: Person = {};
  tabs: MenuItem[];
  msgs: Message[] = [];
  userform: FormGroup;
  employee: Employee = {};
  years: string;
  displayModal: boolean = false;
  ru: any;

  constructor(private employeeService: EmployeeService, 
              private personService: PersonService,
              private router: Router,
              private fb: FormBuilder) { }

  ngOnInit() {
    this.getPersons();
    // this.initUserForm();
    this.initTabs();
    this.ru = UtilService.getCalendarLocalSet();
    this.years = UtilService.getCalendarYears(5);
  }

  onSubmit() {
    this.save();
    this.goBackToEmployee(1500);
  }

  private getPersons() {
    this.personService.getPersons()
      .subscribe(
        persons => this.persons = persons
      );
  }

  private initUserForm() {
    this.userform = this.fb.group({
      'hireDate': new FormControl(''),
      // 'alias': new FormControl('', Validators.compose([
      //   Validators.required,
      //   Validators.minLength(2)
      // ])),
      // 'edrpou': new FormControl('', Validators.compose([
      //   Validators.required,
      //   Validators.minLength(6),
      //   Validators.maxLength(14)
      // ])),
      // 'inn': new FormControl('', Validators.compose([
      //   Validators.maxLength(14)
      // ])),
      // 'vatCertificate': new FormControl('')
    });
  }

  private initTabs() {
    this.tabs = [
      {label: 'Основные данные', icon: 'fa fa-address-card-o', disabled: true},
      {label: 'Адресы', icon: 'fa fa-building-o', disabled: true},
      {label: 'Банковские реквизиты', icon: 'fa fa-bank', disabled: true},
      {label: 'Отпуски / Больничные листы', icon: 'fa fa-user-o', disabled: true},
      {label: 'Документооборот', icon: 'fa fa-file-text-o', disabled: true}
    ];
  }

  private save(): void {
    this.employeeService.addEmployee(this.employee)
      .subscribe(
        response => {
          this.employee = response;
          let msg = 'Сотрудник ' +  ' успешно добавлен (ID=' + response.id + ')';
          this.msgs = [{severity:'success', summary:'Успешно!', detail: msg}];
        }
      );
  }

  private goBackToEmployee(timeMillis: number) {
    setTimeout(
      (router) => {
        this.router.navigate([this.employee.url]);
      }, timeMillis);
  } 

  showDialog() {
    // this.getPersons();
    this.displayModal = true;
  }

  choosePerson() {
    this.displayModal = false;
    // this.employee.person = this.person;
    console.log("person = " + this.employee.person.surname);
  }


}
