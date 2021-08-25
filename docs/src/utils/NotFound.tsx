import React, { Component } from "react";
import { Col, Container, Row } from "reactstrap";
import { Link } from "react-router-dom";
import PathController from "./PathController";

interface Props {

}

interface State {

}

export default class NotFound extends Component<Props, State> {
  constructor(props: Readonly<Props>) {
    super(props);
    this.state = {};
  }

  render(): JSX.Element {
    return (
      <React.Fragment>
        <Container fluid={true}>
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
  }
}