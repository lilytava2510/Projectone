import React, { useEffect } from 'react';
import { Navbar } from '../../Components/Navbar/Navbar';
import { useSelector, useDispatch } from 'react-redux';
import { RootState, AppDispatch }  from '../../Store';
import { useNavigate} from 'react-router-dom';
import { Loading } from '../../Components/Loading/Loading';
import { Reim } from '../../Components/Reim/Reim';
import { IUser } from '../../Interface/IUser';
import { IReim } from '../../Interface/IReim';
import { getReim } from '../../Slices/ReimSlice';
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
              dispatch(getReim() )

          }
      },[userInfo.user, reimState.reimburse]);

      const checkForContent:any = () => {

      }
      
    return(
        <>
           <Navbar />
           <h1> Welcome: {userInfo.user?.firstName} {userInfo.user?.lastName}</h1>
           <h2> ReimPage</h2>
           <div>
           {reimState.reimburse?
            reimState.reimburse.map((post:IReim)=> {
                return <Reim {...post} key={post.reimburse_id}/>
            }): <Loading/>
        } 
           </div>
           
        </>
    )

    }
