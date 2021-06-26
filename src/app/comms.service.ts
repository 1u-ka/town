import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class Comms {

  constructor(private http: HttpClient) { }

  /** */
  get(uri: string = '') : Observable<any> {
    return this.http.get(`${environment.apiUri}${uri}`);
  }

}
