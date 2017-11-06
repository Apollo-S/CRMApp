import { Agreement } from './Agreement';
import { ClientAddress } from './ClientAddress';
import { ClientAccount } from './ClientAccount';

export class Client {
    public id: number;
    public title: string;
    public alias: string;
    public edrpou: string;
    public inn: string;
    public vatCertificate: string;
    public agreements: Agreement[];
    public addresses: ClientAddress[];
    public accounts: ClientAccount[];
}