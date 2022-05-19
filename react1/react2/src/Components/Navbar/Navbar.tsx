import React from 'react';

import {Link} from 'react-router-dom';

//import defaultImage from '../../defaultpic.jpg';

import './Navbar.css';


export const Navbar: React.FC = () => {
     const handleLogout = () => {
         
     }


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
