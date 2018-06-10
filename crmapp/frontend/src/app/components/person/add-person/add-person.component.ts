import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PersonService } from '../../../services/person.service';
import { Person } from '../../../models/Person';
import { Message } from 'primeng/api';
import { UtilService } from '../../../services/util.service';

@Component({
  selector: 'app-add-person',
  templateUrl: './add-person.component.html',
  styleUrls: ['./add-person.component.css']
})
export class AddPersonComponent implements OnInit {
  person: Person = {};
  msgs: Message[] = [];
  userform: FormGroup;
  years: string;
  ru: any;

  constructor(private service: PersonService,
              private fb: FormBuilder,
              private router: Router) { }

  ngOnInit() {
    this.initUserForm();
    this.ru = UtilService.getCalendarLocalSet();
    this.years = UtilService.getCalendarYears(100);
  }

  onSubmit() {
    this.save();
    this.goBackToPerson(1500);
  }

  private initUserForm(): any {
    this.userform = this.fb.group({
      'surname': new FormControl('', Validators.compose([
        Validators.required,
        Validators.minLength(1)
      ])),
      'firstname': new FormControl('', Validators.compose([
        Validators.required,
        Validators.minLength(1)
      ])),
      'lastname': new FormControl(''),
      'shortName': new FormControl('', Validators.compose([
        Validators.required,
        Validators.minLength(1)
      ])),
      'inn': new FormControl('', Validators.compose([
        Validators.minLength(1),
        Validators.maxLength(10),
      ])),
      'birthDate': new FormControl(''),

    });
  }

  private save(): void {
    this.service.addPerson(this.person)
      .subscribe(
        response => {
          this.person = response;
          let msg = 'Физ. лицо ' + this.person.shortName +  ' успешно добавлено (ID=' + response.id + ')';
          this.msgs = [{severity:'success', summary:'Успешно!', detail: msg}];
        }
      );
  }

  private goBackToPerson(timeMillis: number) {
    setTimeout(
      (router) => {
        this.router.navigate([this.person.url]);
      }, timeMillis);
  } 

}
