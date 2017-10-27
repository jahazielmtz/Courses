import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'JazzoSquare';
  lugares:any = [
    {plan: 'pagado', cercania: 1, distancia: 1, active: true, nombre: 'Florería La Gardenia'},
    {plan: 'gratuito', cercania: 1, distancia: 1.8, active: false, nombre: 'Donas La Pasadita'},
    {plan: 'gratuito', cercania: 2, distancia: 5, active: true, nombre: 'Veterinaria Huellitas Felices'},
    {plan: 'gratuito', cercania: 3, distancia: 10, active: true, nombre: 'Florería La Gardenia 2'},
    {plan: 'pagado', cercania: 3, distancia: 35, active: true, nombre: 'Donas La Pasadita 2'},
    {plan: 'gratuito', cercania: 3, distancia: 120, active: false, nombre: 'Veterinaria Huellitas Felices 2'}
  ];
  lat:number = 20.5706569;
  lng:number = -103.3483462;
  constructor() {

  }
}
