import {IUser } from "./IUser";


export interface IReim {
    reimburse_id: number,
    amount: number,
    submission: Date,
    start: string,
    end: string,
    resolution: Date,
    author: number,
    manager: number,
    status: number,
    type: number
    reimUser: IUser
}

