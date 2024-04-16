import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';
import { ApidbService } from './apidb.service';


@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private apidb: ApidbService) {
  }
  canActivate(): boolean {
    if (!this.apidb.isAuthenticated()) {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }
}
