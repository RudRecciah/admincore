import React, { FC, ReactElement } from "react";

interface Props {
  font: "Material";
  icon: string;
  className?: string;
}

const Icon: FC<Props> = ({ font, icon, className }): ReactElement => {
  const classes = className ?? "";
  return (
    <React.Fragment>
      <React.Fragment>
        {font === "Material" && <span className={`material-icons ${classes}`}>{icon}</span>}
      </React.Fragment>
    </React.Fragment>
  );
};

export default Icon;