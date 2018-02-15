import { Injectable } from '@angular/core';

@Injectable()
export class UtilService {

  constructor() { }

  getYears(): string {
    let countOfYears = 5;
    let currentYear = new Date().getFullYear();
    return ((currentYear - countOfYears).toString()) + ':' + currentYear.toString();
  }

}
