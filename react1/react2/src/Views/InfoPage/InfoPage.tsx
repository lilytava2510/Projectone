import React from 'react';


import { useParams } from 'react-router-dom';




export const InfoPage :React.FC = () => {


 const { id } = useParams();

 return(
     <h1>Personal Info {id}</h1>
 )

}