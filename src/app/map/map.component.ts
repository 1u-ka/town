import { Component, ElementRef, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Comms } from '../comms.service';
import { Observable, of } from 'rxjs';

import { Tile } from '../tile.model';
import { Map } from '../map.model';

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
  public style: string = 'line-height: 0;';

  /** */
  constructor(
    protected comms: Comms,
    private route: ActivatedRoute,
    private router: Router,
  ) {
  }

  /** */
  ngOnInit() : void
  {
    const mid  = Number(this.route.snapshot
                                  .paramMap
                                  .get('id'))

    this.comms.get('map.json').subscribe(this.boot.bind(this))

    this.id       = mid ? mid : 1
    // this.tileset$ = this.comms.get('tiles.json')
  }

  /**
   * @todo   de-hardcode tile widhts
   */
  boot(props: Map) : void
  {
    let tileset = new Array

    const { width, height, preface } = props
    this.preface = preface

    for (let i = 1; i <= width; i++) {
      for (let j = 1; j <= height; j++) {
        tileset.push(
          { id: 1, terrain: 'grass', symbol: `${j}@${i}` }
        )
      }
    }

    this.tileset$ = of(tileset)
    this.style += `width: ${width * 32}px;`
  }

  /** */
  onTileSelection(tile: Tile) : void
  {
    this.comms.recontextualize.emit(tile)
  }
}
