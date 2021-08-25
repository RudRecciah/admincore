import React from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "extrastrap";
import "./styles/theme.css";
import Router from "./Router";

function App() {
  return (
    <div className="App min-vh-100 bg-dark">
      <Router/>
    </div>
  );
}

export default App;
