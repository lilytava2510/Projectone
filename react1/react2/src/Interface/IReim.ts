import {IUser } from "./IUser";


export interface IReim {
    reimburse_id?: number,
    amount?: number,
    start?: string,
    end?: string,
    author?: number,
    manager?: number,
    status?: number,
    type?: number
    reimUser?: IUser
}

