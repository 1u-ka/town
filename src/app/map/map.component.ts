import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
  id: number;

  /** */
  tileset$: Observable<Tile[]>;

  /** */
  constructor(
    protected comms: Comms,
    private route: ActivatedRoute,
    private router: Router,
  ) {}

  /** */
  ngOnInit() : void
  {
    const mid = Number(this.route.snapshot
                          .paramMap
                          .get('id'))

    this.id = mid ? mid : 1

    this.tileset$ = this.comms.get('tiles.json')
  }

  /** */
  onTileSelection(tile: Tile) : void
  {
    this.comms.recontextualize.emit(tile)
  }
}
