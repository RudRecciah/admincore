import React, { EffectCallback, FC, ReactElement, useEffect, useRef, useState } from "react";
import { Card, CardBody, CardLink, CardText, CardTitle, Col, Container, Row } from "reactstrap";
import { PageWrapper } from "../../utils/PageWrapper";
import css from "./Home.module.css";
import splash from "../../static/splash.png";
import PathController from "../../utils/PathController";
import { Link } from "react-router-dom";
import inspect from "../../static/inspect.png";
import punish from "../../static/punish.png";
import Icon from "../../utils/Icon";

interface Props {

}

const Home: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <PageWrapper>
        <Container fluid={true} className={`text-light ${css.container}`}>
          <Row className={"flex-column-reverse flex-xl-row"}>
            <Col>
              <div className={`text-center text-xl-start ${css.title}`}>
                <h1 className={css.bigText}>
                  Moderate
                </h1>
                <h1 className={css.titleSecondLine}>with ease.</h1>
                <a href={PathController.getAbsolutePath("download")} className={`btn btn-primary text-urbanist me-sm--3 shadow ${css.titleDownloadBtn}`}>Download</a>
                <Link to={PathController.getAbsolutePath("docs")} className={`btn btn-link text-primary text-link text-urbanist ms-sm-3 ${css.titleDownloadBtn} ${css.titleGetStartedBtn}`} id={"homepage-btn-get-started"}>
                  Get Started
                </Link>
              </div>
            </Col>
            <Col>
              <img src={splash} alt={"Some of Admincore's interfaces."} className={`img-fluid ${css.splash}`}/>
            </Col>
          </Row>
          <div className={css.spacer}/>
          <div className={css.spacer}/>
          <div className={css.spacer}/>
          <h1 className={css.mainText}>Keep your playerbase safe</h1>
          <div className={css.spacer}/>
          <Row>
            <Col xs={12} lg={4} className={"mb-4"}>
              <Card className={"shadow bg-dark h-100"}>
                <CardBody>
                  <CardTitle className={"text-poppins fs-2 text-primary"}>
                    Communicate
                  </CardTitle>
                  <CardText className={"text-urbanist fs-4"}>
                    Anyone can report abusive players with Admincore using it's report system. Reports use a simple UI to allow their creation in just a few seconds. Staff even have access to an extensive system for managing and dealing with reports.
                  </CardText>
                </CardBody>
              </Card>
            </Col>
            <Col xs={12} lg={4} className={"mb-4"}>
              <Card className={"shadow bg-dark h-100"}>
                <CardBody>
                  <CardTitle className={"text-poppins fs-2 text-primary"}>
                    Monitor
                  </CardTitle>
                  <CardText className={"text-urbanist fs-4"}>
                    From in-game activity to inventory management and even <a href={"https://ipqualityscore.com"} className={"text-link text-decoration-none"}>malicious activity across the internet</a>, Admincore allows staff members to monitor multiple aspects of players at any time. Staff will never need to question whether a player is truly abusive with Admincore's enhancements to spectating and monitoring.
                  </CardText>
                </CardBody>
              </Card>
            </Col>
            <Col xs={12} lg={4} className={"mb-4"}>
              <Card className={"shadow bg-dark h-100"}>
                <CardBody>
                  <CardTitle className={"text-poppins fs-2 text-primary"}>
                    Punish
                  </CardTitle>
                  <CardText className={"text-urbanist fs-4"}>
                    Staff are just a few clicks away from banning and muting players with Admincore's advanced punishment UI. Punishment commands are replaced with easy-to-use UIs and reasons are based on templates established in the config, enhancing and standardizing your team's workflow.
                  </CardText>
                </CardBody>
              </Card>
            </Col>
          </Row>
          <div className={css.spacer}/>
          <div className={css.spacer}/>
          <h1 className={css.mainText}>Stay in the know</h1>
          <div className={css.spacer}/>
          <Row>
            <Col xs={12} lg={4} className={"mb-4"}>
              <Card className={"shadow bg-dark h-100"}>
                <CardBody>
                  <CardTitle className={"text-poppins fs-2 text-primary"}>
                    Chat
                  </CardTitle>
                  <CardText className={"text-urbanist fs-4"}>
                    Staff have access to a private chat, making it easy to communicate secrets. Admincore even supports linking a Discord channel to the chat room, allowing offline staff to keep up too.
                  </CardText>
                </CardBody>
              </Card>
            </Col>
            <Col xs={12} lg={4} className={"mb-4"}>
              <Card className={"shadow bg-dark h-100"}>
                <CardBody>
                  <CardTitle className={"text-poppins fs-2 text-primary"}>
                    Aleart
                  </CardTitle>
                  <CardText className={"text-urbanist fs-4"}>
                    Staff recieve notifications for nearly everything moderation related. Whether it's a staff member entering Admincore's spectator mode or a player being punished, staff are kept aware of what's happening. Like chatting, staff alearts can also be linked to a Discord channel, making sure no staff member is left in the dark.
                  </CardText>
                </CardBody>
              </Card>
            </Col>
            <Col xs={12} lg={4} className={"mb-4"}>
              <Card className={"shadow bg-dark h-100"}>
                <CardBody>
                  <CardTitle className={"text-poppins fs-2 text-primary"}>
                    Log
                  </CardTitle>
                  <CardText className={"text-urbanist fs-4"}>
                    Admincore keeps logs of every punishment made using Admincore, lowering the risk of corrupt staff. If a staff member abuses their power, server owners can make sure it doesn't go unnoticed.
                  </CardText>
                </CardBody>
              </Card>
            </Col>
          </Row>
          <div className={css.spacer}/>
          <div className={css.spacer}/>
          <h1 className={css.mainText}>Minimize errors</h1>
          <div className={css.spacer}/>
          <Row>
            <Col xs={12} lg={4} className={"mb-4"}>
              <Card className={"shadow bg-dark h-100"}>
                <CardBody>
                  <CardTitle className={"text-poppins fs-2 text-primary"}>
                    Idiot-proof
                  </CardTitle>
                  <CardText className={"text-urbanist fs-4"}>
                    Admincore makes sure server owners don't run the plugin incorrectly, shutting the server down in the absence of a config and warning administrators when it detects a reload. Admincore even has plugin-wide broadcasts, making sure operators know the latest about Admincore.
                  </CardText>
                </CardBody>
              </Card>
            </Col>
            <Col xs={12} lg={4} className={"mb-4"}>
              <Card className={"shadow bg-dark h-100"}>
                <CardBody>
                  <CardTitle className={"text-poppins fs-2 text-primary"}>
                    Up-to-date
                  </CardTitle>
                  <CardText className={"text-urbanist fs-4"}>
                    Admincore checks for updates on every startup, letting admins know if there's a new version they should be using.
                  </CardText>
                </CardBody>
              </Card>
            </Col>
            <Col xs={12} lg={4} className={"mb-4"}>
              <Card className={"shadow bg-dark h-100"}>
                <CardBody>
                  <CardTitle className={"text-poppins fs-2 text-primary"}>
                    Verbose
                  </CardTitle>
                  <CardText className={"text-urbanist fs-4"}>
                    Like most plugins, Admincore will inevitably have bugs. Unlike most plugins however, Admincore attempts to either log or store errors with as much precision as possible, making it easy for operators to fix something on their end or report a bug in the plugin itself.
                  </CardText>
                </CardBody>
              </Card>
            </Col>
          </Row>
          <div className={css.spacer}/>
          <div className={css.spacer}/>
          <h1 className={css.mainText}>What are you waiting for?</h1>
          {/*<div className={css.spacer}/>*/}
          <a href={PathController.getAbsolutePath("download")} className={`btn btn-primary text-urbanist me-sm--3 shadow ${css.titleDownloadBtn}`}>Download Now</a>
          <Link to={PathController.getAbsolutePath("docs")} className={`btn btn-link text-primary text-link text-urbanist ms-sm-3 ${css.titleDownloadBtn} ${css.titleGetStartedBtn}`} id={"homepage-btn-get-started"}>
            Read The Docs
          </Link>
          <div className={css.spacer}/>
        </Container>
      </PageWrapper>
    </React.Fragment>
  );
};

export default Home;