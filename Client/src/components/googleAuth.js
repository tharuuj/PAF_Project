
import React from "react";
import { auth, provider } from "./auth/firebaseConfig";
import { signInWithPopup } from "firebase/auth";
import google from "./assets/images/google.png";

function GoogleAuth({ handleAuth }) {
  const handleClick = () => {
    console.log("Google auth button clicked");
    
    signInWithPopup(auth, provider)
      .then((data) => {
        console.log("Google Auth Successful:", { 
          email: data.user?.email,
          displayName: data.user?.displayName
        });
        
        if (data && handleAuth) {
          handleAuth(data);
        } else {
          console.error("Google Auth: Missing data or handleAuth function");
        }
      })
      .catch((error) => {
        console.error("Google Auth Error:", error);
        // Display more user-friendly error
        alert("Google sign-in failed. Please try again or use email login.");
      });
  };

  return (
    <button
      onClick={handleClick}
      style={{
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        width: "100%",
        padding: "0.75rem",
        backgroundColor: "#ff0000",
        border: "1px solid #cc0000",
        borderRadius: "0.5rem",
        fontSize: "0.9rem",
        fontWeight: 600,
        color: "#ffffff",
        cursor: "pointer",
        transition: "all 0.2s",
        boxShadow: "0 1px 2px rgba(0,0,0,0.05)",
      }}
      onMouseOver={(e) => {
        e.currentTarget.style.backgroundColor = "#cc0000";
      }}
      onMouseOut={(e) => {
        e.currentTarget.style.backgroundColor = "#ff0000";
      }}
    >
      <img
        src={google}
        alt="Google"
        style={{
          width: "1.25rem",
          height: "1.25rem",
          marginRight: "0.75rem",
        }}
      />
      Continue with Google
    </button>
  );
}

export default GoogleAuth;