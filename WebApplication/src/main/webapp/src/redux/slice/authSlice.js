import {createSlice} from "@reduxjs/toolkit";
import {toast} from "react-toastify";
import {useNavigate} from "react-router-dom";

const initialState = {
    token: null,
    isLoggedIn: false,
    role: null,
};

const authSlice = createSlice({
    name: "auth",
    initialState,
    reducers: {
        loginSuccess: (state, action) => {
            const { email, role,token} = action.payload;
            state.email = email;
            state.role = role;
            state.isLoggedIn = true;
            state.token = token;
            localStorage.setItem("isLoggedIn", "true");
        },
        logoutSuccess: (state) => {
            state.token = null;
            state.email = null;
            state.role = null;
            state.isLoggedIn = false;
            localStorage.removeItem("isLoggedIn");
            localStorage.removeItem("token");
        }
    },
});

export const {loginSuccess, logoutSuccess} = authSlice.actions;
export const  selectIsLoggedIn = (state) => state.auth.isLoggedIn;
export default authSlice.reducer;
