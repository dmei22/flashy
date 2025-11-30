import { Component } from '@angular/core';
import {NavbarComponent} from "./component/navbar/navbar.component";
import {RouterOutlet} from "@angular/router";
import {BreadcrumbComponent} from "./component/breadcrumb/breadcrumb.component";

@Component({
  selector: 'app-root',
    imports: [
        NavbarComponent,
        RouterOutlet,
    ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'flashy';
}
