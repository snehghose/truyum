import { Component, OnInit } from '@angular/core';
import { FoodService } from '../food.service';
import { MenuItemService } from 'src/app/services/menu-item.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit{

  constructor(private foodService:FoodService, private menuItemService:MenuItemService) { }

  onSearchText(event:any){
    this.menuItemService.filter.next({title:event.target.value})
  }

  ngOnInit(){}
}
