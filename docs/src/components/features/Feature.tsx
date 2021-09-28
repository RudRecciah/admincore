import React, { FC, ReactElement } from "react";
import { Card, CardBody, CardTitle, Col } from "reactstrap";

interface Props {
  title: string;
  body: string;
}

const Feature: FC<Props> = (title, body): ReactElement => {
  return (
    <React.Fragment>
      <Col xs={12} sm={6} lg={4} xl={3}>
        <Card className={"shadow bg-dark h-100"}>
          <CardTitle className={"text-poppins fs-2 text-primary"}>
            {title}
          </CardTitle>
          <CardBody className={"text-urbanist fs-4"}>
            {body}
          </CardBody>
        </Card>
      </Col>
    </React.Fragment>
  );
};

export { Feature };