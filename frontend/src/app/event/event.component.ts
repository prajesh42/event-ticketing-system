import { Component, OnInit } from '@angular/core';
import { EventService } from '../event-list/event.service';
import { ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.scss']
})
export class EventComponent implements OnInit {
  event:any;
  image:any;
  imageFolderPath = '../../assets/image/';

  constructor(private eventService: EventService, private activatedRoute: ActivatedRoute){
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      (params:Params)=> {
        console.log(params['id']);

        if(params['id']){
          this.getEventById(params['id']);
        }
      }
    )
    this.image=this.imageFolderPath+"image1.jpg";
  }

  getEventById(id:number){
    this.eventService.getEventById(id).subscribe(
      data=>{
        this.event=data;
      },
      error=>{
       console.log(error);
      }
    );
  }
}
