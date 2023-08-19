import {BaseEntity} from "./base.model";

export class Category extends BaseEntity{
	name?: string;
	description?: string;
	parent?: Category;
}
