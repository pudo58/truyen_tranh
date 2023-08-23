import { Component } from '@angular/core';
import {AuthService} from "../../base/service/auth.service";
import {DataStorageService} from "../../base/service/base/data-storage.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
	constructor(
		private dataStorageService: DataStorageService,
	) {
		this.dataStorageService.header = true;
		this.dataStorageService.footer = true;
	}
}
