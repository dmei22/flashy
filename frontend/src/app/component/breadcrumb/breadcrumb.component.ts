import {Component, input} from '@angular/core';
import {BreadCrumbItem} from "../../model/BreadCrumbItem";
import {NgForOf} from "@angular/common";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-breadcrumb',
  imports: [
    NgForOf,
    RouterLink
  ],
  templateUrl: './breadcrumb.component.html',
  styleUrl: './breadcrumb.component.css'
})
export class BreadcrumbComponent {
  items = input.required<BreadCrumbItem[]>();
}
