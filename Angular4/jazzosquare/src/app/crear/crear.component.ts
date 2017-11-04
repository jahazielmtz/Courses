import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LugaresService } from "../services/lugares.service";

@Component({
  selector: 'app-crear',
  templateUrl: './crear.component.html'
})

export class CrearComponent {
  lugar: any = {};
  id: any = null;

  constructor(private route: ActivatedRoute, private lugaresService: LugaresService) {
    this.id = route.snapshot.params['id'];
    if (this.id != 'new') {
      this.lugaresService.buscarLugar(this.id)
        .subscribe(lugar => this.lugar = lugar);
    }
  }

  guardarLugar() {
    if (this.id == 'new') {
      this.lugar.id = Date.now();
    }
    let direccion = this.lugar.direccion + "," + this.lugar.ciudad + "," + this.lugar.pais;
    this.lugaresService.obtenerGeodata(direccion)
      .subscribe(result => {
        // result = Object.keys(result).map(result => result[0]);
        let direccion = Object.keys(result).map((key) => result[key]);
        this.lugar.lat = direccion[0][0].geometry.location.lat;
        this.lugar.lng = direccion[0][0].geometry.location.lng;
        debugger
        this.lugaresService.guardarLugar(this.lugar).subscribe(_ => alert('guardado con exito'));
        this.lugar = {};
      });
  }
}
