import {IUser } from "./IUser";


export interface IReim {
    reimburse_id?: number,
    amount?: number,
    start?: string,
    end?: string,
    description?: string,
    author?: number,
    manager?: number,
    status?: number,
    type?: number
}