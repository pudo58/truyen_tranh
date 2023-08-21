import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
	{path: 'login', loadChildren: () => import('./page/login/login.module').then(m => m.LoginModule)},
	{path: 'home', loadChildren: () => import('./page/home/home.module').then(m => m.HomeModule)},
	{path: 'register', loadChildren: () => import('./page/register/register.module').then(m => m.RegisterModule)},
];

@NgModule({
	imports: [
		RouterModule.forRoot(routes)
	],
	exports: [RouterModule]
})
export class AppRoutingModule {
}
