import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
	selector: 'app-header',
	templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit {
	public form: FormGroup;

	constructor(private fb: FormBuilder) {
		this.form = this.fb.group({
			keyword: [null, Validators.required],
		})
	}

	ngOnInit(): void {
	}

	search() {
		if(this.form.invalid) {
			return;
		}
		console.log(this.form.value);
	}
}
