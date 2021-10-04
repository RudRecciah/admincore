import React, { FC, ReactElement } from "react";
import { Badge, Card, CardBody, CardSubtitle, CardTitle, Col } from "reactstrap";
import css from "./Features.module.css";

interface Props {
  title: string | string[];
  body: string | string[];
  permissionChildren?: string | string[] | undefined;
  last: boolean;
}

const Permission: FC<Props> = ({ title, body, permissionChildren, last }): ReactElement => {

  const permissionHasChildren = (item: string | string[] | undefined): ReactElement | void => {
    if(Array.isArray(item)) {
      return (
        <React.Fragment>
          <CardSubtitle tag={"h3"} className={"text-primary text-poppins"}>
            Children
          </CardSubtitle>
          <span className={"w-100"}>
            {item.map(child => <Badge color={"primary"} className={`text-urbanist fs-6 px-3 text-light bg-primary ${css.permissionChildren}`}>admincore.{child}</Badge>)}
          </span>
        </React.Fragment>
      );
    }
  };

  return (
    <React.Fragment>
      {last
        ?
        <Col className={"mb-4"}>
          <Card className={"bg-dark h-100 shadow p-3"}>
            <CardTitle className={"text-poppins text-primary fs-2"}>
              admincore.{title} <span className={"badge bg-primary text-light text-poppins"}>OP</span>
            </CardTitle>
            <CardBody className={"text-urbanist text-light fs-4"}>
              {body}
            </CardBody>
            {permissionHasChildren(permissionChildren)}
          </Card>
        </Col>
        :
        <Col xs={12} lg={6} className={"mb-4"}>
          <Card className={"bg-dark h-100 shadow p-3"}>
            <CardTitle className={"text-poppins text-primary fs-2"}>
              admincore.{title} <span className={"badge bg-primary text-light text-poppins"}>OP</span>
            </CardTitle>
            <CardBody className={"text-urbanist text-light fs-4"}>
              {body}
            </CardBody>
            {permissionHasChildren(permissionChildren)}
          </Card>
        </Col>
      }
    </React.Fragment>
  );
};

export { Permission };