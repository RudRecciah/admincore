import React, { FC, ReactElement } from "react";
import { DocsWrapper } from "../../DocsWrapper";
import { PageWrapper } from "../../../../utils/PageWrapper";

interface Props {

}

const Permissions: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <PageWrapper>
        <DocsWrapper url={"./sections/reference/Permissions.tsx"}>
          <React.Fragment>

          </React.Fragment>
        </DocsWrapper>
      </PageWrapper>
    </React.Fragment>
  );
};

export { Permissions };