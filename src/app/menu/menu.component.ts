import { Component, OnInit } from '@angular/core';
import { Comms } from '../comms.service';
import { Tile } from '../tile.model';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})

export class MenuComponent implements OnInit {

  contextual: any;
  links: any[];

  constructor(
    protected comms: Comms
  ) { }

  /**
   * @todo dynamically fetch navigation from a backend
   */
  ngOnInit(): void {
    this.links = [
      { text: 'map', href: '/' },
      { text: 'about', href: '/page/about' }
    ]

    this.comms.recontextualize.subscribe((e: any) => this.contextual = e)
  }
}
