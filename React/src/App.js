import React from "react";
import {Routes, Route, BrowserRouter} from "react-router-dom";
import Home from './pages/Home';
import MyProfile from "./pages/MyProfile";
import CompanyRegister from './pages/CompanyRegister';
import Managers from './pages/Managers';
import Login from "./pages/Login";
import Register from "./pages/Register";
import ForgotPassword from "./pages/ForgotPassword";
import RegisterManager from "./pages/RegisterManager";
import ManagerHome from "./managerPanel/pages/ManagerHome";
import ManagerMyProfile from "./managerPanel/pages/ManagerMyProfile";
import EmployeeRegister from "./managerPanel/pages/EmployeeRegister";
// import EmployeeList from "./managerPanel/pages/EmployeeList";
import AdvanceList from "./managerPanel/pages/AdvanceList";
import ExpenseList from "./managerPanel/pages/ExpenseList";
import PermissionList from "./managerPanel/pages/PermissionList";
import ManagersEmployeePage from "./managerPanel/pages/ManagersEmployeePage";
import EmployeeHome from "./employeePanel/pages/EmployeeHome";
import EmployeeMyProfile from "./employeePanel/pages/EmployeeMyProfile";
import MyExpense from "./employeePanel/pages/MyExpense";
import MyAdvance from "./employeePanel/pages/MyAdvance";
import MyPermission from "./employeePanel/pages/MyPermission";

export default function App(){

  return(
    <>
    <BrowserRouter>
      <Routes>
        <Route path="/" exact element={<Login />} ></Route>
        <Route path="/home" exact element={<Home />} ></Route>
        <Route path="/myprofile" exact element={<MyProfile />} ></Route>
        <Route path="/companyregister" exact element={<CompanyRegister />} ></Route>
        <Route path="/managers" exact element={<Managers />} ></Route>
        <Route path="/login" exact element={<Login />}></Route>
        <Route path="/register" exact element={<Register />}></Route>
        <Route path="/registermanager" exact element={<RegisterManager />}></Route>
        <Route path="/reset-password" exact element={<ForgotPassword />}></Route>

        <Route path="/managerhome" exact element={<ManagerHome />} ></Route>
        <Route path="/managermyprofile" exact element={<ManagerMyProfile />} ></Route>
        <Route path="/employeeregister" exact element={<EmployeeRegister />} ></Route>
        {/* <Route path="/employeelist" exact element={<EmployeeList />} ></Route> */}
        <Route path="/advancelist" exact element={<AdvanceList />} ></Route>
        <Route path="/expenselist" exact element={<ExpenseList />} ></Route>
        <Route path="/permissionlist" exact element={<PermissionList />} ></Route>
        <Route path="/managersemployeepage" exact element={<ManagersEmployeePage />} ></Route>
        <Route path="/employeehome" exact element={<EmployeeHome />} ></Route>
        <Route path="/employeemyprofile" exact element={<EmployeeMyProfile />} ></Route>
        <Route path="/myexpense" exact element={<MyExpense />} ></Route>
        <Route path="/myadvance" exact element={<MyAdvance />} ></Route>
        <Route path="/mypermission" exact element={<MyPermission />} ></Route>
      </Routes>
    </BrowserRouter>
    </>
  )
}