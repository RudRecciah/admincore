import React, { FC, ReactElement } from "react";
import { PageWrapper } from "../../utils/PageWrapper";
import PathController from "../../utils/PathController";
import { AnimatePresence } from "framer-motion";
import { Route, Switch, Switch as BrowserSwitch, useLocation, useRouteMatch } from "react-router-dom";
import { Card, CardBody, CardTitle, Col, Container, Row } from "reactstrap";
import css from "./Docs.module.css";
import { DocsRoutes } from "./DocsRoutes";

interface Props {

}

const DocsContainer: FC<Props> = (): ReactElement => {
  const location = useLocation();
  return (
    <React.Fragment>
      <PageWrapper key={"docs"}>
        <Container className={css.container} fluid={true}>
          <div className={css.topSpacer}/>
          <div className={"spacer d-none d-md-block"}/>
          <h1 className={`text-center ${css.mainText} overflow-hidden`}>Documentation</h1>
          <p className={"text-center fs-3 w-lg-75 text-light"}>
            Admincore Documentation
          </p>
          <Row className={"text-light fs-4 mt-5"}>
            <Col xs={3} className={"mb-4"}>
              <Card className={"shadow bg-dark h-100 p-3"}>
                <CardTitle className={"text-poppins text-primary"}>
                  Sections
                </CardTitle>
                <CardBody className={"text-urbanist"}>

                </CardBody>
              </Card>
            </Col>
            <Col xs={9} className={"mb-4"}>
              <Card className={"shadow bg-dark h-100 p-3"}>
                <AnimatePresence exitBeforeEnter>
                  <Switch location={location} key={location.pathname}>
                    <DocsRoutes/>
                  </Switch>
                </AnimatePresence>
              </Card>
            </Col>
          </Row>
        </Container>
      </PageWrapper>
    </React.Fragment>
  );
};

export { DocsContainer };