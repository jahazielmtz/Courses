import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { AgmCoreModule } from '@agm/core';
import { ResaltarDirective } from './directives/resaltar.directive';
import { ContarClicksDirective } from './directives/contar-clicks.directive';
import { Routes, RouterModule } from '@angular/router';
import { DetalleComponent } from './detalle/detalle.component';
import { LugaresComponent } from './lugares/lugares.component';
import { ContactoComponent } from './contacto/contacto.component';
import { LugaresService } from './services/lugares.service';
import { AngularFireModule } from 'angularfire2';
import { AngularFireDatabaseModule } from 'angularfire2/database';
import { AngularFireAuthModule } from 'angularfire2/auth';
import { environment } from '../environments/firebase.environment';
import { CrearComponent } from './crear/crear.component';
import { HttpClientModule } from '@angular/common/http';

const appRoutes: Routes = [
  { path: '', component: LugaresComponent },
  { path: 'lugares', component: LugaresComponent },
  { path: 'detalle/:id', component: DetalleComponent },
  { path: 'contacto', component: ContactoComponent },
  { path: 'crear/:id', component: CrearComponent }
]

@NgModule({
  declarations: [
    AppComponent,
    ResaltarDirective,
    ContarClicksDirective,
    DetalleComponent,
    LugaresComponent,
    ContactoComponent,
    CrearComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCo-JSjZNGAlRDgz6JJ-B0yvJUMBjrsqAk'
    }),
    RouterModule.forRoot(appRoutes),
    AngularFireModule.initializeApp(environment.firebase, 'jazzosquare'),
    AngularFireDatabaseModule,
    AngularFireAuthModule,
    HttpClientModule
  ],
  providers: [LugaresService],
  bootstrap: [AppComponent]
})

export class AppModule { }
