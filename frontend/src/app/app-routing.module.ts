import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthComponent } from './auth/auth.component';
import { EventListComponent } from './event/event-list.component';
import { TicketBookingComponent } from './ticket/ticket-booking.component';

const routes: Routes = [
  { path: 'auth', component: AuthComponent },
  { path: 'event', component: EventListComponent },
  { path: 'book-ticket', component: TicketBookingComponent },
  { path: '', redirectTo: '/event', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
