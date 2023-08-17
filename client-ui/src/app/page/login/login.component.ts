import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../base/service/auth.service";
import {ToastrService} from "ngx-toastr";
import {DataStorageService} from "../../base/service/base/data-storage.service";

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
	public formSubmitted = false;
	public form: FormGroup;
	public isLoading = false;

	constructor(private fb: FormBuilder,
				private router: Router,
				private toastrService: ToastrService,
				private activatedRoute: ActivatedRoute,
				private dataStorageService: DataStorageService,
				private authService: AuthService) {
		this.form = this.fb.group({
			username: [null, Validators.compose([Validators.required, Validators.maxLength(20)])],
			password: [null, Validators.compose([Validators.required, Validators.maxLength(20)])],
			rememberMe: false,
		})
		this.dataStorageService.header = false;
	}

	get formControls() {
		return this.form.controls;
	}

	ngOnInit() {

	}

	onSubmit(): void {
		this.formSubmitted = true;
		if (this.form.invalid) {
			return;
		}
		this.isLoading = true;
		this.authService.auth(this.form.value).subscribe(data => {
			if(data){
				if(data?.accessToken || data?.refreshToken) {
					document.cookie = `SESSION=${data.accessToken || data.refreshToken}; path=/;`;
					this.toastrService.success('Đăng nhập thành công', 'Thành công');
					this.router.navigate(['/']);
					this.isLoading = false;
				}else {
					this.toastrService.error('Thông tin tài khoản hoặc mật khẩu không chính xác', 'Thất bại');
					this.isLoading = false;
				}
			}
		});
	}

}
