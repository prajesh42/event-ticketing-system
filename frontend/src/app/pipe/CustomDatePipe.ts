import { Pipe, PipeTransform } from '@angular/core';
import { DatePipe } from '@angular/common';

@Pipe({
  name: 'customDate'
})
export class CustomDatePipe implements PipeTransform {

  constructor(private datePipe: DatePipe) {}

  transform(value: any, format: string = 'MMM yyyy', locale: string = 'en-US'): any {
    const date = new Date(value);

    const day = date.getDate();

    let dayWithSuffix: string;
    switch (day % 10) {
      case 1:
        dayWithSuffix = `${day}<sup>st</sup>`;
        break;
      case 2:
        dayWithSuffix = `${day}<sup>nd</sup>`;
        break;
      case 3:
        dayWithSuffix = `${day}<sup>rd</sup>`;
        break;
      default:
        dayWithSuffix = `${day}<sup>th</sup>`;
        break;
    }

    const formattedDate = this.datePipe.transform(date, format, locale);

    return `${dayWithSuffix} ${formattedDate}`;
  }
}
