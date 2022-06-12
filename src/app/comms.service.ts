import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable, Output } from '@angular/core';
import { Observable } from 'rxjs';
import { Tile } from './tile.model';

@Injectable()
export class Comms {

  constructor(private http: HttpClient) { }

  /**
   * @todo   ?
   */
  @Output()
  recontextualize: EventEmitter<Tile> = new EventEmitter

  /** */
  get(uri: string = '') : Observable<any> {
    return this.http.get(uri);
  }

}
