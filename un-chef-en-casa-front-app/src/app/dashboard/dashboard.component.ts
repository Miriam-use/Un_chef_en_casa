import { Component, OnInit } from '@angular/core';
import { Chart} from "chart.js";
import { Dashboard } from './dashboard';
import { DashboardService } from './dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']

})
export class DashboardComponent implements OnInit {

  
  constructor() {}

  ngOnInit(): void {}

}
