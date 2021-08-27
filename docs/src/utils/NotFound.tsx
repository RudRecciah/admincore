import React, { FC, ReactElement } from "react";
import { Link } from "react-router-dom";
import PathController from "./PathController";
import { Container } from "reactstrap";
import { motion } from "framer-motion";
import { PageAnimationController } from "./PageAnimationController";

interface Props {

}

const NotFound: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <motion.div initial={PageAnimationController.initial} animate={PageAnimationController.animate} exit={PageAnimationController.exit} transition={PageAnimationController.transition} className={"flex-fill d-flex flex-column"}>
        <Container fluid={true} className={"flex-fill d-flex flex-column"}>
          <div className={"d-flex flex-column justify-content-center align-items-center text-center flex-fill"}>
            <div>
              <h1 className={"text-primary"}>404</h1>
              <p className={"fs-3 text-light"}>Seems like this page doesn't exist.</p>
              <Link to={PathController.getAbsolutePath("")} className={"btn btn-primary"}>Go Home</Link>
            </div>
          </div>
        </Container>
      </motion.div>
    </React.Fragment>
  );
};

export default NotFound;