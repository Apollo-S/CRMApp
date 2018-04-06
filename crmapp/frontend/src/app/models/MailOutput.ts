import { MailDocumentType } from "./MailDocumentType";

export class MailOutput {
    public id?: number;
    public number?: string;
    public receiver?: string;
    public dated?: Date;
    public comment?: string;
    public docType?: MailDocumentType;
    public docTypeTitle?: string;
    public url?: string;
}