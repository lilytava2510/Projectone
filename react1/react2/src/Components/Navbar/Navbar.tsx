import React from 'react';
import {useSelector} from 'react-redux';
import {Link} from 'react-router-dom';
import {RootState} from'../../Store';
//import defaultImage from '../../defaultpic.jpg';

import './Navbar.css';


export const Navbar: React.FC = () => {

     const handleLogout = () => {

     }

     const user = useSelector((state:RootState) => state.user.user);

    return( 
        <nav className="navbar">
       
            <ul className="nav-menu">
                <li className="nav-item">
                    <Link to= {"/feed"} className="nav-link">Home</Link>
                </li>
                <li className="nav-item">
                    <Link to= {`/user/${user?.userId}`} className="nav-link">User info/updateinfo</Link>
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
