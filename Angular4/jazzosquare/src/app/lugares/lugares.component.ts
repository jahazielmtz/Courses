import { Component } from '@angular/core';
import { LugaresService } from '../services/lugares.service';

@Component({
  selector: 'app-lugares',
  templateUrl: './lugares.component.html',
  styleUrls: ['../app.component.css']
})
export class LugaresComponent {
  title = 'JazzoSquare';
  
  lat:number = 20.5706569;
  lng:number = -103.3483462;
  lugares = null;
  constructor(private lugaresService: LugaresService) {
    lugaresService.getLugares()
      .subscribe(lugares => {
        this.lugares = lugares;
      })
  }
} 
