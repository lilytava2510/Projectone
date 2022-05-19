import React from 'react';
import {Navbar } from '../../Components/Navbar/Navbar';
import { useSelector } from 'react-redux';
import { RootState }  from '../../Store';

export const FeedPage: React.FC = () => {

    const userInfo = useSelector((state:RootState) => state.user);
      
    return(
        <>
           <Navbar />
           <h1> Welcome: {userInfo.user?.firstName}</h1>
           <h2> FeedPage</h2>

        </>
    )

    }