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
  public tileset: Tile[] = [];

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
  async boot(props: Map) : Promise<void>
  {
    const { width, height, preface } = props
    const fetched = await this.comms.get('tiles.json').toPromise()

    this.preface = preface

    for (let x = 0; x < width; x++) {
      for (let y = 0; y < height; y++) {
        const blank = { id: 1, terrain: 'grass', symbol: `${x}@${y}` }

        if (typeof fetched[x] == 'undefined') {
          this.tileset.push(blank)
          continue
        }

        this.tileset.push(fetched[x][y] || blank)
      }
    }

    this.style += `width: ${width * 32}px;`
  }

  /** */
  onTileSelection(tile: Tile) : void
  {
    this.comms.recontextualize.emit(tile)
  }
}
