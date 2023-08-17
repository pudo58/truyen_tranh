import {AuthModel, BASE_URL} from '../model/base.model';
import {HttpClient} from '@angular/common/http';
import {AuthRequest} from '../model/user.model';
import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class AuthService {
	public url = BASE_URL + '/v2/auth';

	constructor(private http: HttpClient) {
	}

	auth(model: AuthRequest) {
		return this.http.post<AuthModel>(`${this.url}`, model);
	}

}
