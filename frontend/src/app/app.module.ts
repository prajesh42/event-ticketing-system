import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthComponent } from './auth/auth.component';
import { EventListComponent } from './event-list/event-list.component';
import { TicketBookingComponent } from './ticket/ticket-booking.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { EventComponent } from './event/event.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CustomDatePipe } from './pipe/CustomDatePipe';
import { DatePipe } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    EventListComponent,
    TicketBookingComponent,
    EventComponent,
    CustomDatePipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
