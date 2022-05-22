import { IReim } from "./IReim";


export interface IUser {
    userId: number,
    firstName: string,
    lastName: string,
    username: string,
    email:string,
    password:string,
    privilege: boolean 
    
}