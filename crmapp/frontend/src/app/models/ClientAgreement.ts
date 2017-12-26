import { Document } from "./Document";

export class ClientAgreement {
    public id?: number;
    public clientId?: number;
    public number?: string;
    public dateStart?: string;
    public documents?: Document[];
    public comment?: string;
}