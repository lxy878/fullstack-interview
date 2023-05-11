import { createSlice, PayloadAction } from "@reduxjs/toolkit"
import Person from "../models/Person";

export interface PageStatus {
    globalPerson: Person;
}
  
let initialState: PageStatus = {
    globalPerson:{

    }
}

export const pageStatusSlice = createSlice({
    name: 'pageStatus',
    initialState,
    reducers: {
        updateFirstName: (state, action: PayloadAction<string>) => {
            state.globalPerson.firstName = action.payload;
        }
    }
});

export const { 
    actions: pageStatusActions,
    reducer: pageStatusReducer,
    name: pageStatusSliceKey,
} = pageStatusSlice;

export default pageStatusSlice.reducer