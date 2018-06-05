import { MailDocumentType } from "./MailDocumentType";

export class MailInput {
    public id?: number;
    public number?: string;
    public sender?: string;
    public dated?: Date;
    public comment?: string;
    public docType?: MailDocumentType;
    public docTypeTitle?: string;
    public url?: string;
}
