import React from "react";
import axios from "axios";
import {createSlice, createAsyncThunk} from "@reduxjs/toolkit";
import {IReim} from "../Interface/IReim";

interface ReimSliceState{
    loading: boolean,
    error: boolean,
    reimburse?: IReim[]
}

const initialReimState: ReimSliceState = {
    loading: false,
    error: false
};

type Tick = {
    amount: number,
    description: string,
    author: number,
    type: number
}

type Tack = {
    id: number,
    author: number,
    status: number
}

export const writeReim = createAsyncThunk(
    'reimburse/create',
    async(ticket:Tick, thunkAPI) => {
         try{
              const res = await axios.post('http://localhost:8080/reimburse/create',ticket);
        
        return  {
                
        }

        } catch(e){
            return thunkAPI.rejectWithValue('Wrong login try again');
        }
    }
)

export const editReim = createAsyncThunk(
    'reimburse/edit',
    async(ticket:Tack, thunkAPI) => {
         try{
              const res = await axios.put('http://localhost:8080/reimburse/edit',ticket);
        
        return  {
                
        }

        } catch(e){
            return thunkAPI.rejectWithValue('Wrong login try again');
        }
    }
)

export const allApproveReim = createAsyncThunk(
    "reimburse/getAllApprove",
    async ( thunkAPI) => {
        try{
              axios.defaults.withCredentials = true;
            const res = await axios.get(`http://localhost:8080/reimburse/approve`);
  
            return res.data;
        } catch (e){
            console.log(e);
        }
    }  
  );

export const allPendReim = createAsyncThunk(
    "reimburse/getAllPend",
    async ( thunkAPI) => {
        try{
              axios.defaults.withCredentials = true;
            const res = await axios.get(`http://localhost:8080/reimburse/pend`);
  
            return res.data;
        } catch (e){
            console.log(e);
        }
    }  
  );

export const getApproveReim = createAsyncThunk(
    "reimburse/getOwnApprove",
    async (userId:number, thunkAPI) => {
        try{
              axios.defaults.withCredentials = true;
            const res = await axios.get(`http://localhost:8080/reimburse/own_approve${userId}`);
  
            return res.data;
        } catch (e){
            console.log(e);
        }
    }  
  );

export const getPendReim = createAsyncThunk(
  "reimburse/getOwnPend",
  async (userId:number, thunkAPI) => {
      try{
            axios.defaults.withCredentials = true;
          const res = await axios.get(`http://localhost:8080/reimburse/own_pend${userId}`);

          return res.data;
      } catch (e){
          console.log(e);
      }
  }  
);

export const getReim = createAsyncThunk(
    "reimburse/get",
    async (userId:number, thunkAPI) => {
        try{
              axios.defaults.withCredentials = true;
            const res = await axios.get(`http://localhost:8080/reimburse/get${userId}`);
  
            return res.data;
        } catch (e){
            console.log(e);
        }
    }  
  );

export const ReimSlice = createSlice({
    name: "reim", 
    initialState: initialReimState,
    reducers: {
        clearReim: (state) => {
            state.reimburse = undefined
        }
    }, 
    extraReducers: (builder) => {
        builder.addCase(getReim.pending, (state, action) => {
            state.loading = true;
        });
        builder.addCase(getReim.fulfilled, (state, action) => {
            state.reimburse = undefined;
            state.reimburse = action.payload;
            state.error = false;
            state.loading = false;
                });

        builder.addCase(getReim.rejected, (state, action) => {
            state.error = true;
            state.loading = false;
            });
        builder.addCase(getPendReim.pending, (state, action) => {
                state.loading = true;
            });
        builder.addCase(getPendReim.fulfilled, (state, action) => {
            state.reimburse = undefined;
            state.reimburse = action.payload;
            state.error = false;
            state.loading = false;
                });
        
        builder.addCase(getPendReim.rejected, (state, action) => {
            state.error = true;
            state.loading = false;
            });
         builder.addCase(getApproveReim.pending, (state, action) => {
                state.loading = true;
            });
         builder.addCase(getApproveReim.fulfilled, (state, action) => {
            state.reimburse = undefined;
            state.reimburse = action.payload;
            state.error = false;
            state.loading = false;
                });
                
        builder.addCase(getApproveReim.rejected, (state, action) => {
            state.error = true;
            state.loading = false;
            });
        builder.addCase(allApproveReim.pending, (state, action) => {
                state.loading = true;
            });
        builder.addCase(allApproveReim.fulfilled, (state, action) => {
            state.reimburse = undefined;
            state.reimburse = action.payload;
            state.error = false;
            state.loading = false;
                });
                        
        builder.addCase(allApproveReim.rejected, (state, action) => {
            state.error = true;
            state.loading = false;
            });
        builder.addCase(allPendReim.pending, (state, action) => {
            state.loading = true;
        });
        builder.addCase(allPendReim.fulfilled, (state, action) => {
            state.reimburse = undefined;
            state.reimburse = action.payload;
            state.error = false;
            state.loading = false;
                });

        builder.addCase(allPendReim.rejected, (state, action) => {
                state.error = true;
                state.loading = false;
                });
        builder.addCase(writeReim.pending, (state, action) => {
            state.loading = true;
        });
        builder.addCase(writeReim.fulfilled, (state, action) => {
            state.reimburse = undefined;
            state.error = false;
            state.loading = false;
                });

        builder.addCase(writeReim.rejected, (state, action) => {
                state.error = true;
                state.loading = false;
                });
        builder.addCase(editReim.pending, (state, action) => {
            state.loading = true;
        });
        builder.addCase(editReim.fulfilled, (state, action) => {
            state.reimburse = undefined;
            state.error = false;
            state.loading = false;
                });

        builder.addCase(editReim.rejected, (state, action) => {
                state.error = true;
                state.loading = false;
                });
    }
})

export const {clearReim} = ReimSlice.actions;

export default ReimSlice.reducer;