import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-viewcart',
  templateUrl: './viewcart.component.html',
  styleUrls: ['./viewcart.component.css']
})
export class ViewcartComponent implements OnInit {
  total: number = 0;
  list: any[] = [];
  paymentMethod: string = ''; // Default payment method is not selected
  address: string = ''; // Address input field

  constructor(
    private api: ApiService,
    private toast: ToastrService,
    private route: Router
  ) { }

  ngOnInit(): void {
    this.loadData();
  }

  removefromwishlist(id: number) {
    this.api.deletefromcart(id).subscribe({
      next: resp => {
        this.toast.success('Item deleted from cart');
        this.loadData();
      }
    });
  }

  placeorder() {
    // Check if the cart is empty
    if (this.list.length === 0) {
      this.toast.error('Your cart is empty. Please add items to your cart before placing an order.');
      return;
    }

    // // Check if an address is provided
    // if (!this.address) {
    //   this.toast.error('Please provide an address');
    //   return;
    // }

    // Check if a payment method is selected
    if (!this.paymentMethod) {
      this.toast.error('Please provide address and select a payment method');
      return;
    }

    // Check if online payment is selected
    if (this.paymentMethod === 'onlinePayment') {
      // Retrieve the card number input value
      const cardNo = (document.getElementById('cardNo') as HTMLInputElement).value;
      const nameOnCard = (document.getElementById('nameOnCard') as HTMLInputElement).value;
      const expiryDate = (document.getElementById('expiryDate') as HTMLInputElement).value;
      const cvv = (document.getElementById('cvv') as HTMLInputElement).value;

      // Validate card number
      if (!cardNo || cardNo.length !== 16 || isNaN(Number(cardNo))) {
        this.toast.error('Please provide a valid 16-digit card number for online payment');
        return; // Prevent order placement if validation fails
      }

      // Validate other card details
      if (!nameOnCard) {
        this.toast.error('Please provide the name on the card');
        return;
      }

      if (!expiryDate) {
        this.toast.error('Please provide the expiry date');
        return;
      }

      if (!cvv || cvv.length !== 3 || isNaN(Number(cvv))) {
        this.toast.error('Please provide a valid 3-digit CVV');
        return;
      }
    }

    // Proceed with order placement logic
    const orderData = {
      customerid: sessionStorage.getItem('id'),
      paymentMethod: this.paymentMethod,
      // address: this.address
    };

    this.api.placeorder(orderData).subscribe({
      next: resp => {
        this.toast.success('Order placed successfully');
        this.route.navigate(['/history']);
      },
      error: err => console.log(err)
    });
  }

  updateqty(cartid: number, qty: number) {
    if (qty === 0) {
      this.toast.error('Cannot reduce quantity');
    } else {
      this.api.updateqty(cartid, qty).subscribe({
        next: resp => {
          this.toast.success('Quantity updated');
          this.loadData();
        },
        error: err => console.log('error')
      });
    }
  }

  loadData() {
    this.api.getcart(sessionStorage.getItem('id')).subscribe({
      next: resp => {
        // Set default quantity to 1 if not already set
        this.list = resp.map(item => {
          if (!item.qty || item.qty === 0) {
            item.qty = 1;
          }
          return item;
        });
        this.total = this.list.reduce((sum, x) => sum + x.qty * x.food.price, 0);
      }
    });
  }

  onPaymentMethodChange(method: string): void {
    this.paymentMethod = method;
  }
}
