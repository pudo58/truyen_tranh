import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ToastrModule} from "ngx-toastr";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HeaderComponent} from "./component/header/header.component";
import {FooterComponent} from "./component/footer/footer.component";
import {CommonModule} from "@angular/common";
import {NgSelectModule} from '@ng-select/ng-select';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button'; // Import module của PrimeNG



@NgModule({
	declarations: [
		AppComponent,
		HeaderComponent,
		FooterComponent,
	],
	imports: [
		CommonModule,
		BrowserModule,
		AppRoutingModule,
		NgSelectModule,
		FormsModule,
		ButtonModule,
		HttpClientModule,
		TableModule,
		ReactiveFormsModule,
		ToastrModule.forRoot({
			timeOut: 5000,
			positionClass: 'toast-top-right',
			preventDuplicates: true,
			closeButton: true,
			progressBar: true,
			maxOpened: 5,
			autoDismiss: true,
			enableHtml: true,
			newestOnTop: true,
			onActivateTick: true,
			resetTimeoutOnDuplicate: true,
			tapToDismiss: true,
			titleClass: 'toast-title',
			messageClass: 'toast-message',
			iconClasses: {
				error: 'toast-error',
				info: 'toast-info',
				success: 'toast-success',
				warning: 'toast-warning',
			}
		}),
		BrowserAnimationsModule
	],
	providers: [],
	bootstrap: [AppComponent]
})
export class AppModule {
}
