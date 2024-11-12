import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  uinfo: any;
  editing: boolean = false;
  submitted: boolean = false;
  fg: FormGroup;
  cities: string[] = ['Pune', 'Mumbai', 'Aurangabad', 'Nashik'];
  otherCity: boolean = false;

  constructor(
    private fb: FormBuilder,
    private api: ApiService,
    private _router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    const userId = sessionStorage.getItem('id');
    if (userId) {
      this.api.getcustomerdetails(userId)
        .subscribe({
          next: resp => this.uinfo = resp.data,
          error: () => alert('Failed to load user details')
        });
    }

    // Initialize form group
    this.fg = this.fb.group({
      name: ['', [Validators.required, Validators.pattern('[a-zA-Z ]*')]],
      userid: ['', Validators.required],
      phone: ['', [Validators.required, Validators.pattern('[0-9]{10}')]],
      gender: ['', Validators.required],
      city: ['', Validators.required]
    });
  }

  editProfile(): void {
    this.editing = true;
    this.submitted = false; // Reset submitted state when editing starts
  }

  cancelEdit(): void {
    this.editing = false;
  }

  checkCity(): void {
    const selectedCity = this.fg.get('city')?.value;
    this.otherCity = selectedCity === 'other';
    if (this.otherCity) {
      this.fg.get('city')?.setValue('');
    }
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.fg.valid) {
      this.api.updateCustomerDetails(this.uinfo).subscribe({
        next: () => {
          this.editing = false;
          this.showUpdateSuccessToast();
        },
        error: () => {
          alert('Update failed. Please try again.');
        }
      });
    }
  }

  showUpdateSuccessToast(): void {
    this.toastr.success('Profile updated successfully!', 'Success');
  }
}
