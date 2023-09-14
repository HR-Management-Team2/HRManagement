import React from "react";
import Sidenav from "../components/Sidenav";
import Navbar from '../components/Navbar';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Stack from '@mui/material/Stack';
import "../Dash.css";
import SupervisorAccountIcon from '@mui/icons-material/SupervisorAccount';
import BadgeIcon from '@mui/icons-material/Badge';
import BusinessIcon from '@mui/icons-material/Business';
import PersonIcon from '@mui/icons-material/Person';
import BarChart from "../charts/BarChart";
import ScatterChart from "../charts/ScatterChart";
import CountUp from "react-countup";
import BasicDateCalendar from "../calendar/Calendar";
import Maps from './Map';




export default function Home(){
    return(
    <>
    <div className="bgcolor">
    <Navbar />
    <Box height={70} />
        <Box sx={{ display: 'flex' }}>
            <Sidenav />
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
                                <CountUp end={4} delay={0.5} duration={1} />  
                                </span><br/>
                                <span>Total Admin</span>
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
                                <CountUp end={36} delay={0.5} duration={1} />  
                                </span><br/>
                                <span>Total Manager</span>
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
                                    <CountUp end={41} delay={0.5} duration={1} />   
                                </span><br/>
                                <span>Total Company</span>
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
                                <CountUp end={647} delay={0.5} duration={1} />  
                                </span><br/>
                                <span>Total Employee</span>
                            </div>
                        </Stack>
                        </CardContent>
                    </Card>
                    </Stack>
                </Grid>
            </Grid> 
            <Box height={20} />
            <Grid container spacing={2}>
                <Grid item xs={6}>
                        <div className="paddingallchart">
                        <Maps />
                        </div>  
                </Grid>
                <Grid item xs={6}>
                     <div className="maps">
                        <BasicDateCalendar />
                     </div>
                </Grid>
            </Grid>    
            </Box>
        </Box>
    </div>
    </>  
    );
}