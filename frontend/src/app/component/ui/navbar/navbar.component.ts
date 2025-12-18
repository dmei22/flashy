import {Component, signal} from '@angular/core';
import {RouterLink} from "@angular/router";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-navbar',
    imports: [
        RouterLink,
        NgForOf,
    ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

    protected navItems = signal([
        { label: "Home", routerLink: "/" },
        { label: "Decks", routerLink: "/decks" },
    ]);
}
