import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './site/header/header.component';
import { LoginComponent } from './site/login/login.component';
import { SignupComponent } from './site/signup/signup.component';
import { SearchComponent } from './food/search/search.component';
import { ItemInfoComponent } from './food/item-info/item-info.component';
import { ItemEditComponent } from './food/item-edit/item-edit.component';
import { MenuComponent } from './food/menu/menu.component';
import { HttpClientModule } from '@angular/common/http';
import { FoodService } from './food/food.service';
import { CartComponent } from './shopping/cart/cart.component';
import { NotFoundComponent } from './site/not-found/not-found.component';
import { AuthGuardService } from './site/auth-guard.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    SignupComponent,
    SearchComponent,
    ItemInfoComponent,
    ItemEditComponent,
    MenuComponent,
    CartComponent,
    NotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [FoodService],
  bootstrap: [AppComponent]
})
export class AppModule { }
