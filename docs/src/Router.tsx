import React, { Component, useContext } from "react";
import { BrowserRouter, Switch, Route, Link } from "react-router-dom";
import PathController from "./utils/PathController";
import NotFound from "./utils/NotFound";
import Home from "./components/Home";

interface Props {

}

interface State {

}

export default class Router extends Component<Props, State> {
  constructor(props: Readonly<Props>) {
    super(props);
    this.state = {};
  }

  render(): JSX.Element {
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
  }
}