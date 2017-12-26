import { DocumentType } from "./DocumentType";
import { Status } from "./Status";

export class Document {
    public id?: number;
    public docType?: DocumentType;
    public number?: number;
    public amount?: Number;
    public dated?: string;
    public paymentDate?: string;
    public comment?: string;
    public status?: Status;
    public agreementId?: number;
}
