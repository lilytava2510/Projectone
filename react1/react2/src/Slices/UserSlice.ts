import {createSlice, createAsyncThunk} from "@reduxjs/toolkit";
import {IUser} from "../Interface/IUser";
import axios from "axios";

interface UserSliceState {
    loading: boolean,
    error: boolean,
    user?: IUser
    currentProfile?: IUser
}


const initialUserState: UserSliceState = {
    loading: false,
    error: false
}
type Login = {
    email:string, 
    password:string
}

export const loginUser = createAsyncThunk(
    'user/login',
    async(credentials:Login, thunkAPI) => {
         try{
              const res = await axios.post('http://localhost:8080/users/login',credentials);
        
        return  {
                  userId: res.data.userId,
                  firstName: res.data.firstName,
                  lastName: res.data.lastName,
                  username: res.data.username,
                  email: res.data.email,
                  password: res.data.password,
                  privilege: res.data.privilege
        }

        } catch(e){
            return thunkAPI.rejectWithValue('Wrong login try again');
        }
    }
)


export const UserSlice = createSlice({
    name: "user", 
    initialState: initialUserState,
    reducers: {
        toggleError : (state) => {
            state.error = !state.error;
        }
    }, 
    extraReducers: (builder) => {
        builder.addCase(loginUser.pending, (state, action) => {
            state.loading = true;
        });
        builder.addCase(loginUser.fulfilled, (state, action) => {
            state.user = action.payload;
            state.error = false;
            state.loading = false;
                });

        builder.addCase(loginUser.rejected, (state, action) => {
             state.error = true;
             state.loading = false;
                });
      
    }
})

   export const {toggleError} = UserSlice.actions;

export default UserSlice.reducer;