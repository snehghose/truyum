import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FoodService } from '../food.service';
import { MenuItem } from '../food.model';
import { MenuItemService } from 'src/app/services/menu-item.service';

@Component({
  selector: 'app-item-edit',
  templateUrl: './item-edit.component.html',
  styleUrls: ['./item-edit.component.css']
})
export class ItemEditComponent implements OnInit {
  editForm:FormGroup;
  productEdited=false
  error:string
  quantity:number
  id:number
  image:string
  updatedItem:MenuItem
  categories:string[]=['Main Course','Starters','Dessert']
  constructor(private route:ActivatedRoute,private menuItemService:MenuItemService, private foodService:FoodService, private router:Router) { }

  ngOnInit() {
    this.editForm=new FormGroup({
      'itemName':new FormControl(null,[Validators.required, Validators.maxLength(20)]),
      'price':new FormControl(null,[Validators.required,Validators.pattern('^[0-9]+$')]),
      'dateOfLaunch':new FormControl(null,Validators.required),
      'category':new FormControl(null,Validators.required),
      'isActive':new FormControl(null,Validators.required),
      'freeDelivery':new FormControl(null,Validators.required)
    })
    this.route.params.subscribe((params:Params)=>{
      const itemId=params['id']
      this.foodService.getItem(itemId).subscribe((menuItem:MenuItem)=>{
        if(menuItem){
          this.id=menuItem.id
          this.image=menuItem.imagePath
          this.editForm.patchValue({
            itemName:menuItem.itemName,
            price:menuItem.price,
            category:menuItem.category,
            dateOfLaunch:menuItem.dateOfLaunch,
            isActive:menuItem.isActive,
            freeDelivery:menuItem.freeDelivery
          })
          this.quantity=menuItem.quantity
        }
        else{
          this.router.navigate(['not-found'])
        }
      })

    })
  }

  onSubmitEditForm(){
    console.log(this.editForm)
    this.updatedItem={
      id:this.id,
      itemName:this.editForm.value.itemName,
      price:this.editForm.value.price,
      category:this.editForm.value.category,
      dateOfLaunch:this.editForm.value.dateOfLaunch,
      isActive:this.editForm.value.isActive,
      freeDelivery:this.editForm.value.freeDelivery,
      imagePath:this.image,
      quantity:this.quantity
    }
    console.log(this.updatedItem);
    
    this.productEdited=true
    this.menuItemService.updateMenuItem(this.updatedItem).subscribe(data=>{
      console.log("subscribed");
    })
  }

}
