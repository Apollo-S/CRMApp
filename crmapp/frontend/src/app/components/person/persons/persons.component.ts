import { Component, OnInit } from '@angular/core';
import { PersonService } from '../../../services/person.service';
import { Person } from '../../../models/Person';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-persons',
  templateUrl: './persons.component.html',
  styleUrls: ['./persons.component.css']
})
export class PersonsComponent implements OnInit {  
  persons: Person[] = [];
  columns: any[];
  items: MenuItem[];

  constructor(private service: PersonService) { }

  ngOnInit() {
    this.initColumns();
    this.getPersons();
    this.initMenu();
  }

  private getPersons(): any {
    this.service.getPersons()
      .subscribe(
        persons => this.persons = persons
      );
  }

  private initColumns(): any {
    this.columns = [
      { field: 'surname', header: 'Фамилия' },
      { field: 'firstname', header: 'Имя' },
      { field: 'lastname', header: 'Отчество' }
    ];
  }

  private initMenu() {
    this.items = [];
  }

}
