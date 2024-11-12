import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  fg: FormGroup;
  list: any[] = [];
  editingCategoryId: number | null = null;

  constructor(private api: ApiService,
              private fb: FormBuilder,
              private toast: ToastrService) { }

  ngOnInit(): void {
    this.createForm();
    this.loadData();
  }

  deleteCat(id: number) {
    this.api.deletecategory(id).subscribe({
      next: resp => {
        this.toast.success('Category deleted successfully');
        this.loadData();
      },
      error: err => this.toast.error('Cannot delete category')
    });
  }

  loadData() {
    this.api.listcategories().subscribe({
      next: resp => this.list = resp,
      error: err => console.log(err.error)
    });
  }

  createForm() {
    this.fg = this.fb.group({
      'catname': ['', Validators.required]
    });
  }

  savecategory(values: any) {
    if (this.editingCategoryId !== null) {
      const updatedCategory = {
        id: this.editingCategoryId,
        catname: values.catname
      };

      this.api.updatecategory(updatedCategory).subscribe({
        next: resp => {
          this.toast.success('Category updated successfully');
          this.loadData();
          this.fg.reset();
          this.editingCategoryId = null;
        },
        error: err => {
          this.toast.error('Error updating category: ' + err.message);
          console.error(err);
        }
      });
    } else {
      this.api.savecategory(values).subscribe({
        next: resp => {
          this.toast.success('Category added successfully');
          this.loadData();
          this.fg.reset();
        },
        error: err => {
          this.toast.error('Error adding category: ' + err.message);
          console.error(err);
        }
      });
    }
  }

  editCat(cat: any) {
    this.fg.patchValue({
      catname: cat.catnameEdit // Assuming catnameEdit is used for editing purposes
    });
    this.editingCategoryId = cat.id;
  }

  cancelEdit() {
    this.fg.reset();
    this.editingCategoryId = null;
  }
}
