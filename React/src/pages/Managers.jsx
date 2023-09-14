import React from "react";
import Sidenav from "../components/Sidenav";
import Box from '@mui/material/Box';
import Navbar from "../components/Navbar";
import Managerlist from "../components/Managerlist";


export default function ManagerRegister(){
    return(
    <>
    <Navbar />
    <Box height={90} />
        <Box sx={{ display: 'flex' }}>
            <Sidenav />
            <Box component="main" sx={{ flexGrow: 1, p: 3 }}>  
             <Managerlist />
            </Box>
        </Box>
    </>   
    );
}