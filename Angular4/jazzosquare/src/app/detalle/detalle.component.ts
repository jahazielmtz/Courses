import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LugaresService } from '../services/lugares.service';

@Component({
  selector: 'app-detalle',
  templateUrl: './detalle.component.html'
})
export class DetalleComponent {
  id = null;
  lugar: any = {};
  constructor(private route: ActivatedRoute, private lugaresService: LugaresService) {
    console.log('Param id ', this.route.snapshot.params['id']);
    console.log('queryParams ', this.route.queryParams);
    console.log('Param action ', this.route.snapshot.queryParams['action']);
    this.id = this.route.snapshot.params['id'];
    this.lugaresService.buscarLugar(this.id).subscribe(lugar => this.lugar = lugar);
    debugger;
  }
}
