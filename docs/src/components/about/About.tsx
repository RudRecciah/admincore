import React, { FC, ReactElement } from "react";
import { PageWrapper } from "../../utils/PageWrapper";
import { Col, Container, Row } from "reactstrap";
import css from "./About.module.css";

interface Props {

}

const About: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <PageWrapper>
        <Container className={css.container} fluid={true}>
          <div className={css.topSpacer}/>
          <div className={"spacer d-none d-md-block"}/>
          <h1 className={`text-center ${css.mainText}`}>About</h1>
          <p className={"text-center fs-3 w-lg-75 text-light"}>
            Admincore is a simple, powerful administration and moderation plugin for Spigot. With Admincore, you can moderate your server with ease and simplicity. Report, punish, spectate, appeal, and manage your playerbase in-game quickly and without hassle. Built by staff for staff, you can be sure Admincore contains every feature you need.
          </p>
          <div className={css.smSpacer}/>
          <Container className={"text-light fs-4 mt-5"}>
            <Row>
              <Col xs={12} lg={6}>
                <h2 className={`text-center text-primary ${css.mainTextSmall}`}>Features</h2>
                <p className={"text-center"}>
                  Admincore is built to be your only solution for everything moderation. It includes 50 unique components, each with their own complexities. Offering 150+ configuration options, Admincore can be customized to fit your moderation needs while also being a nearly drag-and-drop setup with the default values. Have a complex staff hierarchy? Admincore's got you covered, coming with 20 unique permissions to control power between staff members.
                </p>
              </Col>
              <Col xs={12} lg={6}>
                <h2 className={`text-center text-primary ${css.mainTextSmall}`}>Developers</h2>
                <p className={"text-center"}>
                  Admincore was made by staff, for staff. Admincore has mostly been developed by <a href={"https://rudrecciah.dev/"} className={"text-link text-decoration-none"}>RudRecciah</a>, a developer with over 8 years of experience in the Minecraft server industry, and experience in engineering custom staff products built to handle the worst and most damaging players. Admincore has even been checked over with hack client and anticheat developers, getting the best insight from multiple sides of the coin.
                </p>
              </Col>
            </Row>
            <Row>
              <Col>
                <h2 className={`text-center text-primary ${css.mainTextSmall} mt-5`}>Tech Specs</h2>
                <p className={"text-center w-75 mx-auto"}>
                  Admincore is open-source under the <a href={"https://opensource.org/licenses/MIT"} className={"text-link text-decoration-none"}>MIT</a> license, meaning anyone is free to modify and work on Admincore itself. It, like most plugins, is written in Java with Maven. The codebase is about 6000 LOC heavy. Admincore is built for Spigot versions 1.12.x-1.17.x, and should work on Spigot forks like Paper.
                </p>
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

export { About };