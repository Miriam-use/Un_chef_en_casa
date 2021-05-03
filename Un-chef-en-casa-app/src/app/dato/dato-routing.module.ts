import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DatoPage } from './dato.page';

const routes: Routes = [
  {
    path: '',
    component: DatoPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DatoPageRoutingModule {}
