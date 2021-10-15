import React, { FC, ReactElement, useState } from "react";
import { PageWrapper } from "../../utils/PageWrapper";
import PathController from "../../utils/PathController";
import { AnimatePresence } from "framer-motion";
import { Route, Switch, Switch as BrowserSwitch, useLocation, useRouteMatch } from "react-router-dom";
import { Card, CardBody, CardTitle, Col, Container, Row } from "reactstrap";
import css from "./Docs.module.css";
import { DocsRoutes } from "./DocsRoutes";
import { DocsSidebar } from "./DocsSidebar";
import { Link } from "react-router-dom";
import { DocsMobileSidebar } from "./DocsMobileSidebar";

interface Props {

}

const DocsContainer: FC<Props> = (): ReactElement => {

  const location = useLocation();

  const [sidebar, setSidebar] = useState(true);

  const toggleSidebar = (): void => {
    setSidebar(!sidebar);
  };

  return (
    <React.Fragment>
      <PageWrapper key={"docs"}>
        <React.Fragment>
          {sidebar && <div>
            <nav className={"d-md-none"}>
              <div className={"position-absolute top-0 left-0 w-100 min-vh-100"} onClick={toggleSidebar}/>
            </nav>
          </div>}
          <DocsMobileSidebar sidebar={sidebar} toggleSidebar={toggleSidebar}/>
          <Container className={css.container} fluid={true}>
            <div className={css.topSpacer}/>
            <div className={"spacer d-none d-md-block"}/>
            <h1 className={`text-center ${css.mainText} overflow-hidden`}>Documentation</h1>
            <p className={"text-center fs-3 w-lg-75 text-light"}>
              Admincore Documentation
            </p>
            <Row className={"text-light fs-4 mt-5"}>
              <Col xs={12} md={5} lg={4} xl={3} className={"d-none d-md-block mb-4"}>
                <Card className={"shadow bg-dark h-100 p-3"}>
                  <CardTitle className={"text-poppins text-primary fs-3"}>
                    Sections
                  </CardTitle>
                  <CardBody className={"text-urbanist fs-4 py-0 d-flex flex-column"}>
                    <DocsSidebar/>
                  </CardBody>
                </Card>
              </Col>
              <Col xs={12} md={7} lg={8} xl={9} className={"mb-4"}>
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
        </React.Fragment>
      </PageWrapper>
    </React.Fragment>
  );
};

export { DocsContainer };