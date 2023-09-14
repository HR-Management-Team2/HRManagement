import React from "react";
import ManagerSidenav from "../components/ManagerSidenav";
import ManagerNavbar from '../components/ManagerNavbar';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Stack from '@mui/material/Stack';

import SupervisorAccountIcon from '@mui/icons-material/SupervisorAccount';
import BadgeIcon from '@mui/icons-material/Badge';
import BusinessIcon from '@mui/icons-material/Business';
import PersonIcon from '@mui/icons-material/Person';
import BarChart from "../charts/BarChart";
import ScatterChart from "../charts/ScatterChart";
import CountUp from "react-countup";
import BasicDateCalendar from "../calendar/Calendar";




export default function ManagerHome(){
    return(
    <>
    <div className="bgcolor">
    <ManagerNavbar />
    <Box height={70} />
        <Box sx={{ display: 'flex' }}>
            <ManagerSidenav />
            <Box component="main" sx={{ flexGrow: 1, p: 3, width: '100%' }}>
            <Grid container spacing={2}>
                <Grid item xs={12}>
                <Stack spacing={2} direction="row">
                <Card sx={{ maxWidth: '100%', width: '100%', height: 140 }} className="gradient">
                    <CardContent>
                        <Stack spacing={2}>
                            <div className="iconStyle">
                                <SupervisorAccountIcon />
                            </div>
                           <div className="paddingall">
                                <span className="countOf">
                                <CountUp end={501} delay={0.5} duration={1} />  
                                </span><br/>
                                <span>TOTAL EMPLOYEE</span>
                            </div>
                        </Stack>
                    </CardContent>
                </Card>
                    <Card sx={{ maxWidth: '100%', width: '100%', height: 140 }} className="gradient">
                    <CardContent>
                        <Stack spacing={2}>
                            <div className="iconStyle">
                                <PersonIcon />
                            </div>
                            <div className="paddingall">
                                <span className="countOf">
                                <CountUp end={365} delay={0.5} duration={1} />  
                                </span><br/>
                                <span>ACTIVE EMPLOYEE</span>
                            </div>
                        </Stack>
                    </CardContent>
                    </Card>
                    <Card sx={{ maxWidth: '100%', width: '100%', height: 140 }} className="gradient">
                        <CardContent>
                        <Stack spacing={2}>
                            <div className="iconStyle">
                                <BusinessIcon />
                            </div>
                            <div className="paddingall">
                                <span className="countOf">
                                    <CountUp end={11} delay={0.5} duration={1} />   
                                </span><br/>
                                <span>RETIRED EMPLOYEE</span>
                            </div>
                        </Stack>
                        </CardContent>
                    </Card>
                    <Card sx={{ maxWidth: '100%', width: '100%', height: 140 }} className="gradient">
                        <CardContent>
                        <Stack spacing={2}>
                            <div className="iconStyle">
                                <BadgeIcon />
                            </div>
                            <div className="paddingall">
                                <span className="countOf">
                                <CountUp end={6} delay={0.5} duration={1} />  
                                </span><br/>
                                <span>LAIK EMPLOYEE</span>
                            </div>
                        </Stack>
                        </CardContent>
                    </Card>
                    </Stack>
                </Grid>
            </Grid> 
            <Box height={20} />
            <Grid container spacing={2}>
                <Grid item xs={8}>
                        <div className="barchart">
                        <BarChart />
                        </div>
                </Grid>
                <Grid item xs={4}>
                     <div className="barchart">
                        <ScatterChart />
                     </div>
                </Grid>
            </Grid>    
            </Box>
        </Box>
    </div>
    </>  
    );
}