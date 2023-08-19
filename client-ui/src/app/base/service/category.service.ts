import {BaseService} from "./base/base.service";
import {Category} from "../model/category.model";
import {BASE_URL} from "../model/base.model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class CategoryService implements BaseService<Category> {
	public url = BASE_URL + '/category';

	constructor(private http: HttpClient) {
	}

	delete(id: number): Observable<void> {
		return this.http.delete<void>(this.url + '/' + id);
	}

	findAll(): Observable<Category[]> {
		return this.http.get<Category[]>(this.url + '/findAll');
	}

	findById(id: number): Observable<Category> {
		return this.http.get<Category>(this.url + '/' + id);
	}

	save(t: Category): Observable<Category> {
		return this.http.post<Category>(this.url + '/save', t);
	}

	update(t: Category): Observable<Category> {
		return this.http.put<Category>(this.url + '/update', t);
	}

}
