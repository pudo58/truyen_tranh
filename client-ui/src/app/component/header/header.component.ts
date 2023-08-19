import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Category} from "../../base/model/category.model";
import {CategoryService} from "../../base/service/category.service";

@Component({
	selector: 'app-header',
	templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit {
	public form: FormGroup;
	public categoryList: Category[] = [];
	public isLoading = false;

	constructor(private fb: FormBuilder,
				private categoryService: CategoryService) {
		this.form = this.fb.group({
			keyword: [null, Validators.required],
		})
	}

	ngOnInit(): void {
		this.initCategoryList();
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

	protected readonly indexedDB = indexedDB;
}
