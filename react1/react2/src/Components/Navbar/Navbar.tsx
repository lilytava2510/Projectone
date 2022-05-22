import React from 'react';
import {useDispatch, useSelector} from "react-redux";
import {Link} from 'react-router-dom';
import {RootState, AppDispatch} from "../../Store";
import { clearReim } from '../../Slices/ReimSlice';
import { clearUser } from '../../Slices/UserSlice';
//import defaultImage from '../../defaultpic.jpg';

import './Navbar.css';


export const Navbar: React.FC = () => {

    const dispatch:AppDispatch = useDispatch();

    const handleLogout = () => {
         dispatch(clearReim());
         dispatch(clearUser());
     }
     const user = useSelector((state:RootState) => state.user.user);

    return( 
        <nav className="navbar">
       
            <ul>
                <li className="nav-item">
                    <Link to= {"/feed"} className="nav-link">Profile</Link>
                </li>
                <li className="nav-item">
                    <Link to= {"/feed"} className="nav-link">Home</Link>
                </li>
                <li className="logout">
                    <Link to={"/login"}>
                        <button className="logout-btn" onClick={handleLogout}>Log out</button>
                    </Link>
                </li>
            </ul>
        </nav>
        
    )

}