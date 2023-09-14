import React from "react";
import Box from '@mui/material/Box';
import ManagerSidenav from '../components/ManagerSidenav';
import ManagerNavbar from '../components/ManagerNavbar';
import Employeelist from "../components/Employeelist";

export default function EmployeeRegister() {
    return (
        <>
    <ManagerNavbar />
    <Box height={70} />
        <Box sx={{ display: 'flex' }}>
            <ManagerSidenav />
            <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
                <Employeelist />       
            </Box>
        </Box>
    </>   
    )
    
}