import { DocumentType } from "./DocumentType";
import { DocumentStatus } from "./DocumentStatus";

export class Document {
    public id?: number;
    public docType?: DocumentType;
    public number?: number;
    public amount?: Number;
    public dated?: string;
    public paymentDate?: string;
    public comment?: string;
    public docStatus?: DocumentStatus;
    public agreementId?: number;
    public agreementNumber?: string;
}
