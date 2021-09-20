import React, { FC, ReactElement } from "react";
import css from "./Features.module.css";
import { PageWrapper } from "../../utils/PageWrapper";
import { Col, Container, Row } from "reactstrap";

interface Props {

}

const Features: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <PageWrapper>
        <Container fluid={true} className={css.container}>
          <div className={css.topSpacer}/>
          <div className={"spacer d-none d-md-block"}/>
          <h1 className={`text-center ${css.mainText} overflow-hidden`}>Features</h1>
          <p className={"text-center fs-3 w-lg-75 text-light"}>
            Admincore is a plugin crafted to serve all your moderation needs. Offering 50 unique components, 150+ configuration options, and 20 permissions, Admincore is expansive, customizable, and trustworthy. Being able to handle anything from small hobby servers to massive thousand-player servers, Admincore has it all.
          </p>
          <Container className={"text-light fs-4 mt-5"}>
            <h2 className={`text-center text-primary ${css.mainTextSmall}`}>Components</h2>
            <Row>
              <Col xs={1}/>
              <Col xs={2}>

              </Col>
              <Col xs={2}>

              </Col>
              <Col xs={2}>

              </Col>
              <Col xs={2}>

              </Col>
              <Col xs={2}>

              </Col>
              <Col xs={1}/>
            </Row>
          </Container>
        </Container>
      </PageWrapper>
    </React.Fragment>
  );
};

export { Features };