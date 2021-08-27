import React, { FC, ReactElement } from "react";
import { Container } from "reactstrap";
import logo from "../static/logo.png";
import { motion } from "framer-motion";
import { PageAnimationController } from "../utils/PageAnimationController";

interface Props {

}

const Home: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <motion.div initial={PageAnimationController.initial} animate={PageAnimationController.animate} exit={PageAnimationController.exit} transition={PageAnimationController.transition}>
        <Container fluid={true}>
          <Container className={"text-center"}>
            <h1>bro</h1>
          </Container>
        </Container>
      </motion.div>
    </React.Fragment>
  );
};

export default Home;