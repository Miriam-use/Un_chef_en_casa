import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetallesrecetasComponent } from './detallesrecetas.component';

describe('DetallesrecetasComponent', () => {
  let component: DetallesrecetasComponent;
  let fixture: ComponentFixture<DetallesrecetasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetallesrecetasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetallesrecetasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
