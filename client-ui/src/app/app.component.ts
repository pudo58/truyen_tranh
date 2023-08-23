import { AfterViewInit, Component, OnInit } from '@angular/core';
import { DataStorageService } from './base/service/base/data-storage.service';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
})
export class AppComponent implements OnInit, AfterViewInit {
	public showHeader: boolean = true;
	public showFooter: boolean = true;

	constructor(private dataStorageService: DataStorageService) {}

	ngOnInit(): void {
		// Thiết lập giá trị mặc định cho showHeader tại đây
	}

	ngAfterViewInit(): void {
		this.initUI();
	}

	initUI() {
		this.dataStorageService.showHeaderChange.subscribe((val) => {
			this.showHeader = val;
		});
		this.dataStorageService.showFooterChange.subscribe((val) => {
			this.showFooter = val;
		});
	}
}
