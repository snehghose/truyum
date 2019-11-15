import { Component, Input, Output, EventEmitter } from '@angular/core';
import { MenuItem } from '../food.model';
import { AuthService } from 'src/app/site/auth.service';

@Component({
  selector: 'app-item-info',
  templateUrl: './item-info.component.html',
  styleUrls: ['./item-info.component.css']
})
export class ItemInfoComponent {
  @Input() menuItem: MenuItem;
  @Output() addedToCart: EventEmitter<number> = new EventEmitter<number>()
  itemAdded = false

  constructor(private authService: AuthService) { }

  onAddToCart(productId: number) {
    this.addedToCart.emit(productId)
    console.log(productId);
    
    this.itemAdded = true
    setTimeout(() => {
      this.itemAdded = false
    }, 1000)
    return false
  }

  isEditAllowed() {
    return this.authService.loggedIn && this.authService.isAdmin
  }

}
