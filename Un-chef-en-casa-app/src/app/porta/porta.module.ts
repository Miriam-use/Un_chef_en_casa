import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PortaPageRoutingModule } from './porta-routing.module';

import { PortaPage } from './porta.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PortaPageRoutingModule
  ],
  declarations: [PortaPage]
})
export class PortaPageModule {}
