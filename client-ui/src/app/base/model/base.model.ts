export const BASE_URL = 'http://localhost:9999/api';

export class AuthModel {
	accessToken?: string;
	refreshToken?: string;
}

export class BaseEntity {
	id?: number;
	modifiedDate?: Date;
	createdDate?: Date;
}
