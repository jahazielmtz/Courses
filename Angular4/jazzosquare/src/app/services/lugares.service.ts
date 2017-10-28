import { Injectable } from "@angular/core";
import { AngularFireDatabase } from "angularfire2/database/database";

@Injectable()
export class LugaresService {
  lugares:any = [
    {id: 1, plan: 'pagado', cercania: 1, distancia: 1, active: true, nombre: 'Florería La Gardenia', description: 'Descripción 1'},
    {id: 2, plan: 'gratuito', cercania: 1, distancia: 1.8, active: false, nombre: 'Donas La Pasadita', description: 'Descripción 2'},
    {id: 3, plan: 'gratuito', cercania: 2, distancia: 5, active: true, nombre: 'Veterinaria Huellitas Felices', description: 'Descripción 3'},
    {id: 4, plan: 'gratuito', cercania: 3, distancia: 10, active: true, nombre: 'Florería La Gardenia 2', description: 'Descripción 4'},
    {id: 5, plan: 'pagado', cercania: 3, distancia: 35, active: true, nombre: 'Donas La Pasadita 2', description: 'Descripción 5'},
    {id: 6, plan: 'gratuito', cercania: 3, distancia: 120, active: false, nombre: 'Veterinaria Huellitas Felices 2', description: 'Descripción 6'}
  ];

  constructor(private afDB: AngularFireDatabase) {
  }

  public getLugares() {
    return this.afDB.list('lugares/').valueChanges();
  }

  public buscarLugar(id) {
    return this.lugares.filter((lugar) => { return lugar.id == id })[0] || null;
  }

  public guardarLugar(lugar) {
    console.log(lugar);
    this.afDB.database.ref('lugares/' + lugar.id).set(lugar);
  }
}