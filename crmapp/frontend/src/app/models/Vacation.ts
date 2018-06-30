import { Employee } from "./Employee";

export class Vacation {
    public id?: number;
    public employee?: Employee;
    public employeeId?: number;
    public employeeShortName?: string;
    public description?: string;
    public dateStart?: Date;
    public dateFinal?: Date;
    public fullPeriod?: string;
    public daysAmount?: number;
    public holidayAmount?: number;
    public comment?: string;
    public url?: string;
}
