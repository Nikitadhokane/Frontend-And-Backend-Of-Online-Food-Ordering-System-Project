import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { ApiService } from '../api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {
  total: number = 0;
  list: any[];

  constructor(private api: ApiService, private toast: ToastrService, private route: Router) {}

  ngOnInit(): void {
    this.loadData();
  }

  removefromwishlist(id: number) {
    this.api.deletefromwishlist(id).subscribe({
      next: resp => {
        this.toast.success('Item deleted from wishlist');
        this.loadData();
      },
      error: err => {
        this.toast.error('Failed to remove item from wishlist');
        console.error(err);
      }
    });
  }

  placeorder(itemId: number) {
    const orderData = {
      customerid: sessionStorage.getItem('id'),
      itemId: itemId // Include the item ID to place order for the specific item
    };

    this.api.placeorder(orderData).subscribe({
      next: resp => {
        this.toast.success('Order placed successfully');
        this.route.navigate(['/history']);
      },
      error: err => {
        this.toast.error('Failed to place order');
        console.error(err);
      }
    });
  }

  loadData() {
    this.api.getwishlist(sessionStorage.getItem('id')).subscribe({
      next: resp => {
        this.list = resp;
        this.calculateTotal();
      },
      error: err => {
        this.toast.error('Failed to load wishlist');
        console.error(err);
      }
    });
  }

  calculateTotal() {
    this.total = this.list.reduce((sum, item) => sum + item.food.price, 0);
  }
}

