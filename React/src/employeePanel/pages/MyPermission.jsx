import React from "react";
import Box from '@mui/material/Box';
import EmployeeSidenav from '../components/EmployeeSidenav';
import EmployeeNavbar from '../components/EmployeeNavbar';
import Permissionlist from "../components/Permissionlist";

export default function MyPermission() {
    return (
    <>
    <EmployeeNavbar />
    <Box height={70} />
        <Box sx={{ display: 'flex' }}>
            <EmployeeSidenav />
            <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
                <Permissionlist />  
            </Box>
        </Box>
    </>   
    )
    
}