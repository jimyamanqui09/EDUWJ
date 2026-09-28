import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CategoriaCard } from './categoria-card';

describe('CategoriaCard', () => {
  let component: CategoriaCard;
  let fixture: ComponentFixture<CategoriaCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoriaCard],
    }).compileComponents();

    fixture = TestBed.createComponent(CategoriaCard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
