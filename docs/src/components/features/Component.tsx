import React, { FC, ReactElement } from "react";
import { Card, CardBody, CardTitle, Col } from "reactstrap";

interface Props {
  title: string;
  body?: string;
  last: boolean;
}

const Component: FC<Props> = ({ title, body, last }): ReactElement => {
  return (
    <React.Fragment>
      {last
        ?
        <Col className={"mb-4"}>
          <Card className={"shadow bg-dark h-100 p-3"}>
            <CardTitle className={"text-poppins fs-2 text-primary"}>
              {title}
            </CardTitle>
            {body && <React.Fragment>
              <CardBody className={"text-urbanist fs-4"}>
                {body}
              </CardBody>
            </React.Fragment>}
          </Card>
        </Col> 
        :
        <Col xs={12} sm={6} xl={4} className={"mb-4"}>
          <Card className={"shadow bg-dark h-100 p-3"}>
            <CardTitle className={"text-poppins fs-2 text-primary"}>
              {title}
            </CardTitle>
            {body && <React.Fragment>
              <CardBody className={"text-urbanist fs-4"}>
                {body}
              </CardBody>
            </React.Fragment>}
          </Card>
        </Col>
      }
    </React.Fragment>
  );
};

export { Component };