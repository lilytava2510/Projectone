import {createSlice, createAsyncThunk} from "@reduxjs/toolkit";
import {IUser} from "../Interface/IUser";

interface UserSliceState {
    loading: boolean,
    error: boolean,
    user?: IUser
}


const initialUserState: UserSliceState = {
    loading: false,
    error: false
}

export const UserSlice = createSlice({
    name: "user", 
    initialState: initialUserState,
    reducers: {

    }, 
    extraReducers: (builder) => {
        
    }
})

export default UserSlice.reducer;