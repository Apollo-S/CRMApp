import { Component, OnInit } from '@angular/core';
import { VacationService } from '../../../services/vacation.service';
import { Vacation } from '../../../models/Vacation';
import { MenuItem } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vacations',
  templateUrl: './vacations.component.html',
  styleUrls: ['./vacations.component.css']
})
export class VacationsComponent implements OnInit {
  vacations: Vacation[] = [];
  columns: any[];
  items: MenuItem[];

  constructor(private vacationService: VacationService,
              private router: Router) { }

  ngOnInit() {
    this.initColumns();
    this.getVacations();
  }

  private getVacations(): any {
    this.vacationService.getVacations()
      .subscribe(
        vacations => this.vacations = vacations
      );
  }

  private initColumns() {
    this.columns = [
      { field: 'employeeShortName', header: 'Сотрудник' },
      { field: 'description', header: 'Описание' },
      { field: 'fullPeriod', header: 'Период' }
    ];
  }

  private initMenu(employeeId: any) {
    let url = '/employees/' + employeeId + '/vacations';
    this.items = [
      { label: 'К сотруднику', icon: '', routerLink: url }
    ];
  }

  goToEntry(url: string) {
    this.router.navigate([url]);
  }

}
