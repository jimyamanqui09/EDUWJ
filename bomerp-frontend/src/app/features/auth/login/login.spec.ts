import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Login } from './login';
import { AuthService } from '../../../core/services';
import { Router } from '@angular/router';
import { of, throwError } from 'rxjs';
import { signal } from '@angular/core';

describe('Login', () => {
  let component: Login;
  let fixture: ComponentFixture<Login>;
  let authServiceSpy: jasmine.SpyObj<AuthService>;
  let routerSpy: jasmine.SpyObj<Router>;

  beforeEach(async () => {
    authServiceSpy = jasmine.createSpyObj('AuthService', ['login']);
    routerSpy = jasmine.createSpyObj('Router', ['navigate']);

    await TestBed.configureTestingModule({
      imports: [Login],
      providers: [
        { provide: AuthService, useValue: authServiceSpy },
        { provide: Router, useValue: routerSpy },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(Login);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should not submit if form invalid', () => {
    component.onSubmit();
    expect(authServiceSpy.login).not.toHaveBeenCalled();
  });

  it('should call login and navigate on success', () => {
    authServiceSpy.login.and.returnValue(of({ id: 1, username: 'admin', rol: 'ADMIN', token: 'token', mensaje: 'OK' }));

    component.username.set('admin');
    component.password.set('admin123');
    component.onSubmit();

    expect(authServiceSpy.login).toHaveBeenCalledWith({ username: 'admin', password: 'admin123' });
    expect(routerSpy.navigate).toHaveBeenCalledWith(['/alumnos']);
  });

  it('should show error on login failure', () => {
    authServiceSpy.login.and.returnValue(throwError(() => ({ error: { mensaje: 'Credenciales inválidas' } })));

    component.username.set('admin');
    component.password.set('wrong');
    component.onSubmit();

    expect(component.error()).toBe('Credenciales inválidas');
    expect(component.loading()).toBeFalse();
  });
});