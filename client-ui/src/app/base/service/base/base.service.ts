import {Observable} from "rxjs";
import {User} from "../../model/user.model";
import {HttpClient} from "@angular/common/http";

export interface BaseService<T> {
	findAll(): Observable<User[]>;

	findById(id: number): Observable<T>;

	save(t: T): Observable<T>;

	update(t: T): Observable<T>;

	delete(id: number): Observable<void>;
}

export class BaseServiceImpl<T> implements BaseService<T> {

	constructor(private http: HttpClient, private url: string) {
	}


	delete(id: number): Observable<void> {
		return this.http.delete<void>(this.url + '/delete/' + id);
	}

	findAll(): Observable<User[]> {
		return this.http.get<User[]>(this.url + '/findAll');
	}

	findById(id: number): Observable<T> {
		return this.http.get<T>(this.url + '/findById/' + id);
	}

	save(t: T): Observable<T> {
		return this.http.post<T>(this.url + '/save', t);
	}

	update(t: T): Observable<T> {
		return this.http.put<T>(this.url + '/update', t);
	}

}
