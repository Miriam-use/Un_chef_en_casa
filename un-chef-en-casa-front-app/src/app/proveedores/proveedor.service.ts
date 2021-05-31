import {Injectable} from '@angular/core';
import {Proveedor} from './proveedor';
import {of,Observable, throwError} from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Injectable()
export class ProveedorService {

  constructor(){}

}
