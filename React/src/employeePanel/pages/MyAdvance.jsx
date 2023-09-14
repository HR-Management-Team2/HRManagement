import React from "react";
import Box from '@mui/material/Box';
import EmployeeSidenav from '../components/EmployeeSidenav';
import EmployeeNavbar from '../components/EmployeeNavbar';
import AdvanceList from "../components/Advancelist";

export default function MyAdvance() {
    return (
    <>
    <EmployeeNavbar />
    <Box height={70} />
        <Box sx={{ display: 'flex' }}>
            <EmployeeSidenav />
            <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
                <AdvanceList/>  
            </Box>
        </Box>
    </>   
    )
    
}