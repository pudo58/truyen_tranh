import {AfterViewInit, Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Category} from "../../base/model/category.model";
import {CategoryService} from "../../base/service/category.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
	selector: 'app-header',
	templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit, AfterViewInit {
	public form: FormGroup;
	public categoryList: Category[] = [];
	public isLoading = false;
	public isLogin = false;

	constructor(private fb: FormBuilder,
				private router: Router,
				private toastrService: ToastrService,
				private categoryService: CategoryService) {
		this.form = this.fb.group({
			keyword: [null, Validators.required],
		})
	}

	ngOnInit(): void {
		this.initCategoryList();
	}

	ngAfterViewInit(): void {
		this.checkLogin();
	}

	search() {
		if (this.form.invalid) {
			return;
		}
	}

	initCategoryList() {
		this.isLoading = true;
		this.categoryService.findAll().subscribe(res => {
			this.categoryList = res;
			this.isLoading = false;
		});
	}

	checkLogin() {
		if (document.cookie.indexOf('SESSION') > -1) {
			this.isLogin = true;
		}
	}

	logout() {
		// check if cookie exist then delete it
		if(confirm('Bạn có chắc chắn muốn đăng xuất không ?')) {
			if (document.cookie.indexOf('SESSION') > -1) {
				document.cookie = `SESSION=; path=/; expires=Thu, 01 Jan 1970 00:00:01 GMT;`;
				this.isLogin = false;
				this.toastrService.success('Đăng xuất thành công', 'Thành công');
				this.router.navigateByUrl('');
			}
		}
	}
}
