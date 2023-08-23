import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {DataStorageService} from "../../base/service/base/data-storage.service";
import {UserService} from "../../base/service/user.service";
import {ErrorException} from "../../base/model/base.model";

@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
	public formSubmitted = false;
	public form: FormGroup;
	public isLoading = false;
	public isReloadCaptcha = false;
	public readonly SITE_KEY = '6LfGY8InAAAAAJa5wYYTrGpQpplpcNe-G2kedFcZ';

	constructor(private fb: FormBuilder,
				private router: Router,
				private toastrService: ToastrService,
				private activatedRoute: ActivatedRoute,
				private dataStorageService: DataStorageService,
				private userService: UserService) {
		this.form = this.fb.group({
			email: [null, Validators.compose([Validators.required, Validators.maxLength(255), Validators.pattern(/^[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\.[a-zA-Z0-9_]+)+$/)])],
			username: [null, Validators.compose([Validators.required, Validators.maxLength(20)])],
			password: [null, Validators.compose([Validators.required, Validators.maxLength(20)])],
			rePassword: [null, Validators.compose([Validators.required, Validators.maxLength(20)])],
			recaptcha: ['', Validators.compose([Validators.required])]
		})
		this.dataStorageService.header = false;
		this.dataStorageService.footer = false;
	}

	get formControls() {
		return this.form.controls;
	}

	ngOnInit() {
		window.onload = () => {
			this.reloadGoogleCaptcha();
		};
	}

	onSubmit(): void {
		this.formSubmitted = true;
		if (this.form.invalid) {
			return;
		}
		this.isLoading = true;
		if (this.form.value.password !== this.form.value.rePassword) {
			this.formControls['rePassword'].setErrors({notMatch: true});
			this.isLoading = false;
		}
		this.userService.save(this.form.value).subscribe((data: any) => {
			if (data instanceof ErrorException) {
				this.toastrService.error(data.detail, 'Lỗi');
				this.isLoading = false;
			} else {
				this.toastrService.success('Đăng ký thành công', 'Thành công');
				this.router.navigateByUrl('login')
				this.isLoading = false;
			}
		});
	}

	reloadGoogleCaptcha() {
		const scriptElement = document.createElement('script');
		scriptElement.src = 'https://www.google.com/recaptcha/api.js?render=explicit&hl=vi';
		scriptElement.async = true;
		scriptElement.defer = true;
		document.body.appendChild(scriptElement);
		this.isReloadCaptcha = true;
	}

	handleReset() {
		this.form.reset();
		this.reloadGoogleCaptcha();
	}

	handleExpire() {
		this.formControls['recaptcha'].setErrors({expired: true});
		this.reloadGoogleCaptcha();
	}

	handleLoad() {
		this.isReloadCaptcha = false;
	}

	handleSuccess($event: any) {
		this.formControls['recaptcha'].setValue($event);
	}
}

