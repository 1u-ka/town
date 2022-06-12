import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable, Output } from '@angular/core';
import { Observable } from 'rxjs';
import { Tile } from './tile.model';

@Injectable()
export class Utils {

  /** */
  pipe(initial: any, ...functs: Array<Function>) : any {
    return functs.reduce((acc, el) => el(acc))
  }

}
