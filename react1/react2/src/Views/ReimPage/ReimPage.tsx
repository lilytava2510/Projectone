import React, { useEffect } from 'react';
import {Navbar } from '../../Components/Navbar/Navbar';
import { useSelector } from 'react-redux';
import { RootState }  from '../../Store';
import {useNavigate} from 'react-router-dom';
import { Loading } from '../../Components/Loading/Loading';
import { Reim } from '../../Components/Reim/Reim';
export const ReimPage: React.FC = () => {

    const userInfo = useSelector((state:RootState) => state.user);
    const navigator = useNavigate();
      
      const checkForContent:any = () => {

        if(userInfo.user.)

        return (
            <Loading />
        )

      }
    
    return(
        <>
           <Navbar />
           <h1> Welcome: {userInfo.user?.firstName}</h1>
           <h2> ReimPage</h2>

           
        </>
    )

    }