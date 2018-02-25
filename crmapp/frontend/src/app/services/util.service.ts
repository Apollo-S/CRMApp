import { Injectable } from '@angular/core';

@Injectable()
export class UtilService {

  constructor() { }

  getCalendarYears(countOfYears: number): string {
    let currentYear = new Date().getFullYear();
    return ((currentYear - countOfYears).toString()) + ':' + currentYear.toString();
  }

  getCalendarLocalSet(): any  {
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

}
