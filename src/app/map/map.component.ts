import { Component, ElementRef, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Comms } from '../comms.service';
import { Utils } from '../utils.service';
import { Observable, of } from 'rxjs';

import { Tile } from '../tile.model';
import { Map } from '../map.model';

import { Matrix } from '../matrix.type';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})

export class MapComponent implements OnInit {

  /** */
  private id: number;
  
  /** */
  private host: string;

  /** */
  protected blank: Tile = {
    id: null,
    terrain: 'grass',
    symbol: `Grass`,
    coordx: null,
    coordy: null,
    styling: null
  }

  /** */
  protected width: number
  protected height: number

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
    protected utils: Utils,
    private route: ActivatedRoute,
    private router: Router,
  ) {
    this.host = 'http://localhost:8000'
  }

  /**
   * []
   * 
   * @return void
   */
  ngOnInit() : void {
    const mid  = Number(this.route.snapshot
                                  .paramMap
                                  .get('id'))

                                  
    this.id = mid ? mid : 1
    this.comms.get(`${this.host}/api/maps/${this.id}`).subscribe(this.boot.bind(this))
    // this.tileset$ = this.comms.get('tiles.json')
  }

  /** */
  spriting(t: Array<Tile>) : Array<Tile> {
    return t.map((e: Tile) : Tile => {
      if (typeof e.terrain !== 'undefined')
        e.styling = `background:url('${e.terrain}')`
      
      return e
    })
  }

  /**
   * [ arrange the tiles before rendering ] 
   * 
   * @param  ?
   * 
   * @return ?
   */
  arrange (t: Array<Tile>) : Matrix {
    let m: Matrix = []

    t.forEach(e => {
      m[e.coordx] = m[e.coordx] || []
      m[e.coordx][e.coordy] = e
    })

    return m
  }

  /**
   * fill in blanks
   */
  fill(t: Matrix) : Array<Tile> {
    let tmp: Array<Tile> = []

    for (let x = 1; x < (this.width + 1); x++) {
      for (let y = 1; y < (this.height + 1); y++) {
        tmp.push((t[x] && t[x][y]) ? t[x][y] : this.blank)
      }
    }

    return tmp
  }

  /**
   * @todo   de-hardcode tile widhts
   */
  async boot(props: Map) : Promise<void> {
    const fetched = await this.comms.get(`${this.host}/api/maps/${this.id}/tiles`).toPromise()

    // ?
    this.width = props.width
    this.height = props.height
    this.preface = props.preface

    // ????
    // ({ this.width, this.height, this.preface } = props)

    let operations: Array<Function> = [
      this.spriting.bind(this),
      this.arrange.bind(this),
      this.fill.bind(this)
    ]
    
    this.tileset  = operations.reduce((acc, el) => el(acc), fetched)
    console.log( this.tileset )
    this.style   += `width: ${this.width * 32}px;`
  }

  /** */
  onTileSelection(tile: Tile) : void {
    this.comms.recontextualize.emit(tile)
  }
}
