import React from "react";
import Box from '@mui/material/Box';
import EmployeeSidenav from '../components/EmployeeSidenav';
import EmployeeNavbar from '../components/EmployeeNavbar';
import Grid from '@mui/material/Grid';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Stack from '@mui/material/Stack';

import AccountBalanceWalletIcon from '@mui/icons-material/AccountBalanceWallet';
import BadgeIcon from '@mui/icons-material/Badge';
import CardGiftcardIcon from '@mui/icons-material/CardGiftcard';
import GiteIcon from '@mui/icons-material/Gite';
import BarChart from "../../charts/BarChart";
import CountUp from "react-countup";
import BasicDateCalendar from "../../calendar/Calendar"
import TodoList from "../../charts/TodoList";
import WebsiteVisits from "../../charts/WebsiteVisits";


export default function EmployeeHome() {
    return (
        <>
            <div className="bgcolor">
                <EmployeeNavbar />
                <Box height={70} />
                <Box sx={{ display: 'flex' }}>
                    <EmployeeSidenav />
                    <Box component="main" sx={{ flexGrow: 1, p: 3, width: '100%' }}>
                        <Grid container spacing={2}>
                            <Grid item xs={12}>
                                <Stack spacing={2} direction="row">
                                    <Card sx={{ maxWidth: '100%', width: '100%', height: 140, }} className="gradient">
                                        <CardContent>
                                            <Stack spacing={2}>
                                                <div className="iconStyle">
                                                    <AccountBalanceWalletIcon />
                                                </div>
                                                <div className="paddingall">
                                                    <span className="countOf">
                                                        <CountUp end={24} delay={0.5} duration={1} />
                                                    </span><br />
                                                    <span>TOTAL EXPENSE</span>
                                                </div>
                                            </Stack>
                                        </CardContent>
                                    </Card>
                                    <Card sx={{ maxWidth: '100%', width: '100%', height: 140 }} className="gradient">
                                        <CardContent>
                                            <Stack spacing={2}>
                                                <div className="iconStyle">
                                                    <GiteIcon />
                                                </div>
                                                <div className="paddingall">
                                                    <span className="countOf">
                                                        <CountUp end={3} delay={0.5} duration={1} />
                                                    </span><br />
                                                    <span>TOTAL PERMISSION</span>
                                                </div>
                                            </Stack>
                                        </CardContent>
                                    </Card>
                                    <Card sx={{ maxWidth: '100%', width: '100%', height: 140 }} className="gradient">
                                        <CardContent>
                                            <Stack spacing={2}>
                                                <div className="iconStyle">
                                                    <CardGiftcardIcon />
                                                </div>
                                                <div className="paddingall">
                                                    <span className="countOf">
                                                        <CountUp end={8} delay={0.5} duration={1} />
                                                    </span><br />
                                                    <span>TOTAL ADVANCE</span>
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
                                                        <CountUp end={5} delay={0.5} duration={1} />
                                                    </span><br />
                                                    <span>TOTAL PROJECT</span>
                                                </div>
                                            </Stack>
                                        </CardContent>
                                    </Card>
                                </Stack>
                            </Grid>
                        </Grid>
                        <Box height={20} />
                        <Grid container spacing={2}>
                            <Grid item xs={5}>
                                <div className="websitevisits">
                                    <WebsiteVisits />
                                </div>
                            </Grid>
                            <Grid item xs={4}>
                                <div className="gradient">
                                    <BasicDateCalendar />
                                </div>
                            </Grid>
                            <Grid item xs={3}>
                                <div className="todolist">
                                    <TodoList />
                                </div>
                            </Grid>
                        </Grid>
                    </Box>
                </Box>
            </div>
        </>
    )

}