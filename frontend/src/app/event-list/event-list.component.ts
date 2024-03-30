import { Component, OnInit } from '@angular/core';
import { EventService } from './event.service';
import { Router } from '@angular/router';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.scss']
})
export class EventListComponent implements OnInit {
  events: any[] = [];
  imageFilenames = ['image1.jpg', 'image2.jpg', 'image3.jpg', 'image4.jpg', 'image5.jpg', 'image6.jpg'];
  imageFolderPath = '../../assets/image/';
  slides:any[]=[];

  constructor(private eventService: EventService, private route: Router, private config: NgbCarouselConfig) {
    config.interval = 5000;  
    config.wrap = true;  
    config.keyboard = false;  
    config.pauseOnHover = false; 
    this.slides=this.getSlides();
  }

  ngOnInit(): void {
    this.eventService.getEvents().subscribe((data: any) => {
      this.events = data;
    });
  }

  getEventById(id:number){
    this.route.navigate(['event',id]);
  }

  getSlides() {
    return this.imageFilenames.map(filename => ({
      imageSrc: this.imageFolderPath + filename,
      alt: 'Sample image ' + (this.imageFilenames.indexOf(filename) + 1)
    }));
  }
}
