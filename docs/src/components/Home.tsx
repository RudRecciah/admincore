import React, { FC, ReactElement } from "react";
import Icon from "../utils/Icon";

interface Props {

}

const Home: FC<Props> = (): ReactElement => {
  return (
    <React.Fragment>
      <h1>you're not that guy pal</h1> <Icon font={"Material"} icon={"done"} className={"text-primary"}/>
    </React.Fragment>
  );
};

export default Home;