import React, { FC, ReactElement } from "react";
import { AnimatePresence, motion } from "framer-motion";
import { Link } from "react-router-dom";
import PathController from "../../utils/PathController";
import Icon from "../../utils/Icon";

interface Props {
  sidebar: boolean;
  toggleSidebar: () => void;
}

const DocsMobileSidebar: FC<Props> = ({ sidebar, toggleSidebar }): ReactElement => {
  return (
    <React.Fragment>
      <AnimatePresence>
        {sidebar && (
          <motion.div className={"position-fixed top-0 left-0 sidebar z-1 d-md-none overflow-auto"} key={"sidebar"} initial={{ x: -320 }} animate={{ x: 0 }} transition={{ type: "spring", bounce: 0 }} exit={{ x: -320 }}>
            <div className={"w-100 h-100 d-flex flex-column"}>
              <Link to={PathController.getDocsPath("installation")} className={"sidebar-content d-flex flex-row w-100"} onClick={toggleSidebar}>
                <span className={"text-poppins d-flex flex-column justify-content-center align-items-center"}>Installation</span><span className={"flex-fill"}/><Icon font={"Material"} icon={"chevron_right"} className={"d-flex flex-column justify-content-center align-items-center sidebar-icon"}/></Link>
              <Link to={PathController.getDocsPath("updating")} className={"sidebar-content d-flex flex-row w-100"} onClick={toggleSidebar}>
                <span className={"text-poppins d-flex flex-column justify-content-center align-items-center"}>Updating Guide</span><span className={"flex-fill"}/><Icon font={"Material"} icon={"chevron_right"} className={"d-flex flex-column justify-content-center align-items-center sidebar-icon"}/></Link>
              <Link to={PathController.getDocsPath("commands")} className={"sidebar-content d-flex flex-row w-100"} onClick={toggleSidebar}>
                <span className={"text-poppins d-flex flex-column justify-content-center align-items-center"}>Commands</span><span className={"flex-fill"}/><Icon font={"Material"} icon={"chevron_right"} className={"d-flex flex-column justify-content-center align-items-center sidebar-icon"}/></Link>
              <Link to={PathController.getDocsPath("config")} className={"sidebar-content d-flex flex-row w-100"} onClick={toggleSidebar}>
                <span className={"text-poppins d-flex flex-column justify-content-center align-items-center"}>Configuration</span><span className={"flex-fill"}/><Icon font={"Material"} icon={"chevron_right"} className={"d-flex flex-column justify-content-center align-items-center sidebar-icon"}/></Link>
              <Link to={PathController.getDocsPath("permissions")} className={"sidebar-content d-flex flex-row w-100"} onClick={toggleSidebar}>
                <span className={"text-poppins d-flex flex-column justify-content-center align-items-center"}>Permissions</span><span className={"flex-fill"}/><Icon font={"Material"} icon={"chevron_right"} className={"d-flex flex-column justify-content-center align-items-center sidebar-icon"}/></Link>
              <Link to={PathController.getDocsPath("misc")} className={"sidebar-content d-flex flex-row w-100"} onClick={toggleSidebar}>
                <span className={"text-poppins d-flex flex-column justify-content-center align-items-center"}>Other Information</span><span className={"flex-fill"}/><Icon font={"Material"} icon={"chevron_right"} className={"d-flex flex-column justify-content-center align-items-center sidebar-icon"}/></Link>
              <span/>
            </div>
          </motion.div>
        )}
      </AnimatePresence>
    </React.Fragment>
  );
};

export { DocsMobileSidebar };