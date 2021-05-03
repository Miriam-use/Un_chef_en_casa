import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PortaPage } from './porta.page';

const routes: Routes = [
  {
    path: '',
    component: PortaPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PortaPageRoutingModule {}
