import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { EmployeeService } from '../../../../services/employee.service';
import { Vacation } from '../../../../models/Vacation';
import { Employee } from '../../../../models/Employee';
import { VacationService } from '../../../../services/vacation.service';

@Component({
  selector: 'app-employee-details-vacations-tab',
  templateUrl: './employee-details-vacations-tab.component.html',
  styleUrls: ['./employee-details-vacations-tab.component.css']
})
export class EmployeeDetailsVacationsTabComponent implements OnInit, OnDestroy {
  employee: Employee = {};
  private _propertySubscription: Subscription;
  vacations: Vacation[] = [];
  columns: any[] = [];

  constructor(private vacationService: VacationService,
              private employeeService: EmployeeService) { }

  ngOnInit() {
    this.initSubscription();
    this.initColumns();
  }

  ngOnDestroy() {
    this._propertySubscription.unsubscribe();
  }

  private initSubscription() {
    this._propertySubscription = this.employeeService.property$
      .subscribe(
        p => {
          this.employee = p;
          this.getVacationsByEmployeeId(p.id);
        }
      );
  }

  private getVacationsByEmployeeId(id: number) {
    this.vacationService.getVacationsByEmployeeId(id)
      .subscribe(
        vacations => this.vacations = vacations
      );
  }

  private initColumns(): void {
    this.columns = [
      { field: 'id', header: 'ID' },
      { field: 'description', header: 'Описание' },
      { field: 'fullPeriod', header: 'Период' },
      { field: 'daysAmount', header: 'Кол-во дней' },
    ];
  }

}
