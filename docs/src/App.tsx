import React from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "extrastrap";
import "./styles/theme.css";
import "./styles/main.css";
import Router from "./Router";
import { BrowserRouter } from "react-router-dom";

function App() {
  return (
    <div className="App min-vh-100 bg-dark d-flex flex-column overflow-x-hidden">
      <BrowserRouter>
        <Router/>
      </BrowserRouter>
    </div>
  );
}

export default App;
