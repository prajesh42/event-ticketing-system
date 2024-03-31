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
  typedTexts:any[]=[];

  constructor(private eventService: EventService, private route: Router, private config: NgbCarouselConfig) {
    config.interval = 5000;  
    config.wrap = true;  
    config.keyboard = false;  
    config.pauseOnHover = false; 
    this.slides=this.getSlides();
    this.typedTexts=this.getTypedTexts();
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

  getTypedTexts(){
    return ['Where creativity meets coordination – your event, our expertise.',
    'From concept to celebration, we\'re with you every step of the way.',
    'Turning dreams into events, and events into unforgettable memories.',
    'Crafting moments that last a lifetime welcome to the art of event planning.',
    'Events tailored to perfection because every detail matters.'
  ];
  }
}
