import { Post } from './Post';

export class Employee {
    public id?: number;
    public surname?: string;
    public firstname?: string;
    public lastname?: string;
    public shortName?: string;
    public birthDate?: string;
    public inn?: string;
    public entrepreneur?: boolean;
    public hireDate?: string;
    public firedDate?: string;
    public post?: Post;
}