import { BrowserModule } from '@angular/platform-browser';
import { LOCALE_ID, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatMomentDateModule } from '@angular/material-moment-adapter';
import { HttpClientModule } from '@angular/common/http';
import { registerLocaleData } from '@angular/common';
import localeES from '@angular/common/locales/es';
import { GooglePlaceModule } from "ngx-google-places-autocomplete";

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { FormComponent } from './usuarios/form.component';
import { ViajesComponent } from './viajes/viajes.component';
import { LoginComponent } from './usuarios/login.component';
import { FormnuevoviajeuberComponent } from './viajes/formnuevoviajeuber.component';
import { ProveedoresComponent } from './proveedores/proveedores.component';
import { UsuariodetalleComponent } from './usuarios/usuariodetalle.component';
import { DashboardComponent } from './dashboard/dashboard.component';

import { PaginatorComponent } from './paginator/paginator.component'

import { UsuarioService } from './usuarios/usuario.service';
import { ViajeService } from './viajes/viaje.service';
import { ProveedorService } from './proveedores/proveedor.service';
import { DashboardService } from './dashboard/dashboard.service';
//import { ContractsService } from './contracts/contracts.service';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { RecetadetalleComponent } from './recetas/recetadetalle/recetadetalle.component';
import { RecetalistComponent } from './recetas/recetalist/recetalist.component';
import { FavoritoComponent } from './recetas/favorito/favorito.component';
import { RecetasComponent } from './recetas/recetas/recetas.component';
import { DetallesrecetasComponent } from './recetas/detallesrecetas/detallesrecetas.component';

registerLocaleData(localeES, 'es');

const routes: Routes=[
  {path:'',redirectTo:'/usuarios/login', pathMatch:'full'},
  {path:'home',component:HomeComponent},
  {path:'usuarios',component:UsuariosComponent},
  {path:'usuarios/pg/:pagina',component:UsuariosComponent},
  {path:'usuarios/form',component:FormComponent},
  {path:'usuarios/form/:id',component:FormComponent},
  {path:'usuarios/login',component:LoginComponent},
  {path:'usuarios/detalle',component:UsuariodetalleComponent},
  {path:'viajes',component:ViajesComponent},
  {path:'viajes/pg/:pagina',component:ViajesComponent},
  {path:'viajes/detalle',component:ViajesComponent},
  {path:'viajes/formnuevoviajeuber',component:FormnuevoviajeuberComponent},
  {path:'proveedores',component:ProveedoresComponent},
  {path:'dashboard',component:DashboardComponent},
  {path:'recetadetalle',component:RecetadetalleComponent},
  {path:'recetadetalle/:id',component:RecetadetalleComponent},
  {path:'receta',component:RecetalistComponent},
  {path:'favorito',component:FavoritoComponent},
  {path:'receta/detalles',component:RecetasComponent},
  {path:'receta/detalles/:id',component:DetallesrecetasComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ViajesComponent,
    UsuariosComponent,
    FormComponent,
    FormnuevoviajeuberComponent,
    ProveedoresComponent,
    PaginatorComponent,
    HomeComponent,
    LoginComponent,
    UsuariodetalleComponent,
    DashboardComponent,
    RecetadetalleComponent,
    RecetalistComponent,
    FavoritoComponent,
    RecetasComponent,
    DetallesrecetasComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatMomentDateModule,
    GooglePlaceModule,
    MatProgressBarModule 
  ],
  providers: [
    UsuarioService,
    ViajeService,
    ProveedorService,
    FormComponent,
    DashboardService,
//    ContractsService,
    {provide: LOCALE_ID, useValue: 'es' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
