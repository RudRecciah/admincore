import React, { FC, ReactElement, ReactNode, useEffect, useState } from "react";
import { request } from "@octokit/request";

interface Props {
  url: string;
  children?: ReactElement;
}

interface CommitWrapper {
  author: Id;
  commit: Commit
}

interface Id {
  id: number;
}

interface Commit {
  author: Author
}

interface Author {
  name: string,
  date: string
}

/**
 * @param url The path to this element's location on GitHub relative to RudRecciah/admincore/docs/src/components/docs/. For example, RudRecciah/admincore/docs/src/components/docs/DocsWrapper.tsx is either ./DocsWrapper.tsx, /DocsWrapper.tsx, or DocsWrapper.tsx.
 * @constructor
 */
const DocsWrapper: FC<Props> = ({ url, children }): ReactElement => {

  if(url.substring(0, 2) === "./") {
    url = url.substring(2);
  }
  if(url.substring(0, 1) === "/") {
    url = url.substring(1);
  }
  url = url.replaceAll("/", "%2F");

  const defaultCommitText = (
    <div className={"d-flex flex-row justify-content-start align-items-center"}>
      <span className="material-icons text-primary mx-2 fs-1">info</span>
      <span className={"text-light text-urbanist fs-6"}>Last updated sometime by someone.</span>
    </div>
  );

  const [commitText, updateCommitText] = useState(defaultCommitText);

  useEffect(() => {
    request(`GET /repos/rudrecciah/admincore/commits?path=docs%2Fsrc%2Fcomponents%2Fdocs%2F${url}&page=1&per_page=1`).then(response => {
      try {
        if(response.status === 200) {
          const commitWrapper = response.data[0] as CommitWrapper;
          const name = commitWrapper.commit.author.name;
          const date = new Date(Date.parse(commitWrapper.commit.author.date));
          const newCommitText = (
            <div className={"d-flex flex-row justify-content-start align-items-center"}>
              <span className="material-icons text-primary mx-2 fs-1">info</span>
              <span className={"text-light text-urbanist fs-6"}>Last updated on {date.toDateString().split(" ")[1]} {date.toDateString().split(" ")[2]}, {date.toDateString().split(" ")[3]} by {name}.</span>
            </div>
          );
          console.log(newCommitText);
          updateCommitText(newCommitText);
        }
      }catch(e) {
        throw `GET /repos/rudrecciah/admincore/commits?path=docs%2Fsrc%2Fcomponents%2Fdocs%2F${url}&page=1&per_page=1 failed. Please report to https://rudrecciah.dev/admincore/bugs.`;
      }
    });
  }, [0]);
  
  return (
    <React.Fragment>
      {children ?? <React.Fragment>{children}</React.Fragment>}
      <hr className={"text-primary"}/>
      {commitText}
    </React.Fragment>
  );
};

export { DocsWrapper };