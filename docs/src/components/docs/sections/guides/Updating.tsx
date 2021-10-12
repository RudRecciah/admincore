import React, { FC, ReactElement } from "react";
import { PageWrapper } from "../../../../utils/PageWrapper";
import { DocsWrapper } from "../../DocsWrapper";

interface Props {

}

const Updating: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <PageWrapper>
        <DocsWrapper url={"./sections/guides/Updating.tsx"}>
          <React.Fragment>

          </React.Fragment>
        </DocsWrapper>
      </PageWrapper>
    </React.Fragment>
  );
};

export { Updating };