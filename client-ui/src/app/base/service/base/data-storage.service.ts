import {EventEmitter, Injectable} from '@angular/core';

@Injectable({
	providedIn: 'root',
})
export class DataStorageService {
	showHeaderChange: EventEmitter<any> = new EventEmitter();
	showHeader: any;
	showFooterChange: EventEmitter<any> = new EventEmitter();
	showFooter: any;

	get header(): any {
		return this.showHeader;
	}
	set header(val: any) {
		this.showHeader = val;
		this.showHeaderChange.emit(val);
	}

	get footer(): any {
		return this.showFooter;
	}

	set footer(val: any) {
		this.showFooter = val;
		this.showFooterChange.emit(val);
	}
}
