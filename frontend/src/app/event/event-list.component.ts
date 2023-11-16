// event-list.component.ts
import { Component, OnInit } from '@angular/core';
import { EventService } from './event.service';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.scss']
})
export class EventListComponent implements OnInit {
  events: any[] = [];

  constructor(private eventService: EventService) {}

  ngOnInit(): void {
    this.eventService.getEvents().subscribe((data: any) => {
      console.log(data);
      this.events = data;
    });
  }
}
