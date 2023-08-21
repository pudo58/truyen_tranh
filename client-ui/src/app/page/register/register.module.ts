import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {SpinnerModule} from "../../component/spinner/spinner.module";
import {RegisterComponent} from "./register.component";
import {NgxCaptchaModule} from "ngx-captcha";

const routes: Routes = [
	{path: '', component: RegisterComponent},
];

@NgModule({
	imports: [
		CommonModule,
		RouterModule.forChild(routes),
		FormsModule,
		ReactiveFormsModule,
		SpinnerModule,
		NgxCaptchaModule
	],
	declarations: [
		RegisterComponent
	],
	providers: [],
})
export class RegisterModule {
}
