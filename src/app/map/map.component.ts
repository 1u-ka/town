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
  private id: number;

  /**
   * @todo   map header contents shouldn't be externally accessible
   */
  public preface: string;

  /**
   * @todo   map tiles should not be externally accessible
   */
  public tileset$: Observable<Tile[]>;

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

    this.id       = mid ? mid : 1
    this.comms.get('map.json').subscribe(r => this.preface = r.preface)
    this.tileset$ = this.comms.get('tiles.json')
  }

  /** */
  onTileSelection(tile: Tile) : void
  {
    this.comms.recontextualize.emit(tile)
  }
}
