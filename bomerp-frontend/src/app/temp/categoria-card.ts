import {
  Component, Input, OnChanges, OnInit, DoCheck, AfterContentInit, AfterContentChecked,
  AfterViewInit, AfterViewChecked, OnDestroy, SimpleChanges, ContentChild, ViewChild,
  ElementRef, inject,
} from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-categoria-card',
  imports: [ReactiveFormsModule],
  template: `
    <form [formGroup]="form">
      <input type="text" formControlName="nombre" #nombreInput />
    </form>
  `,
})
export class CategoriaCard
  implements OnChanges, OnInit, DoCheck, AfterContentInit, AfterContentChecked,
    AfterViewInit, AfterViewChecked, OnDestroy
{
  @Input() nombreInicial = '';

  @ContentChild('etiqueta') etiqueta?: ElementRef;
  @ViewChild('nombreInput') nombreInput?: ElementRef<HTMLInputElement>;

  private readonly fb = inject(FormBuilder);
  private cambiosSub?: Subscription;

  protected readonly form = this.fb.nonNullable.group({
    nombre: ['', [Validators.required, Validators.maxLength(80)]],
  });

  constructor() {
    // Solo inyección de dependencias (fb ya se resolvió arriba, al declarar
    // el campo). `nombreInicial` todavía no tiene ningún valor aquí.
    console.log('constructor');

  }

  ngOnChanges(changes: SimpleChanges): void {
    // Antes de ngOnInit, y de nuevo cada vez que el padre cambia [nombreInicial].
    if (changes['nombreInicial']) {
      console.log('Valor anterior:', changes['nombreInicial'].previousValue);
      console.log('Valor cambiado:', changes['nombreInicial'].currentValue);
    }
  }

  ngOnInit(): void {
    // Una sola vez, ya con nombreInicial asignado: arranca el formulario con ese valor.
    this.form.controls.nombre.setValue(this.nombreInicial);
    this.cambiosSub = this.form.controls.nombre.valueChanges.subscribe((nombre) => {
      console.log(nombre);
    });
 
  }

  ngDoCheck(): void {
    // En cada pasada, incluso si nada "cambió de verdad" según Angular.
    // Sirve para detectar una mutación dentro del mismo objeto/valor de entrada
    // (por ejemplo, si nombreInicial fuera un objeto y alguien lo mutara sin
    // reasignarlo), algo que ngOnChanges no vería.
  }

  ngAfterContentInit(): void {
    // Una sola vez, cuando el contenido proyectado por <ng-content> ya existe.
    console.log('Etiqueta proyectada:', this.etiqueta?.nativeElement.textContent);
  }

  ngAfterContentChecked(): void {
    // En cada pasada, después de revisar ese contenido proyectado.
  }

  ngAfterViewInit(): void {
    // Una sola vez, cuando la propia plantilla ya está en el DOM.
    this.nombreInput?.nativeElement.focus();
  }

  ngAfterViewChecked(): void {
    // En cada pasada, después de revisar la propia vista.
  }

  ngOnDestroy(): void {
    // Cierra lo que ngOnInit dejó abierto.
    this.cambiosSub?.unsubscribe();
  }
}
