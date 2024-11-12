import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  
BASEURL:string="http://localhost:8080/api/";
  updateCategory: any;

  constructor(private http:HttpClient) { }

  validate(data:any){
    return this.http.post<any>(this.BASEURL+"customers/validate",data);
  }

  validateadmin(data:any){
    return this.http.post<any>(this.BASEURL+"admin/validate",data);
  }

  //categories
  savecategory(data:any){
    return this.http.post<any>(this.BASEURL+"categories",data);
  }

  listcategories(){
    return this.http.get<any[]>(this.BASEURL+"categories");
  }

  deletecategory(id:number){
    return this.http.delete<any>(this.BASEURL+"categories/"+id)
  }

  updatecategory(category: any) {
    return this.http.put<any>(`${this.BASEURL}categories/${category.id}`, category);
  }



  //customer
  register(data:any){
    return this.http.post<any>(this.BASEURL+"customers",data);
  }

  listcustomers(){
    return this.http.get<any[]>(this.BASEURL+"customers")
  }
  
  getcustomerdetails(id:any){
    return this.http.get<any>(this.BASEURL+"customers/"+id)
  }

  updateCustomerDetails(data: any) {
    return this.http.put<any>(`${this.BASEURL}customers/${data.id}`, data);
  }
  

  deleteUser(userId: number) {
    return this.http.delete<any>(`${this.BASEURL}customers/${userId}`);
  }

  savefood(data:any){
    return this.http.post<any>(this.BASEURL+"foods",data);
  }

  listfoods(){
    return this.http.get<any[]>(this.BASEURL+"foods")
  }

  catfoods(catid:number){
    return this.http.get<any[]>(this.BASEURL+"foods/cats?catid="+catid)
  }

  searchfoods(search:string){
    return this.http.get<any[]>(this.BASEURL+"foods?search="+search)
  }
  
  deletefood(id:number){
    return this.http.delete<any>(this.BASEURL+"foods/"+id)
  }
  
  
  updatefood(data: any, id: number) {
    return this.http.put<any>(`${this.BASEURL}foods/${id}`, data);
  }
  

  //wishlist
  addtowishlist(data:any){
    return this.http.post<any>(this.BASEURL+"wishlist",data)
  }

  getwishlist(id:any){
    return this.http.get<any[]>(this.BASEURL+"wishlist?custid="+id)
  }

  deletefromwishlist(id:any){
    return this.http.delete<any>(this.BASEURL+"wishlist/"+id)
  }

  //cart
  addtocart(data:any){
    return this.http.post<any>(this.BASEURL+"cart",data)
  }

  getcart(id:any){
    return this.http.get<any[]>(this.BASEURL+"cart?custid="+id)
  }

  deletefromcart(id:any){
    return this.http.delete<any>(this.BASEURL+"cart/"+id)
  }

  updateqty(cartid:number,qty:number){
    return this.http.get<any>(this.BASEURL+"cart/updateqty?cartid="+cartid+"&qty="+qty);
  }

  //orders
  
  placeorder(data:any){
    return this.http.post<any>(this.BASEURL+"orders",data);
  }

  orderhistory(custid:any){
    return this.http.get<any[]>(this.BASEURL+"orders?custid="+custid);
  }
  
  
  allorders(){
    return this.http.get<any[]>(this.BASEURL+"orders");
  }

  
}
