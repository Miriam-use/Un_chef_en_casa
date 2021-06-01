import { Proveedor } from '../proveedores/proveedor';
import { Usuario } from '../usuarios/usuario';

export class Viaje {
  id:number;
  rideid: number;
  requestid: string;
  proveedor: Proveedor;
  usuario: Usuario;
  status: string;
  dasatos: string;
  origen: string;
  destino: string;
  fecha: string;
  fechaida: string;
  horaida: string;
  motivo: string;
  descripcion: string;
  ecocoins: number;
  idreceipt: string;
  precio:number;
  txdataviaje:string;
  txdataecocoins:string;
  tipotransporte:string;
  consumo:number;
  distancia:number;
  huellaemision:string;
  huellasalvada:string;

  setProveedor(proveedor:Proveedor){
      this.proveedor=proveedor;
      console.log(this.proveedor);
  }
}
