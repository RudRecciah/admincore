import React, { FC, ReactElement } from "react";
import { PageWrapper } from "../../utils/PageWrapper";
import { Card, CardBody, CardTitle, Col, Container, Row } from "reactstrap";
import css from "./Bugs.module.css";

interface Props {

}

const Bugs: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <PageWrapper>
        <Container className={css.container}>
          <div className={css.topSpacer}/>
          <div className={"spacer d-none d-md-block"}/>
          <h1 className={`text-center ${css.mainText}`}>Bugs</h1>
          <p className={"text-center fs-3 w-lg-75 text-light"}>
            Admincore, like nearly every other piece of software, has errors. Especially with the current WIP state of the plugin, there will be bugs people come across along the way. You can report any bugs you find here.
          </p>
          <div className={css.smSpacer}/>
          <Container className={"text-light fs-4 mt-5"}>
            <Row>
              <Col xs={12} lg={6}>
                <h2 className={`text-center text-primary ${css.mainTextSmall}`}>GitHub Issues</h2>
                <p className={"text-center"}>
                  Admincore's primary way of reporting bugs is through GitHub's issue system. If you have a GitHub account, you can create an issue in Admincore's repository.
                </p>
                <div className={"text-center mb-5"}>
                  <a href={"https://github.com/rudrecciah/admincore/issues"} className={"btn btn-primary text-center"}>Create an Issue</a>
                </div>
              </Col>
              <Col xs={12} lg={6}>
                <h2 className={`text-center text-primary ${css.mainTextSmall}`}>Bug Page</h2>
                <p className={"text-center"}>
                  If you can't create an issue on GitHub, using this page is the next best way to report a bug. You can simply and anonomously write about any errors you find.
                </p>
                <div className={"text-center"}>
                  <a href={"../../bugs"} className={"btn btn-primary text-center"}>Report a Bug</a>
                </div>
              </Col>
            </Row>
            <div className={"spacer"}/>
            <div className={"spacer"}/>
          </Container>
        </Container>
      </PageWrapper>
    </React.Fragment>
  );
};

export { Bugs };