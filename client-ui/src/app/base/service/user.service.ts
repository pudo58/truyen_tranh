import {BASE_URL} from "../model/base.model";
import {HttpClient} from "@angular/common/http";
import {BaseInterface} from "./base.interface";
import {User} from "../model/user.model";
import {Observable} from "rxjs";

export class UserService implements BaseInterface<User> {
	public url = BASE_URL + '/user';

	constructor(private http: HttpClient) {
	}

	findAll(): Observable<User[]> {
		return this.http.get<User[]>(this.url);
	}

	findById(id: number): Observable<User> {
		return this.http.get<User>(this.url + '/' + id);
	}

	save(t: User): Observable<User> {
		return this.http.post<User>(this.url, t);
	}

	update(t: User): Observable<User> {
		return this.http.put<User>(this.url + '/' + t.id, t);
	}

	delete(id: number): Observable<void> {
		return this.http.delete<void>(this.url + '/' + id);
	}
}
