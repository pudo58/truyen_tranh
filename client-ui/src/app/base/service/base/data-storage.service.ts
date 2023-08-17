import {EventEmitter, Injectable} from '@angular/core';

@Injectable({
	providedIn: 'root',
})
export class DataStorageService {
	showHeaderChange: EventEmitter<any> = new EventEmitter();
	showHeader: any;

	get header(): any {
		return this.showHeader;
	}
	set header(val: any) {
		this.showHeader = val;
		this.showHeaderChange.emit(val);
	}
}
