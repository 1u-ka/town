import { Component, OnInit } from '@angular/core';
import { Comms } from '../comms.service';
import { Observable } from 'rxjs';

import { Tile } from '../tile.model';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})

export class MapComponent implements OnInit {

  /** */
  tileset$: Observable<Tile[]>;

  /** */
  constructor(
    protected comms: Comms
  ) {}

  /** */
  ngOnInit() : void
  {
    this.tileset$ = this.comms.get('tiles.json')
  }
}
