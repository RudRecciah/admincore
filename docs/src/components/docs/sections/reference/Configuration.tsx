import React, { FC, ReactElement } from "react";
import { DocsWrapper } from "../../DocsWrapper";
import { PageWrapper } from "../../../../utils/PageWrapper";

interface Props {

}

const Configuration: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <PageWrapper>
        <DocsWrapper url={"./sections/reference/Configuration.tsx"}>
          <React.Fragment>

          </React.Fragment>
        </DocsWrapper>
      </PageWrapper>
    </React.Fragment>
  );
};

export { Configuration };