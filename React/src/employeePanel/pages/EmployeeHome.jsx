import React from "react";
import Box from '@mui/material/Box';
import EmployeeSidenav from '../components/EmployeeSidenav';
import EmployeeNavbar from '../components/EmployeeNavbar';

export default function EmployeeHome() {
    return (
    <>
    <EmployeeNavbar />
    <Box height={30} />
        <Box sx={{ display: 'flex' }}>
            <EmployeeSidenav />
            <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
                <h1>EmployeeHome</h1>  
            </Box>
        </Box>
    </>   
    )
    
}