import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthComponent } from './auth/auth.component';
import { EventListComponent } from './event-list/event-list.component';
import { TicketBookingComponent } from './ticket/ticket-booking.component';
import { EventComponent } from './event/event.component';

const routes: Routes = [
  { path: 'auth', component: AuthComponent },
  { path: 'event-list', component: EventListComponent },
  { path: 'event/:id', component: EventComponent },
  { path: 'book-ticket', component: TicketBookingComponent },
  { path: '', redirectTo: '/event-list', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
