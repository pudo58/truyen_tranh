import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LoginComponent} from "./login.component";
import {SpinnerModule} from "../../component/spinner/spinner.module";

const routes: Routes = [
	{path: '', component: LoginComponent, data: {showHeader: false}},
];

@NgModule({
	imports: [
		CommonModule,
		RouterModule.forChild(routes),
		FormsModule,
		ReactiveFormsModule,
		SpinnerModule
	],
	declarations: [
		LoginComponent
	],
	providers: [],
})
export class LoginModule {
}
