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

  chart = [];
  dashboard:Dashboard=new Dashboard();

  constructor(
    private dashboardService: DashboardService,
  ) { }

  ngOnInit(): void {
    this.getDatosDashBoard();
  }

  getDatosDashBoard(){
    this.dashboardService.getDatos().subscribe(response => {
      this.dashboard=response;
      this.setCirculoEcos(response);
    });
  }

  setDashboard(respuesta:any){
    this.dashboard=respuesta;
  }

  setCirculoEcos(dashboard:Dashboard){
    this.chart = new Chart('myChart', {
      type: 'pie',
      data:
      {
          labels:['SIN','C','B','ECO','CERO'],
          datasets: [
              {
              label: 'Points',
              backgroundColor: ['#545454','#00bb2d','#ffff00','#35ECDE','#3567EC'],
              data: [dashboard.viajessin,dashboard.viajesc,dashboard.viajesb,dashboard.viajeseco,dashboard.viajescero]
              }
          ]
      },
       options: {
          cutoutPercentage: 40,
           animation:  {
               animateScale: true
           }
       }
      });
  }

}
