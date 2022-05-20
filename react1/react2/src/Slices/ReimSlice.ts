import React from "react";
import axios from "axios";
import {createSlice, createAsyncThunk } from '@reduxjs/toolkit';

import { IReim } from "../Interface/IReim";

interface ReimSliceState{
    loading: boolean,
    error: boolean,
    reimburse?: IReim[]
}

const initialReimState: ReimSliceState = {
    loading: false,
    error: false
};

export const getReim = createAsyncThunk(
  "reimburse/get",
  async ( thunkAPI) => {
      try{
          const res = await axios.get(`http://localhost:8080/reimburse/get/`);

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
        toggleError : (state) => {
            state.error = !state.error;
        }
    }, 
    extraReducers: (builder) => {
        builder.addCase(getReim.pending, (state, action) => {
            state.loading = true;
        });
        builder.addCase(getReim.fulfilled, (state, action) => {
            state.reimburse = action.payload;
            state.error = false;
            state.loading = false;
                });

        builder.addCase(getReim.rejected, (state, action) => {
             state.error = true;
             state.loading = false;
                });
      
    }
})

   export const {toggleError} = ReimSlice.actions;

export default ReimSlice.reducer;