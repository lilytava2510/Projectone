import {createSlice, createAsyncThunk} from "@reduxjs/toolkit";
import {IUser} from "../Interface/IUser";
import axios from "axios";
import { userInfo } from "os";

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

type Paper = {
    userId: number,
    firstName: string,
    lastName: string,
    username: string,
    email: string,
    password: string,
   // privilege: boolean
}

export const loginUser = createAsyncThunk(
    'user/login',
    async(credentials:Login, thunkAPI) => {
         try{
              const res = await axios.post('http://localhost:8080/users/login',credentials);
              console.log(res.data);
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

export const getUser = createAsyncThunk(
    "user/info",
    async (userId:number, thunkAPI) => {
        try{
              axios.defaults.withCredentials = true;
            const res = await axios.get(`http://localhost:8080/users/info${userId}`);
  
            return res.data;
        } catch (e){
            console.log(e);
        }
    }  
  );

  export const updateUser = createAsyncThunk(
    "user/update",
    async (credentials:Paper, thunkAPI) => {
        try{
              axios.defaults.withCredentials = true;
            const res = await axios.put(`http://localhost:8080/users/update`,credentials);
  
            return res.data;
        } catch (e){
            console.log(e);
        }
    }  
  );

export const UserSlice = createSlice({
    name: "user", 
    initialState: initialUserState,
    reducers: {
        toggleError : (state) => {
            state.error = !state.error;
        },
        clearUser: (state) => {
            state.user = undefined
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
            console.log(state.user.privilege);
                });

        builder.addCase(loginUser.rejected, (state, action) => {
             state.error = true;
             state.loading = false;
                });
        builder.addCase(updateUser.pending, (state, action) => {
            state.loading = true;
        });
        builder.addCase(updateUser.fulfilled, (state, action) => {
            state.user = action.payload;
            state.error = false;
            state.loading = false;
                });

        builder.addCase(updateUser.rejected, (state, action) => {
                state.error = true;
                state.loading = false;
                });
    }
})

   export const {toggleError, clearUser} = UserSlice.actions;

export default UserSlice.reducer;