import React, { FC, ReactElement } from "react";
import { Col, Container, Row } from "reactstrap";
import Icon from "./Icon";
import { Link } from "react-router-dom";
import PathController from "./PathController";
import logo from "../static/logo.png";

interface Props {

}

const Footer: FC<Props> = (): ReactElement => {

  const copyright = parseInt(Date().split(" ")[3]) === 2021 ? "2021" : `2021-${(parseInt(Date().split(" ")[3])).toString()}`;

  return (
    <React.Fragment>
      <Container fluid={true} className={"p-0 bg-darker"}>
        <div className={"footer-width mb-3 mx-auto"}>
          <div className={"footer-spacer"}/>
          <Row>
            <Col xs={12} md={6} lg={4} className={"d-flex flex-column"}>
              <h2 className={"text-center text-lg-start"}>Navigation</h2>
              <Link to={PathController.getAbsolutePath("about")} className={"text-decoration-none pb-2 text-center text-lg-start"}>
                <span className={"ps-lg-2 text-center text-lg-start fs-4 text-decoration-none footer-link"}>About</span>
              </Link>
              <Link to={PathController.getAbsolutePath("features")} className={"text-decoration-none pb-2 text-center text-lg-start"}>
                <span className={"ps-lg-2 text-center text-lg-start fs-4 text-decoration-none footer-link"}>Features</span>
              </Link>
              <Link to={PathController.getAbsolutePath("docs")} className={"text-decoration-none pb-2 text-center text-lg-start"}>
                <span className={"ps-lg-2 text-center text-lg-start fs-4 text-decoration-none footer-link"}>Docs</span>
              </Link>
              <Link to={PathController.getAbsolutePath("bugs")} className={"text-decoration-none pb-5 pb-md-2 text-center text-lg-start"}>
                <span className={"ps-lg-2 text-center text-lg-start fs-4 text-decoration-none footer-link"}>Bug Reports</span>
              </Link>
            </Col>
            <Col xs={12} md={6} lg={4} className={"d-flex flex-column"}>
              <h2 className={"text-center text-lg-start"}>Resources</h2>
              <a href={PathController.getAbsolutePath("download")} className={"ps-lg-2 text-center text-lg-start fs-4 text-decoration-none footer-link pb-2"}>Download</a>
              <a href={"https://rudrecciah.dev"} className={"ps-lg-2 text-center text-lg-start fs-4 text-decoration-none footer-link pb-2"}>RudRecciah</a>
              <a href={"https://github.com/rudrecciah/admincore"} className={"ps-lg-2 text-center text-lg-start fs-4 text-decoration-none footer-link pb-2"}>Source Code</a>
            </Col>
            <Col xs={4} className={"d-none d-lg-flex flex-row"}>
              <div className={"flex-fill"}/>
              <img src={logo} alt={"Admincore"} className={"img-fluid rounded-2 shadow logo"}/>
            </Col>
          </Row>
          <div className={"h-3rem"}/>
          <hr className={"text-primary"}/>
          <div className={"mt-4"}/>
          <div className={"text-center text-light mt-3 mb-3-5"}>
            <p>Copyright <Icon font={"Material"} icon={"copyright"} className={"fs-6 footer-copyright"}/> {copyright}, RudRecciah</p>
          </div>
        </div>
      </Container>
    </React.Fragment>
  );
};

export default Footer;