import React, { FC, ReactElement } from "react";
import { CardBody, CardTitle } from "reactstrap";

interface Props {

}

const Test: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <CardTitle className={"text-poppins text-primary"}>
        Test page
      </CardTitle>
      <CardBody className={"text-urbanist"}>
        test
      </CardBody>
    </React.Fragment>
  );
};

export { Test };