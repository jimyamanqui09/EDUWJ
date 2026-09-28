import { Component } from '@angular/core';
import { CategoriaCard } from '../../temp/categoria-card'; // ajusta la ruta según dónde quedó tu carpeta temp/

@Component({
  selector: 'app-inicio',
  imports: [CategoriaCard],
  templateUrl: './inicio.html',
})
export class Inicio {}
