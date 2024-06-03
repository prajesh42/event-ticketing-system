import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient) {}

  getEvents() {
    // Create headers with basic authorization
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Cache-Control': 'no-cache'
    });

    // Include headers in the HTTP request
    return this.http.get<[]>("/events", { headers});
  }

  getEventById(id:number) {
    // Create headers with basic authorization
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Cache-Control': 'no-cache'
    });

    // Include headers in the HTTP request
    return this.http.get<[]>("/events/"+id, { headers});
  }
}
