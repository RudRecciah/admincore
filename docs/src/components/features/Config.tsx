import React, { FC, ReactElement } from "react";
import { Card, CardBody, CardTitle } from "reactstrap";

interface Props {

}

const Config: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <Card className={"shadow bg-dark p-3"}>
        {/*<CardTitle className={"text-poppins text-primary"}>*/}
        {/*  */}
        {/*</CardTitle>*/}
      </Card>
    </React.Fragment>
  );
};

export { Config };