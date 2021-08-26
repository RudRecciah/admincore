import React, { FC, ReactElement } from "react";
import { Link } from "react-router-dom";
import PathController from "./PathController";
import { Container } from "reactstrap";

interface Props {

}

const NotFound: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <Container fluid={true} className={"overflow-hidden"}>
        <div className={"d-flex flex-column justify-content-center align-items-center min-vh-100 text-center"}>
          <div>
            <h1 className={"text-primary"}>404</h1>
            <p className={"fs-3 text-light"}>Seems like this page doesn't exist.</p>
            <Link to={PathController.getAbsolutePath("")} className={"btn btn-primary"}>Go Home</Link>
          </div>
        </div>
      </Container>
    </React.Fragment>
  );
};

export default NotFound;