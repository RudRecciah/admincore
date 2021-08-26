import React, { FC, ReactElement } from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import PathController from "./utils/PathController";
import Home from "./components/Home";
import NotFound from "./utils/NotFound";

interface Props {

}

const Router: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <BrowserRouter>
        <Switch>
          <Route path={PathController.getAbsoluteRootPath()} exact component={Home}/>
          <Route path={PathController.getAbsoluteNotFoundPath()} component={NotFound}/>
        </Switch>
      </BrowserRouter>
    </React.Fragment>
  );
};

export default Router;