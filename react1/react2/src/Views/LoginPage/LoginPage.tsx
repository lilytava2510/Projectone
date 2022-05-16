import React, { useEffect } from 'react';

import { useSelector }  from 'react-redux';
import { RootState, AppDispatch } from '../../Store';

import { Login } from '../../Components/LoginForm';

import "./LoginPage.css";

export const LoginPage: React.FC = () => {

    const userState = useSelector ((state :RootState) => state.user);

     useEffect(()=> {
          console.log("User State: ", userState);
     }, [userState]);

    return(
        <div className="login-page">
          <Login />
        </div>
    )

}