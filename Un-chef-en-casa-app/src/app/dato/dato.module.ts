import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { DatoPageRoutingModule } from './dato-routing.module';

import { DatoPage } from './dato.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    DatoPageRoutingModule
  ],
  declarations: [DatoPage]
})
export class DatoPageModule {}
