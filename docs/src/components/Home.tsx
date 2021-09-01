import React, { EffectCallback, FC, ReactElement, useEffect, useRef, useState } from "react";
import { Container } from "reactstrap";
import { AnimatePresence, motion } from "framer-motion";
import { PageWrapper } from "../utils/PageWrapper";
import { useForceUpdate } from "../utils/useForceUpdate";
import { v4 as uuidv4 } from "uuid";
import { sleep } from "../utils/sleep";
import Typing from "react-typing-animation";

interface Props {

}

const Home: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <PageWrapper>
        <Container fluid={true} className={"text-light landing-header"}>
          <h1 className={"landing-bigtext position-relative left-0 top-0"}>
            Moderate
          </h1>
          <h1>with ease.</h1>
        </Container>
      </PageWrapper>
    </React.Fragment>
  );
};

export default Home;