import React from "react";
import Box from '@mui/material/Box';
import ManagerSidenav from '../components/ManagerSidenav';
import ManagerNavbar from '../components/ManagerNavbar';
import Myprofilemanager from '../components/Myprofilemanager';

export default function ManagerMyProfile(){
    return (
        <>
        <ManagerNavbar />
        <Box height={30} />
            <Box sx={{ display: 'flex' }}>
                <ManagerSidenav />
                <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
                    <Myprofilemanager />      
                </Box>
            </Box>
        </>   
    )
}