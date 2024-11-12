// import { Component, OnInit } from '@angular/core';
// import { ApiService } from '../api.service';

// @Component({
//   selector: 'app-orderhistory',
//   templateUrl: './orderhistory.component.html',
//   styleUrls: ['./orderhistory.component.css']
// })
// export class OrderhistoryComponent implements OnInit {

//    list:any[]
//   constructor(private api:ApiService) { }

//   ngOnInit(): void {
//     this.loadData()
//   }

//    loadData(){
//     this.api.orderhistory(sessionStorage.getItem('id')).subscribe({
//       next:resp=>{
//         this.list=resp       
//       }
//     })
//   }
// }

import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-orderhistory',
  templateUrl: './orderhistory.component.html',
  styleUrls: ['./orderhistory.component.css']
})
export class OrderhistoryComponent implements OnInit {

   list:any[];
  
  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.api.orderhistory(sessionStorage.getItem('id')).subscribe({
      next: resp => {
        console.log('API Response:', JSON.stringify(resp, null, 2)); // Log the entire response
        this.list = resp.map(order => {
          order.orderDate = new Date(order.orderDate); // Ensure date is in correct format
          order.orderdetails = order.orderdetails.map(detail => {
            if (detail.qty === 0) {
              detail.qty = 1;
            }
            return detail;
          });
          return order;
        });
      },
      error: err => {
        console.error('Error fetching order history:', err);
      }
    });
  }
}



