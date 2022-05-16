import React from 'react';

import { Login } from '../../Components/LoginForm';

import "./LoginPage.css";

export const LoginPage: React.FC = () => {

    return(
        <div className="login-page">
          <Login />
        </div>
    )

}