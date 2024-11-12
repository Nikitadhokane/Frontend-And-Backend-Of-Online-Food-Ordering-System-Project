// import { Component, OnInit } from '@angular/core';
// import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
// import { Router } from '@angular/router';
// import { ToastrService } from 'ngx-toastr';
// import { ApiService } from '../api.service';

// @Component({
//   selector: 'app-register',
//   templateUrl: './register.component.html',
//   styleUrls: ['./register.component.css']
// })
// export class RegisterComponent implements OnInit {
//   submitted = false;
//   fg: FormGroup;
//   cities = ['Mumbai', 'Pune', 'Aurangabad', 'Nagpur', 'Ahilyanagar', 'Nashik','Kolhapur','Nanded','Solapur','Jalgaon','Akola']; // Add your list of cities here
//   showOtherCityInput = false;

//   constructor(
//     private fb: FormBuilder,
//     private api: ApiService,
//     private _router: Router,
//     private toast: ToastrService
//   ) {
//     this.createForm();
//   }

//   ngOnInit(): void { }

//   createForm() {
//     this.fg = this.fb.group({
//       'userid': ['', [Validators.required, Validators.email]],
//       'name': ['', [Validators.required, this.onlyAlphabets]],
//       'gender': ['', Validators.required],
//       'city': ['', Validators.required],
//       'otherCity': [''],
//       'phone': ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
//       'pwd': ['', [Validators.required, Validators.minLength(8), this.passwordStrength]],
//     });
//   }


//   onlyAlphabets(control: AbstractControl): ValidationErrors | null {
//     const valid = /^[a-zA-Z]+$/.test(control.value);
//     return valid ? null : { onlyAlphabets: true };
//   }

//   passwordStrength(control: AbstractControl): ValidationErrors | null {
//     const value = control.value;
//     if (!value) return null;

//     const hasUpperCase = /[A-Z]+/.test(value);
//     const hasLowerCase = /[a-z]+/.test(value);
//     const hasNumeric = /[0-9]+/.test(value);
//     const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]+/.test(value);
//     const valid = hasUpperCase && hasLowerCase && hasNumeric && hasSpecialChar;
//     return valid ? null : { passwordStrength: true };
//   }

//   onCityChange(event: any) {
//     const value = event.target.value;
//     if (value === 'Other') {
//       this.showOtherCityInput = true;
//       this.fg.get('otherCity')?.setValidators(Validators.required);
//     } else {
//       this.showOtherCityInput = false;
//       this.fg.get('otherCity')?.clearValidators();
//     }
//     this.fg.get('otherCity')?.updateValueAndValidity();
//   }


//   registeruser(values: any) {
//     this.submitted = true;
//     if (this.fg.valid) {
//       const registrationData = { ...values };
//       if (this.showOtherCityInput) {
//         registrationData.city = values.otherCity;
//       }
//       console.log('Registration Data:', registrationData); // Debugging line
//       this.api.register(registrationData).subscribe({
//         next: resp => {
//           console.log(resp);
//           this.toast.success('Registered successfully');
//           this._router.navigate(['login']);
//         },
//         error: err => {
//           console.error('Error Response:', err); // Log the error response
//           this.toast.error('Something bad happened', "Registration Failed");
//         }
//       });
//     }
//   }
  
// }

import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  submitted = false;
  fg: FormGroup;
  cities = ['Mumbai', 'Pune', 'Aurangabad', 'Nagpur', 'Ahilyanagar', 'Nashik','Kolhapur','Nanded','Solapur','Jalgaon','Akola']; // Add your list of cities here
  showOtherCityInput = false;

  constructor(
    private fb: FormBuilder,
    private api: ApiService,
    private _router: Router,
    private toast: ToastrService
  ) {
    this.createForm();
  }

  ngOnInit(): void { }

  createForm() {
    this.fg = this.fb.group({
      'userid': ['', [Validators.required, Validators.email]],
      'name': ['', [Validators.required, this.onlyAlphabets]],
      'gender': ['', Validators.required],
      'city': ['', Validators.required],
      'otherCity': [''],
      'phone': ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      'pwd': ['', [Validators.required, Validators.minLength(8), this.passwordStrength]],
    });
  }

  onlyAlphabets(control: AbstractControl): ValidationErrors | null {
    const valid = /^[a-zA-Z]+$/.test(control.value);
    return valid ? null : { onlyAlphabets: true };
  }

  passwordStrength(control: AbstractControl): ValidationErrors | null {
    const value = control.value;
    if (!value) return null;

    const hasUpperCase = /[A-Z]+/.test(value);
    const hasLowerCase = /[a-z]+/.test(value);
    const hasNumeric = /[0-9]+/.test(value);
    const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]+/.test(value);
    const valid = hasUpperCase && hasLowerCase && hasNumeric && hasSpecialChar;

    return valid ? null : { passwordStrength: true };
  }

  onCityChange(event: any) {
    const value = event.target.value;
    if (value === 'Other') {
      this.showOtherCityInput = true;
      this.fg.get('otherCity')?.setValidators(Validators.required);
    } else {
      this.showOtherCityInput = false;
      this.fg.get('otherCity')?.clearValidators();
    }
    this.fg.get('otherCity')?.updateValueAndValidity();
  }

  registeruser(values: any) {
    this.submitted = true;
    if (this.fg.valid) {
      const registrationData = { ...values };
      if (this.showOtherCityInput) {
        registrationData.city = values.otherCity;
      }
      this.api.register(registrationData).subscribe({
        next: resp => {
          this.toast.success('Registered successfully');
          this._router.navigate(['login']);
        },
        error: err => {
          console.error('Error Response:', err); // Log the error response
          this.toast.error('Something bad happened', "Registration Failed");
        }
      });
    }
  }
}
