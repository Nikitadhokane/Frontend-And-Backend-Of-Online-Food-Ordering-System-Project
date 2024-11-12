// import { HttpHeaders } from '@angular/common/http';
// import { Component, OnInit } from '@angular/core';
// import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { ToastrService } from 'ngx-toastr';
// import { ApiService } from '../api.service';

// @Component({
//   selector: 'app-products',
//   templateUrl: './products.component.html',
//   styleUrls: ['./products.component.css']
// })
// export class FoodsComponent implements OnInit {

//   fg:FormGroup;
//   model:any={}
//   cats:any[]
//   list:any[]
//   filePath="../assets/upload.png"
//   image:any
//   submitted=false;
//   upload=false;
 
//   constructor(private api:ApiService,
//     private fb:FormBuilder,
//     private toast:ToastrService) { }

//   ngOnInit(): void {
//     this.createForm()
//     this.loadData()
//   }

//   loadData(){
//     this.api.listcategories().subscribe({
//       next:resp=>this.cats=resp
//     })
//     this.api.listfoods().subscribe({
//       next:resp=>{
//         this.list=resp;
//       }
//     })
//   }

//   createForm(){
//      this.fg=this.fb.group({
//       'fname':['',Validators.required],
//       'category':['',Validators.required],
//       'descr':['',Validators.required],
//       'price':['',Validators.required],
//       'pic':['',Validators.required],
//       'id':['0',Validators.required],
//     })
//   }

//   saveFile(e:any){
//     const ele=(e.target as HTMLInputElement)
//     const file=ele.files?.item(0)
//     console.log(file)
//     this.image=file
//     this.upload=true;
//     const reader=new FileReader()
//     reader.readAsDataURL(file as Blob)
//     reader.onload=()=>{
//       this.filePath=reader.result as string
//       //this.fg.patchValue({pic:reader.result})
//     }
//   }

//   deleteFood(id:number){
//     this.api.deletefood(id).subscribe({
//       next:resp=>{
//         this.toast.success('Food deleted')
//         this.loadData()
//       },
//       error:err=>this.toast.error('Food cannot delete')
//     })
//   }

//   savefood(f:any){  
//     this.submitted=true;
//     this.fg.get('id').setValue('0')
//     if(this.fg.valid && this.upload){
//       console.log("Valid",this.fg.valid)
//       let fd=new FormData()
      
//       console.log(f)
//     for(let ele in this.fg.value){
//       console.log(ele)
//       fd.append(ele,(<any>this.fg.get(ele).value))
//     }
//     fd.append("pic",this.image)    
    
    
//       this.api.savefood(fd).subscribe({
//         next:resp=>{              
//         this.toast.success("Food saved successfully")      
//         this.fg.reset()
//         this.submitted=false;
//         //this.filePath="../assets/upload.png"   
//         this.loadData()
//         },
//         error:err=>console.log(err.error)
//       });
//     }
//   }

// }


import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class FoodsComponent implements OnInit {
  fg: FormGroup;
  model: any = {};
  cats: any[];
  list: any[];
  filePath = "../assets/upload.png";
  image: any;
  submitted = false;
  upload = false;
  editMode = false;
  currentFoodId: number | null = null;

  constructor(private api: ApiService, private fb: FormBuilder, private toast: ToastrService) { }

  ngOnInit(): void {
    this.createForm();
    this.loadData();
  }

  loadData() {
    this.api.listcategories().subscribe({
      next: resp => this.cats = resp
    });
    this.api.listfoods().subscribe({
      next: resp => this.list = resp
    });
  }

  createForm() {
    this.fg = this.fb.group({
      'fname': ['', Validators.required],
      'category': ['', Validators.required],
      'descr': ['', Validators.required],
      'price': ['', Validators.required],
      'pic': ['', Validators.required],
      'id': ['0', Validators.required]
    });
  }

  saveFile(e: any) {
    const ele = (e.target as HTMLInputElement);
    const file = ele.files?.item(0);
    this.image = file;
    this.upload = true;
    const reader = new FileReader();
    reader.readAsDataURL(file as Blob);
    reader.onload = () => {
      this.filePath = reader.result as string;
    };
  }

  deleteFood(id: number) {
    this.api.deletefood(id).subscribe({
      next: resp => {
        this.toast.success('Food deleted');
        this.loadData();
      },
      error: err => this.toast.error('Food cannot be deleted')
    });
  }
  

  editFood(food: any) {
    this.editMode = true;
    this.currentFoodId = food.id;
    this.fg.patchValue({
      fname: food.fname,
      category: food.category.id,
      descr: food.descr,
      price: food.price,
      id: food.id
    });
    this.filePath = `http://localhost:8080/${food.photo}`;
  }

  savefood(f: any) {
    this.submitted = true;
    if (this.fg.valid && (this.upload || this.editMode)) {
      let fd = new FormData();
      for (let ele in this.fg.value) {
        fd.append(ele, (<any>this.fg.get(ele).value));
      }
      if (this.image) {
        fd.append("pic", this.image);
      }

      if (this.editMode && this.currentFoodId) {
        this.api.updatefood(fd, this.currentFoodId).subscribe({
          next: resp => {
            this.toast.success("Food updated successfully");
            this.resetForm();
            this.loadData();
          },
          error: err => console.log(err.error)
        });
      } else {
        this.api.savefood(fd).subscribe({
          next: resp => {
            this.toast.success("Food saved successfully");
            this.resetForm();
            this.loadData();
          },
          error: err => console.log(err.error)
        });
      }
    }
  }

  resetForm() {
    this.fg.reset();
    this.submitted = false;
    this.upload = false;
    this.editMode = false;
    this.currentFoodId = null;
    this.filePath = "../assets/upload.png";
  }
}
