import React, { FC, ReactElement, useState } from "react";
import css from "./Features.module.css";
import { PageWrapper } from "../../utils/PageWrapper";
import { Card, CardBody, CardTitle, Col, Container, Row } from "reactstrap";
import FeatureList from "./FeatureList.json";
import { Component } from "./Component";
import { Config } from "./Config";
import { Permission } from "./Permission";

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
            Admincore is a plugin crafted to serve all your moderation needs. Offering 20 unique components, 150+ configuration options, and 20 permissions, Admincore is expansive, customizable, and trustworthy. Being able to handle anything from small hobby servers to massive thousand-player servers, Admincore has it all.
          </p>
          <Container className={"text-light fs-4 mt-5"}>
            <h2 className={`text-center text-primary ${css.mainTextSmall} mb-4`}>Components</h2>
            <Row>
              {FeatureList.components.map((component, index, array) => <Component title={component[0]} body={component[1]} last={index === array.length - 1}/>)}
            </Row>
            <h2 className={`text-center text-primary ${css.mainTextSmall} mt-5 mb-4`}>Configuration</h2>
            <Row>
              <Config/>
            </Row>
            <h2 className={`text-center text-primary ${css.mainTextSmall} mt-5 mb-4`}>Permissions</h2>
            <Row>
              {FeatureList.permissions.map((permission, index, array) => <Permission title={permission[0]} body={permission[1]} permissionChildren={permission[2]} last={index === array.length - 1}/>)}
            </Row>
          </Container>
          <div className={"spacer"}/>
          <div className={"spacer"}/>
        </Container>
      </PageWrapper>
    </React.Fragment>
  );
};

export { Features };