import { Injectable } from '@angular/core';

@Injectable()
export class UtilService {

  constructor() { }

  static getCalendarYears(countOfYears: number): string {
    let currentYear = new Date().getFullYear();
    return ((currentYear - countOfYears).toString()) + ':' + currentYear.toString();
  }

  static getCalendarLocalSet(): any  {
    return {
      firstDayOfWeek: 1,
      dayNames: ["Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"],
      dayNamesShort: ["Вос", "Пон", "Втор", "Ср", "Чет", "Пят", "Суб"],
      dayNamesMin: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"],
      monthNames: [ "Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь" ],
      monthNamesShort: [ "Янв", "Фев", "Мар", "Апр", "Май", "Июн","Июл", "Авг", "Сен", "Окт", "Ноя", "Дек" ],
      today: 'Сегодня',
      clear: 'Очистить'
    };
  }

  static calcDifferenceBetweenDates(dateOne: Date, dateTwo: Date): number {
    let result: number = 0;
    result = (new Date(dateOne).getTime() - new Date(dateTwo).getTime());
    result = result / (1000 * 3600 * 24);
    return Math.ceil(result);
  }

  static addDaysToDate(date: Date, daysQuantity: number): Date {
    let result: number = daysQuantity * (1000 * 3600 * 24);
    result = result + new Date(date).getTime();
    return new Date(result);
  }

}
