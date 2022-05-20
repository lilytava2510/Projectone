import React from "react";

import {IReim} from '../../Interface/IReim';



export const Reim:React.FC<IReim> = (post:IReim) => {

    return(
        <div className="reim" >
            <div className="reim-profile">
                <h2 className="reim-user">{post.reimUser?.firstName} {post.reimUser?.lastName}</h2>

            </div>
<div className="reim-ticket" >
    <p>{post.amount} </p>
</div>
        </div>
    )
}