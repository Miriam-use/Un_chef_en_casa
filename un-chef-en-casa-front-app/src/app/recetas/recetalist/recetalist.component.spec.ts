import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecetalistComponent } from './recetalist.component';

describe('RecetalistComponent', () => {
  let component: RecetalistComponent;
  let fixture: ComponentFixture<RecetalistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecetalistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecetalistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
