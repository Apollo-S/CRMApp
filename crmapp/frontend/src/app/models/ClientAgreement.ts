import { Document } from "./Document";
import { Client } from "./Client";

export class ClientAgreement {
    public id?: number;
    public client?: Client;
    public clientId?: number;
    public clientAlias?: string;
    public clientTitle?: string;
    public number?: string;
    public dateStart?: Date;
    public comment?: string;
    public url?: string;
}
