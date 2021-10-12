import React, { FC, ReactElement } from "react";
import { PageWrapper } from "../../utils/PageWrapper";
import { CardBody, CardTitle } from "reactstrap";
import { Link } from "react-router-dom";
import PathController from "../../utils/PathController";
import { DocsWrapper } from "./DocsWrapper";

interface Props {

}

const DocsHome: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <PageWrapper>
        <DocsWrapper url={"DocsHome.tsx"}>
          <React.Fragment>
            <CardTitle className={"text-primary text-poppins fs-3"}>Welcome</CardTitle>
            <CardBody className={"text-light text-urbanist fs-5 py-0"}>Welcome to Admincore's documentation! Here you can find setup guides, command and permission references, and other information about Admincore. We recommend using a guide if you're a first-time user, but if you just need to find something quick, use a reference.</CardBody>
          </React.Fragment>
        </DocsWrapper>
      </PageWrapper>
    </React.Fragment>
  );
};

export { DocsHome };