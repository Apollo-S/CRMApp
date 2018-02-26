import { Person } from './Person';
import { Post } from './Post';

export class Employee {
    public id?: number;
    public person?: Person;
    public personShortName?: string;
    public personInn?: string;
    public entrepreneur?: boolean;
    public hireDate?: Date;
    public firedDate?: Date;
    public post?: Post;
    public postTitle?: string;
    public url?: string;
}