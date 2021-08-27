import React, { FC, ReactElement } from "react";
import { BrowserRouter, Route, Switch, useLocation } from "react-router-dom";
import PathController from "./utils/PathController";
import Home from "./components/Home";
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
      <Navbar/>
      <AnimatePresence exitBeforeEnter>
        <Switch location={location} key={location.pathname}>
          <Route path={PathController.getAbsoluteRootPath()} exact component={Home}/>
          <Route path={PathController.getAbsoluteNotFoundPath()} component={NotFound}/>
        </Switch>
      </AnimatePresence>
      <Footer/>
    </React.Fragment>
  );
};

export default Router;