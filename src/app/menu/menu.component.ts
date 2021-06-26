import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})

export class MenuComponent implements OnInit {

  links: any[];

  constructor() { }

  /**
   * @todo dynamically fetch navigation from a backend
   */
  ngOnInit(): void {
    this.links = [
      { text: 'map', href: '/' },
      { text: 'about', href: '/page/about' }
    ]
  }

}
