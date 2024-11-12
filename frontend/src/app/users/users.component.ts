import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  list: any[];

  constructor(private api: ApiService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.api.listcustomers().subscribe({
      next: resp => this.list = resp,
      error: err => this.toastr.error('Failed to load customer data', 'Error')
    });
  }
  deleteUser(userId: number) {
    if (confirm('Are you sure you want to deactivate this user?')) {
      this.api.deleteUser(userId).subscribe({
        next: resp => {
          this.toastr.success('User deactivated successfully', 'Success');
          this.loadData(); // Reload the data after deletion
        },
        error: err => {
          console.error('Error deleting user', err);
          this.toastr.error('Failed to deactivate user', 'Error');
        }
      });
    }
  }
}


