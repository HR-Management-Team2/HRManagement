import React, { useState, useEffect } from "react";
import Sidenav from "../components/Sidenav";
import Box from "@mui/material/Box";
import Navbar from "../components/Navbar";
import Avatar from "@mui/material/Avatar";
import TextField from "@mui/material/TextField";
import Paper from "@mui/material/Paper";
import { styled } from "@mui/material/styles";
import Button from "@mui/material/Button";
import CloudUploadIcon from "@mui/icons-material/CloudUpload";
import axios from "axios";
import jwt_decode from "jwt-decode";


export default function Myprofile() {
  const [isEditing, setIsEditing] = useState(false);
  const [admin, setAdmin] = useState([]);

  const token = localStorage.getItem("TOKEN");
  const decodedToken = jwt_decode(token);
  const authId = decodedToken.authId;



  const [userData, setUserData] = useState({
    firstName: "",
    lastName: "",
    email: "",
  });

  const handleEditClick = () => {
    setIsEditing(!isEditing);
  };

  const handleSaveClick = () => {
    setIsEditing(false);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUserData({
      ...userData,
      [name]: value,
    });
  };

  useEffect(() => {
    axios.get(`http://10.116.9.110:8090/api/v1/user/find-by-user-dto/${authId}`,token,{
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
        }
    })
    .then((response) => {
      const { firstName, lastName, email } = response.data;
      setUserData({ firstName, lastName, email });
    }).catch((error)=>{
      console.log(error);
    });
  }, [authId, token]);

  // useEffect(() => {
  //   axios
  //     .get(`http://localhost:8090/api/v1/user/find-by-user-dto${authId}`, {
  //       headers: {
  //         'Content-Type': 'application/json',
  //         Authorization: `Bearer ${token}`,
  //       },
  //     })
  //     .then((response) => {
  //       const { firstName, lastName, email } = response.data;
  //       setUserData({ firstName, lastName, email });
  //     })
  //     .catch((error) => {
  //       console.log(error);
  //     });
  // }, [authId, token]);


  const VisuallyHiddenInput = styled("input")`
    clip: rect(0 0 0 0);
    clip-path: inset(50%);
    height: 1px;
    overflow: hidden;
    position: absolute;
    bottom: 0;
    left: 0;
    white-space: nowrap;
    width: 1px;
  `;

  return (
    <>
      <Navbar />
      <Box height={10} />
      <Box sx={{ display: "flex" }}>
        <Sidenav />
        <Box component="main" sx={{ flexGrow: 1, p: 3, maxWidth: "600px" }}>
          <h1>My Profile</h1>
          {/* bunun yerine admin isim soyismi çekilse güzel olur */}
          <Avatar
            alt="Remy Sharp"
            src="\pages\bg\login.png"
            sx={{ width: 150, height: 150, marginBottom: "20px" }}
          />
          <Button
            component="label"
            variant="contained"
            startIcon={<CloudUploadIcon />}
            href="#file-upload"
          >
            Upload a file
            <VisuallyHiddenInput type="file" />
          </Button>
          {isEditing ? (
            <Box sx={{ marginTop: "50px" }}>
              <TextField
                name="firstName"
                label="First Name"
                value={userData.firstName}
                onChange={handleChange}
                fullWidth
                sx={{ mb: 2 }}
              />
              <TextField
                name="lastName"
                label="Last Name"
                value={userData.lastName}
                onChange={handleChange}
                fullWidth
                sx={{ mb: 2 }}
              />
              <TextField
                name="email"
                label="Email"
                type="email"
                value={userData.email}
                onChange={handleChange}
                fullWidth
                sx={{ mb: 2 }}
              />
            </Box>
          ) : (
            <Paper
              elevation={3}
              sx={{ padding: 2, mb: 2, maxWidth: "600px", marginTop: "30px" }}
            >
              <p>First Name: {userData.firstName}</p>
              <p>Last Name: {userData.lastName}</p>
              <p>Email: {userData.email}</p>
            </Paper>
          )}

          <Box sx={{ display: "flex", gap: 2 }}>
            <Button
              variant="contained"
              color="primary"
              onClick={handleEditClick}
            >
              {isEditing ? "Cancel" : "Edit"}
            </Button>
            {isEditing && (
              <Button
                variant="contained"
                color="primary"
                onClick={handleSaveClick}
              >
                Save
              </Button>
            )}
          </Box>
        </Box>
      </Box>
    </>
  );
}
