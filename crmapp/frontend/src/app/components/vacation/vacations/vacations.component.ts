import { Component, OnInit } from '@angular/core';
import { VacationService } from '../../../services/vacation.service';
import { Vacation } from '../../../models/Vacation';
import { MenuItem, ConfirmationService, Message } from 'primeng/api';
import { Router } from '@angular/router';
import { UtilService } from '../../../services/util.service';
import { DatePipe } from '@angular/common';
import { EmployeeService } from '../../../services/employee.service';
import { Employee } from '../../../models/Employee';

@Component({
  selector: 'app-vacations',
  templateUrl: './vacations.component.html',
  styleUrls: ['./vacations.component.css']
})
export class VacationsComponent implements OnInit {
  ru: any;
  years: string;
  vacations: Vacation[] = [];
  employees: Employee[] = [];
  columns: any[];
  menuModel: MenuItem[] = [];
  loading: boolean; 
  vacation: Vacation = {};
  selectedItem: Vacation;
  newVacation: boolean = false;
  displayDialog: boolean = false;
  headerText: string;
  msgs: Message[] = [];
  daysCount: number = 0;

  constructor(private vacationService: VacationService,
              private employeeService: EmployeeService,
              private router: Router,
              private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.loading = true;
    this.initColumns();
    setTimeout(() => {
      this.loading = false;
    }, 700);
    this.getEmployees();
    this.getVacations();
    this.setCalendarSettings(5)
  }

  private getVacations(): any {
    this.vacationService.getVacations()
      .subscribe(
        vacations => this.vacations = vacations
      );
  }

  private getEmployees(): any {
    this.employeeService.getEmployees()
      .subscribe(
        employees => this.employees = employees
      );
  }

  private getEmployeeById(employeeId: number): any {
    this.employeeService.getEmployeeById(employeeId)
      .subscribe(
        employee => this.vacation.employee = employee
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
    this.menuModel = [
      { label: 'К сотруднику', icon: '', routerLink: url }
    ];
  }

  private setCalendarSettings(years: number) {
    this.ru = UtilService.getCalendarLocalSet();
    this.years = UtilService.getCalendarYears(years);
  }

  updateHeaderAndCalcDates() {
    this.calcDifferenceBetweenDates();
    this.updateHeaderText();
  }

  updateHeaderAndCalcDatesWithEvent() {
    this.vacation.dateFinal = UtilService.addDaysToDate(this.vacation.dateStart, this.vacation.daysAmount-1);
    this.updateHeaderText();
  }

  private calcDifferenceBetweenDates() {
    this.vacation.daysAmount = UtilService.calcDifferenceBetweenDates(this.vacation.dateFinal, this.vacation.dateStart);
    ++this.vacation.daysAmount;
  }

  private updateHeaderText() {
    this.headerText = this.vacation.employeeShortName + " | Отпуск за период: " + 
        this.vacation.fullPeriod + " | " + this.vacation.daysAmount + "дн.";
  }

  showDialog(vacation: Vacation) {
    this.newVacation = false;
    this.vacation = this.cloneVacation(vacation);
    this.vacation.dateStart = new Date(this.vacation.dateStart);
    this.vacation.dateFinal = new Date(this.vacation.dateFinal);
    this.getEmployeeById(this.vacation.employeeId);
    this.updateHeaderAndCalcDates();
    this.displayDialog = true;
  }

  private cloneVacation(vacation: Vacation): Vacation {
    let newVacation = {};
    for (let prop in vacation) {
      newVacation[prop] = vacation[prop];
    }
    return newVacation;
  }

  showDialogToAdd() {
    let newDate: Date = new Date();
    this.headerText = "Добавление отпуска";
    this.newVacation = true;
    this.vacation = {};
    this.vacation.dateStart = newDate;
    this.vacation.dateFinal = newDate;
    this.displayDialog = true;
  }

  saveOrUpdate() {
    let msg = "Отпуск ";
    if (this.newVacation) {
      msg = msg + " добавлен в кол-ве " + this.vacation.daysAmount + "дн.";
      this.vacationService.addVacation(this.vacation)
        .subscribe(() => {
          this.getVacations();
        }
      );
    } else {
      msg = msg + "за период " + this.vacation.fullPeriod + " обновлен";
      this.vacationService.updateVacation(this.vacation)
        .subscribe(() => {
          this.getVacations();
        }
      );
    }
    this.displayDialog = false;
    this.msgs = [{severity:'success', summary:'Успешно', detail: msg}];
  }

  confirmDeleting() {
    let msg  = 'Отпуск (ID=' + this.vacation.id + ') успешно удален';
    this.confirmationService.confirm({
      message: 'Действительно удалить отпуск?',
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
    this.vacationService.deleteVacation(this.vacation)
      .subscribe(
        () => {
          this.getVacations();
          this.displayDialog = false;
        }
      );
  }

  goToEntry(url: string) {
    this.router.navigate([url]);
  }

}
