export class User {
	id?: number;
	username?: string;
	password?: string;
	email?: string;
	lever?: number;
	status?: number;
	modifiedDate?: Date;
	createdDate?: Date;
}

export class AuthRequest {
	username?: string;
	password?: string;
	rememberMe?: boolean;
}

export interface RouterData {
	showHeader?: boolean;
	showFooter?: boolean;
	showSidebar?: boolean;
	showNavbar?: boolean;
}
