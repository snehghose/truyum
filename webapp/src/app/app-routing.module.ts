import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './site/login/login.component';
import { SignupComponent } from './site/signup/signup.component';
import { MenuComponent } from './food/menu/menu.component';
import { CartComponent } from './shopping/cart/cart.component';
import { ItemEditComponent } from './food/item-edit/item-edit.component';
import { NotFoundComponent } from './site/not-found/not-found.component';
import { AuthGuardService } from './site/auth-guard.service';

const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'signup',component:SignupComponent},
  {path:'menu',component:MenuComponent},
  {path:'cart',component:CartComponent, canActivate:[AuthGuardService]},
  {path:'itemEdit/:id',component:ItemEditComponent, canActivate:[AuthGuardService]},
  {path:'', redirectTo:'menu', pathMatch:'full'},
  {path:'**',component:NotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
