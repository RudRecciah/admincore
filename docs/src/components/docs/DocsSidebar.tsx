import React, { FC, ReactElement } from "react";
import { Link } from "react-router-dom";
import PathController from "../../utils/PathController";

interface Props {

}

const DocsSidebar: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <Link to={PathController.getDocsPath("installation")} className={"fs-5 text-decoration-none footer-link"}>Installation Guide</Link>
      <Link to={PathController.getDocsPath("updating")} className={"fs-5 text-decoration-none footer-link"}>Updating Guide</Link>
      <Link to={PathController.getDocsPath("commands")} className={"fs-5 text-decoration-none footer-link"}>Commands</Link>
      <Link to={PathController.getDocsPath("config")} className={"fs-5 text-decoration-none footer-link"}>Configuration</Link>
      <Link to={PathController.getDocsPath("permissions")} className={"fs-5 text-decoration-none footer-link"}>Permissions</Link>
      <Link to={PathController.getDocsPath("misc")} className={"fs-5 text-decoration-none footer-link"}>Other Information</Link>
    </React.Fragment>
  );
};

export { DocsSidebar };