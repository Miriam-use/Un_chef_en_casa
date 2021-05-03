import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { DatoPage } from './dato.page';

describe('DatoPage', () => {
  let component: DatoPage;
  let fixture: ComponentFixture<DatoPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DatoPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(DatoPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
