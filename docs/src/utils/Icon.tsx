import React, { Component } from "react";

interface Props {
  font: "Material";
  icon: string;
}

interface State {

}

export default class Icon extends Component<Props, State> {
  constructor(props: Readonly<Props>) {
    super(props);
    this.state = {};
  }

  render(): JSX.Element {
    return (
      <React.Fragment>
        {this.props.font === "Material" && <span className={"material-icons"}>{this.props.icon}</span>}
      </React.Fragment>
    );
  }
}