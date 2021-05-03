import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { PortaPage } from './porta.page';

describe('PortaPage', () => {
  let component: PortaPage;
  let fixture: ComponentFixture<PortaPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PortaPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(PortaPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
