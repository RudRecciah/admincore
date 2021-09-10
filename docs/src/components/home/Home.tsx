import React, { EffectCallback, FC, ReactElement, useEffect, useRef, useState } from "react";
import { Col, Container, Row } from "reactstrap";
import { PageWrapper } from "../../utils/PageWrapper";
import css from "./Home.module.css";
import splash from "../../static/splash.png";
import PathController from "../../utils/PathController";
import { Link } from "react-router-dom";
import inspect from "../../static/inspect.png";
import punish from "../../static/punish.png";

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
                <Link to={PathController.getAbsolutePath("docs/guide")} className={`btn btn-link text-primary text-link text-urbanist ms-sm-3 ${css.titleDownloadBtn} ${css.titleGetStartedBtn}`} id={"homepage-btn-get-started"}>
                  Get Started
                </Link>
              </div>
            </Col>
            <Col>
              <img src={splash} alt={"Some of Admincore's interfaces."} className={`img-fluid ${css.splash}`}/>
            </Col>
          </Row>
          <div className={css.spacer}/>
          <Row className={"flex-column-reverse flex-lg-row"}>
            <Col xs={12} lg={6} className={"text-center text-lg-start"}>
              <div className={css.inspectImageContainer}>
                <img src={inspect} alt={"View player information like bans, aliases, and more."} className={`img-fluid ${css.inspectImage}`}/>
              </div>
            </Col>
            <Col xs={12} lg={6} className={"text-center text-lg-end"}>
              <div className={css.inspectCopy}>
                <h1 className={css.landingHeader}>Never question a player's identity</h1>
                <p className={css.landingText}>With Admincore, you can inspect your entire playerbase for information like their past punishments, alternate accounts, and even malicious activity across the internet with <a href={"https://www.ipqualityscore.com/"} className={"text-decoration-none text-link"}>IPQS</a> integration.</p>
              </div>
            </Col>
          </Row>
          <Row className={"flex-column flex-lg-row"}>
            <Col xs={12} lg={6} className={"text-center text-lg-start"}>
              <div className={css.punishCopy}>
                <h1 className={css.landingHeader}>Keep your playerbase in check</h1>
                <p className={css.landingText}>With easy-to-use player reporting and punishment handling, you can maintain a healthy playerbase. Account ban, IP ban, and mute players using Admincore's UI.</p>
              </div>
            </Col>
            <Col xs={12} lg={6} className={"text-center text-lg-end"}>
              <div className={css.punishImageContainer}>
                <img src={punish} alt={"Punish players easily."} className={`img-fluid ${css.punishImage}`}/>
              </div>
            </Col>
          </Row>
        </Container>
      </PageWrapper>
    </React.Fragment>
  );
};

export default Home;