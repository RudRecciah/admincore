import React, { Component } from "react";

interface Props {

}

interface State {

}

export default class Home extends Component<Props, State> {
  constructor(props: Readonly<Props>) {
    super(props);
    this.state = {};
  }

  render(): JSX.Element {
    return (
      <React.Fragment>
        <h1>HEY</h1>
      </React.Fragment>
    );
  }
}