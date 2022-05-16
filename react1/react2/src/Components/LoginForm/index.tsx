import React, {useState} from 'react';
import "./LoginForm.css";
import { useDispatch } from 'react-redux';
import { toggleError } from '../../Slices/UserSlice';


export const Login: React.FC = () => {

    const [email, setEmail] = useState<string>("");
    const [password, setPassword] = useState<string>("");

   const dispatch = useDispatch();

    const handleInput = (event:React.ChangeEvent<HTMLInputElement>) => {
        if(event.target.name === "email"){
            setEmail(event.target.value);
        }
        else {
            setPassword(event.target.value);
        }
        }

    
    const handleLogin = (event:React.MouseEvent<HTMLButtonElement>) => {
        console.log("Login user: ", {
            email,
            password
        });

        
    }
    return(
        <div className="login">
            <div className="text-container">
                <h1 className="login-header">Welcome to your reimburement website </h1>
                <h2 className="login-header">Please sign in to submit a reimbursement</h2>
            </div>
            <form className="login-form">
                <div className="input-div">
                  <h4 className="input-h4">Enter Email</h4>
                    <input autoComplete="off" className="login-input" type="text" name="email" placeholder="email" onChange={handleInput}/>
                </div>
                <div className="input-div">
                    <h4 className="input-h4">Enter Password</h4>
                    <input className="login-input" type="password" name="password" placeholder="password" onChange={handleInput}/>
                </div>
                <div className="input-div">

                </div>
            </form>
                <button className="login-button" onClick={handleLogin}>Login</button>
        </div>
    )
}