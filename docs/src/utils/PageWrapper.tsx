import React, { FC, ReactElement } from "react";
import { PageAnimationController } from "./PageAnimationController";
import { Container } from "reactstrap";
import { motion } from "framer-motion";

interface Props {
  children?: ReactElement;
}

const PageWrapper: FC<Props> = ({ children }): ReactElement => {
  window.scrollTo(0, 0);
  return (
    <React.Fragment>
      <motion.div initial={PageAnimationController.initial} animate={PageAnimationController.animate} exit={PageAnimationController.exit} transition={PageAnimationController.transition} className={"overflow-hidden"}>
        <Container fluid={true} className={"p-0 overflow-auto"}>
          {children}
        </Container>
      </motion.div>
    </React.Fragment>
  );
};

export { PageWrapper };