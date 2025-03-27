import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Customer} from "../model/customer.model";
import {JsonPipe} from "@angular/common";

@Component({
  selector: 'app-customer-accounts',
  standalone: true,
  imports: [
    JsonPipe
  ],
  templateUrl: './customer-accounts.component.html',
  styleUrl: './customer-accounts.component.css'
})
export class CustomerAccountsComponent implements  OnInit{
  customerId!:string;
  customer!:Customer;
  constructor(private route:ActivatedRoute,
              private router:Router) {
    this.customer=this.router.getCurrentNavigation()?.extras.state as Customer;
  }
  ngOnInit() {
    this.customerId=this.route.snapshot.params['id'];
  }

}
