import { Component, OnInit } from '@angular/core';
import { PersonService } from '../../../services/person.service';
import { Person } from '../../../models/Person';

@Component({
  selector: 'app-persons',
  templateUrl: './persons.component.html',
  styleUrls: ['./persons.component.css']
})
export class PersonsComponent implements OnInit {  
  persons: Person[] = [];
  columns: any[];

  constructor(private service: PersonService) { }

  ngOnInit() {
    this.getPersons();
    this.initColumns();
  }

  private getPersons(): any {
    this.service.getPersons()
      .subscribe(
        persons => this.persons = persons
      );
  }

  initColumns(): any {
    this.columns = [
      { field: 'surname', header: 'Фамилия' },
      { field: 'firstname', header: 'Имя' },
      { field: 'lastname', header: 'Отчество' }
    ];
  }

}
