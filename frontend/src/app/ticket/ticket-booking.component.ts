import { Component } from '@angular/core';
import { TicketService } from './ticket.service';

@Component({
  selector: 'app-ticket-booking',
  templateUrl: './ticket-booking.component.html',
//   styleUrls: ['./ticket-booking.component.css']
})
export class TicketBookingComponent {
  constructor(private ticketService: TicketService) {}

  bookTicket(userId: string, eventId: string): void {
    this.ticketService.bookTicket(Number(userId), Number(eventId));
  }
}
