import React, {useEffect} from 'react';
import {Navbar } from '../../Components/Navbar/Navbar';
import { useSelector, useDispatch} from 'react-redux';
import { RootState, AppDispatch}  from '../../Store';
import {useNavigate} from "react-router-dom";
import {Loading} from "../../Components/Loading/Loading";
import {Reim} from "../../Components/Reim/Reim";
import {IUser} from "../../Interface/IUser";
import {IReim} from "../../Interface/IReim";
import {getReim} from "../../Slices/ReimSlice";

export const FeedPage: React.FC = () => {

    const userInfo = useSelector((state:RootState) => state.user);
    const reimState = useSelector ((state :RootState) => state.reim);
    const dispatch:AppDispatch = useDispatch();
    const navigator = useNavigate();
        useEffect(() => {
        if(!userInfo.user){
          navigator("/login");

        }
        else if(userInfo.user && !reimState.reimburse){
            dispatch(getReim(userInfo.user.userId));

        }
    },[userInfo.user, reimState.reimburse]);
    console.log(userInfo.user?.privilege)
    return(
        <>
           <Navbar />
           <h1> Welcome: {userInfo.user?.firstName}</h1>
           <h2> FeedPage</h2>
           <table>
               <tr>
                   <th>Reimbursement #</th>
                   <th>Reimbursement amount</th>
                   <th>Subimtion Date</th>
                   <th>Resolution Date</th>
                   <th>Reimbursement Description</th>
                   <th>Reimbursement Author</th>
                   <th>Reimbursement Manager</th>
                   <th>Reimbursement Status</th>
                   <th>Reimbursement Type</th>
               </tr>
           {reimState.reimburse?
            reimState.reimburse.map((post:IReim)=> {
                return <Reim {...post} key={post.reimburse_id}/>
            }): <Loading/>
        } 
           </table>
        </>
    )

    }
