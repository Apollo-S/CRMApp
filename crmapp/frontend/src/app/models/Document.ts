import { DocumentType } from "./DocumentType";
import { Status } from "./Status";

export class Document {
    id?: number;
    docType?: DocumentType;
    number?: number;
    amount?: Number;
    dated?: string;
    paymentDate?: string;
    comment?: string;
    status: Status;
    agreementId?: number;
}
