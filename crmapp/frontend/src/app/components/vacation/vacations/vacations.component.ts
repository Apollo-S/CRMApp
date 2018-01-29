import { Component, OnInit } from '@angular/core';
import { VacationService } from '../../../services/vacation.service';
import { Vacation } from '../../../models/Vacation';

@Component({
  selector: 'app-vacations',
  templateUrl: './vacations.component.html',
  styleUrls: ['./vacations.component.css']
})

export class VacationsComponent implements OnInit {
  vacations: Array<Vacation> = [];

  constructor(private vacationService: VacationService) { }

  ngOnInit() {
    this.getVacations();
  }

  private getVacations() {
    this.vacationService.getVacations()
      .subscribe(vacations => this.vacations = vacations);
  }

}
