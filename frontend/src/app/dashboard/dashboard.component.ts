import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {
  totalCustomers: number = 0;
  totalOrders: number = 0;
  totalCategories: number = 0;

  constructor(private api: ApiService) { }

  ngOnInit(): void {
   // Fetch data for the dashboard when the component initializes
    this.fetchData();
  }

  fetchData() {
    // Example: Fetching total number of customers
    this.api.listcustomers().subscribe((data: any[]) => {
      this.totalCustomers = data.length;
    });

    // Example: Fetching total number of orders
    this.api.allorders().subscribe((data: any[]) => {
      this.totalOrders = data.length;
    });

    // Example: Fetching total number of categories
    this.api.listcategories().subscribe((data: any[]) => {
      this.totalCategories = data.length;
    });
  }
}
