import React, { FC, ReactElement } from "react";
import { PageWrapper } from "../../utils/PageWrapper";
import PathController from "../../utils/PathController";
import { AnimatePresence } from "framer-motion";
import { Route, Switch, Switch as BrowserSwitch, useLocation, useRouteMatch } from "react-router-dom";
import { Card, CardBody, CardTitle, Col, Container, Row } from "reactstrap";
import css from "./Docs.module.css";
import { DocsRoutes } from "./DocsRoutes";
import { DocsSidebar } from "./DocsSidebar";
import { Link } from "react-router-dom";

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
            <Col xs={12} className={"d-md-none text-center d-flex flex-row"}>
              <Link to={PathController.getDocsPath("installation")} className={"fs-5 btn btn-primary text-decoration-none footer-link flex-fill"}>
                <span className={"d-none d-sm-inline"}>Installation Guide</span>
                <span className={"d-sm-none"}>Installation</span>
              </Link>
              <span className={"px-1"}/>
              <Link to={PathController.getDocsPath("updating")} className={"fs-5 btn btn-primary text-decoration-none footer-link flex-fill"}>
                <span className={"d-none d-sm-inline"}>Updating Guide</span>
                <span className={"d-sm-none"}>Updating</span>
              </Link>
            </Col>
            <div className={"d-md-none py-1"}/>
            <Col xs={12} className={"d-md-none text-center d-flex flex-row"}>
              <Link to={PathController.getDocsPath("commands")} className={"fs-5 btn btn-primary text-decoration-none footer-link flex-fill"}>Commands</Link>
              <span className={"px-1"}/>
              <Link to={PathController.getDocsPath("config")} className={"fs-5 btn btn-primary text-decoration-none footer-link flex-fill"}>Config</Link>
              <span className={"px-1 d-none d-sm-flex"}/>
              <Link to={PathController.getDocsPath("permissions")} className={"d-none d-sm-flex fs-5 btn btn-primary text-decoration-none footer-link flex-fill flex-row justify-content-center"}>Perms</Link>
              <span className={"px-1 d-none d-sm-flex"}/>
              <Link to={PathController.getDocsPath("misc")} className={"d-none d-sm-flex fs-5 btn btn-primary text-decoration-none footer-link flex-fill flex-row justify-content-center"}>Other</Link>
            </Col>
            <div className={"d-sm-none py-1"}/>
            <Col xs={12} className={"d-sm-none d-flex flex-row"}>
              <Link to={PathController.getDocsPath("permissions")} className={"d-sm-none fs-5 btn btn-primary text-decoration-none footer-link flex-fill"}>Perms</Link>
              <span className={"px-1 d-sm-none"}/>
              <Link to={PathController.getDocsPath("misc")} className={"d-sm-none fs-5 btn btn-primary text-decoration-none footer-link flex-fill"}>Other</Link>
            </Col>
          </Row>
        </Container>
      </PageWrapper>
    </React.Fragment>
  );
};

export { DocsContainer };