import React, { FC, ReactElement } from "react";
import { DocsWrapper } from "../../DocsWrapper";
import { PageWrapper } from "../../../../utils/PageWrapper";

interface Props {

}

const Misc: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <PageWrapper>
        <DocsWrapper url={"./sections/reference/Misc.tsx"}>
          <React.Fragment>

          </React.Fragment>
        </DocsWrapper>
      </PageWrapper>
    </React.Fragment>
  );
};

export { Misc };