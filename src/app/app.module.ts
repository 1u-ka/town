import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageComponent } from './page/page.component';
import { MenuComponent } from './menu/menu.component';
import { MapComponent } from './map/map.component';

import { Comms } from './comms.service';
import { Utils } from './utils.service';

@NgModule({
  declarations: [
    AppComponent,
    PageComponent,
    MenuComponent,
    MapComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [Comms, Utils],
  bootstrap: [AppComponent]
})
export class AppModule { }
