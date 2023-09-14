import React from "react";
import Box from '@mui/material/Box';
import EmployeeSidenav from '../components/EmployeeSidenav';
import EmployeeNavbar from '../components/EmployeeNavbar';
import Expenselist from "../components/Expenselist";

export default function MyExpense() {
    return (
    <>
    <EmployeeNavbar />
    <Box height={70} />
        <Box sx={{ display: 'flex' }}>
            <EmployeeSidenav />
            <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
                <Expenselist />
            </Box>
        </Box>
    </>   
    )
    
}