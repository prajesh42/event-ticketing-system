import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  private apiUrl = 'http://localhost:8080/tickets'; // Replace with your backend API URL

  constructor(private http: HttpClient) {}

  bookTicket(userId: number, eventId: number): void {
    // Implement ticket booking logic
  }
}
