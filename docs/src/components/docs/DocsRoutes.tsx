import React, { FC, ReactElement } from "react";
import PathController from "../../utils/PathController";
import { Route } from "react-router-dom";
import { Test } from "./Test";
import NotFound from "../../utils/NotFound";
import HomeComponent from "../home/Home";
import { DocsHome } from "./DocsHome";
import * as Path from "path";

interface Props {

}

const DocsRoutes: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <Route path={PathController.getDocsPath("test")} exact component={Test}/>
      <Route path={PathController.getAbsolutePath("docs")} exact component={DocsHome}/>
    </React.Fragment>
  );
};

export { DocsRoutes };