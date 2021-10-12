import React, { FC, ReactElement } from "react";
import PathController from "../../utils/PathController";
import { Route } from "react-router-dom";
import { DocsHome } from "./DocsHome";
import { Installation } from "./sections/guides/Installation";
import { Updating } from "./sections/guides/Updating";
import { Commands } from "./sections/reference/Commands";
import { Configuration } from "./sections/reference/Configuration";
import { Misc } from "./sections/reference/Misc";
import { Permissions } from "./sections/reference/Permissions";

interface Props {

}

const DocsRoutes: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <Route path={PathController.getDocsPath("installation")} exact component={Installation}/>
      <Route path={PathController.getDocsPath("updating")} exact component={Updating}/>
      <Route path={PathController.getDocsPath("commands")} exact component={Commands}/>
      <Route path={PathController.getDocsPath("config")} exact component={Configuration}/>
      <Route path={PathController.getDocsPath("permissions")} exact component={Permissions}/>
      <Route path={PathController.getDocsPath("misc")} exact component={Misc}/>
      <Route path={PathController.getAbsolutePath("docs")} exact component={DocsHome}/>
    </React.Fragment>
  );
};

export { DocsRoutes };