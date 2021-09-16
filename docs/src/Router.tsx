import React, { FC, ReactElement } from "react";
import { BrowserRouter, Route, Switch, useLocation } from "react-router-dom";
import PathController from "./utils/PathController";
import HomeComponent from "./components/home/Home";
import NotFound from "./utils/NotFound";
import Navbar from "./utils/Navbar";
import Footer from "./utils/Footer";
import { AnimatePresence, motion } from "framer-motion";


interface Props {

}

const Router: FC<Props> = (): ReactElement => {
  const location = useLocation();
  return (
    <React.Fragment>
      <div className={"min-vh-100 d-flex flex-column overflow-hidden"}>
        <Navbar/>
        <AnimatePresence exitBeforeEnter>
          <Switch location={location} key={location.pathname}>
            <Route path={PathController.getAbsoluteRootPath()} exact component={HomeComponent}/>
            <Route path={PathController.getAbsoluteNotFoundPath()} component={NotFound}/>
          </Switch>
        </AnimatePresence>
      </div>
      <Footer/>
    </React.Fragment>
  );
};

export default Router;