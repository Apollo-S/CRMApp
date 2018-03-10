import { Document } from "./Document";

export class ClientAgreement {
    public id?: number;
    public clientId?: number;
    public clientAlias?: string;
    public number?: string;
    public dateStart?: Date;
    public comment?: string;
    public url?: string;
}