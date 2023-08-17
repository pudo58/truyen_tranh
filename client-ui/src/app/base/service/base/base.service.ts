import {Observable} from "rxjs";
import {User} from "../../model/user.model";

export interface BaseService<T> {
	findAll(): Observable<User[]>;
	findById(id: number): Observable<T>;
	save(t: T): Observable<T>;
	update(t: T): Observable<T>;
	delete(id: number): Observable<void>;
}
