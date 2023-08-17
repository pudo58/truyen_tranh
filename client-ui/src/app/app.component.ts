import { AfterViewInit, Component, OnInit } from '@angular/core';
import { DataStorageService } from './base/service/base/data-storage.service';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
})
export class AppComponent implements OnInit, AfterViewInit {
	public showHeader: boolean = true;

	constructor(private dataStorageService: DataStorageService) {}

	ngOnInit(): void {
		// Thiết lập giá trị mặc định cho showHeader tại đây
	}

	ngAfterViewInit(): void {
		this.initHeader();
	}

	initHeader() {
		this.dataStorageService.showHeaderChange.subscribe((val) => {
			this.showHeader = val;
		});
	}
}
