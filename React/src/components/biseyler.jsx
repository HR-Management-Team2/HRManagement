import React, { useState } from "react";
import Sidenav from "../components/Sidenav";
import Box from "@mui/material/Box";
import Navbar from "../components/Navbar";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Paper from "@mui/material/Paper";

export default function MyProfile() {
  const [isEditing, setIsEditing] = useState(false);
  const [userData, setUserData] = useState({
    firstName: "John",
    lastName: "Doe",
    email: "johndoe@example.com",
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

  return (
    <>
      <Navbar />
      <Box height={30} />
      <Box sx={{ display: "flex" }}>
        <Sidenav />
        <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
          <h1>My Profile</h1>

          {isEditing ? (
            <>
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
            </>
          ) : (
            <Paper elevation={3} sx={{ padding: 2, mb: 2 }}>
              <p>First Name: {userData.firstName}</p>
              <p>Last Name: {userData.lastName}</p>
              <p>Email: {userData.email}</p>
            </Paper>
          )}
          <Button variant="contained" color="primary" onClick={handleEditClick}>
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
    </>
  );
}

<Box sx={{ display: "flex", gap: 2 }}>
  <Button variant="contained" color="primary" onClick={handleEditClick}>
    {isEditing ? "Cancel" : "Edit"}
  </Button>
  {isEditing && (
    <Button variant="contained" color="primary" onClick={handleSaveClick}>
      Save
    </Button>
  )}
</Box>;
