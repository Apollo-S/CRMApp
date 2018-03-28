import { DocumentType } from "./DocumentType";
import { DocumentStatus } from "./DocumentStatus";

export class Document {
    public id?: number;
    public docType?: DocumentType;
    public docTypeShortTitle?: string;
    public number?: number;
    public amount?: number;
    public dated?: string;
    public paymentDate?: string;
    public comment?: string;
    public status?: DocumentStatus;
    public docStatus?: string;
    public agreementId?: number;
    public agreementNumber?: string;
    public url?: string;
}
