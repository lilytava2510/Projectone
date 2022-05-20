import React, { useEffect } from 'react';
import {Navbar } from '../../Components/Navbar/Navbar';
import { useSelector } from 'react-redux';
import { RootState }  from '../../Store';
import {useNavigate} from 'react-router-dom';
import { Loading } from '../../Components/Loading/Loading';
import { Reim } from '../../Components/Reim/Reim';
import { IReim } from '../../Interface/IReim';
export const ReimPage: React.FC = () => {

    const userInfo = useSelector((state:RootState) => state.user);
    const navigator = useNavigate();
      useEffect(() => {
          if(!userInfo.user){

            navigator("/login");

          }
      },[userInfo,Reim]);
        
      const checkForContent:any = () => {

        if(userInfo.user?.Reim){
            userInfo.user.Reim.map((post:IReim)=> {
                return <Reim {...post} />
            });
        } else {

        return (
            <Loading />
        )

      }
      }
    return(
        <>
           <Navbar />
           <h1> Welcome: {userInfo.user?.firstName} {userInfo.user?.lastName}</h1>
           <h2> ReimPage</h2>
           {checkForContent()};
           
           
        </>
    )

    }