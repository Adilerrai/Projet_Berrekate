import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { AcceuilComponent } from './pages/acceuil/acceuil.component';
import { NavbarComponent } from './pages/navbar/navbar.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { AuthGuard } from './services/auth.guard';
import { AddrestaurantComponent } from './pages/restaurant/addrestaurant/addrestaurant.component';
import { ListrestaurantComponent } from './pages/restaurant/listrestaurant/listrestaurant.component';


const routes: Routes = [

  {
    path: '',
    component: AcceuilComponent,
    children: [
    
      {
        path: 'auth',
        loadChildren: () => import('./pages/auth/auth.module').then(m => m.AuthModule)
      },
    ]
   
  },
  {
    path: 'addrestaurant',
    component: AddrestaurantComponent,
  },
  {
    path: 'listerestaurant',
    component: ListrestaurantComponent,
  },

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
