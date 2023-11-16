import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private apiUrl = '/events'; // Replace with your backend API URL

  constructor(private http: HttpClient) {}

  getEvents() {
    // Create headers with basic authorization
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Cache-Control': 'no-cache',
      'Authorization': 'Basic ' + btoa("eventuser" + ':' + "testuser")
    });

    // Include headers in the HTTP request
    return this.http.get<[]>(this.apiUrl, { headers});
  }
}
