import { Component } from '@angular/core';
import { Router, NavigationEnd, Event as RouterEvent } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'berkate';
  showHeaderFooter: boolean = true;
  showHeaderFooterregister: boolean = true;


  constructor(private router: Router) {
    this.router.events.pipe(
      // Filter for NavigationEnd events, ensuring correct type assertion
      filter((event: RouterEvent): event is NavigationEnd => event instanceof NavigationEnd)
    ).subscribe((event: NavigationEnd) => {
      // Here, TypeScript knows event is definitely NavigationEnd
      // Hide navbar and footer for login route
      this.showHeaderFooter = !event.urlAfterRedirects.includes('/login');
      this.showHeaderFooterregister = !event.urlAfterRedirects.includes('/register');

    });
  }
}
