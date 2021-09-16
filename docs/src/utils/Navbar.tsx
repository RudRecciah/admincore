import React, { FC, ReactElement, useState } from "react";
import { Container, Nav, Navbar as Navi, NavItem } from "reactstrap";
import logo from "../static/logo.png";
import { Link } from "react-router-dom";
import PathController from "./PathController";
import Icon from "./Icon";
import { AnimatePresence, motion } from "framer-motion";

interface Props {

}

const Navbar: FC<Props> = (): ReactElement => {

  const [sidebar, setSidebar] = useState(false);
  
  const toggleSidebar = (): void => {
    setSidebar(!sidebar);
  };

  return (
    <React.Fragment>
      <header>
        <nav>
          <Container fluid={true} className={"p-0"}>
            <Navi color={"dark"} className={"p-3 d-flex"}>
              <Nav className={"d-flex flex-row w-100"}>
                <NavItem>
                  <Link to={PathController.getAbsoluteRootPath()}>
                    <img src={logo} alt={"Admincore"} className={"rounded shadow"} height={48} width={48}/>
                  </Link>
                </NavItem>
                <NavItem className={"d-none d-md-flex flex-column justify-content-center align-items-center ps-5"}>
                  <Link to={PathController.getAbsolutePath("about")} className={"text-decoration-none"}>
                    <span className={"text-urbanist fs-5 text-decoration-none text-primary text-link"}>About</span>
                  </Link>
                </NavItem>
                <NavItem className={"d-none d-md-flex flex-column justify-content-center align-items-center ps-5"}>
                  <Link to={PathController.getAbsolutePath("features")} className={"text-decoration-none"}>
                    <span className={"text-urbanist fs-5 text-decoration-none text-primary text-link"}>Features</span>
                  </Link>
                </NavItem>
                <NavItem className={"d-none d-md-flex flex-column justify-content-center align-items-center ps-5"}>
                  <Link to={PathController.getAbsolutePath("docs")} className={"text-decoration-none"}>
                    <span className={"text-urbanist fs-5 text-decoration-none text-primary text-link"}>Docs</span>
                  </Link>
                </NavItem>
                <NavItem className={"d-none d-md-flex flex-column justify-content-center align-items-center ps-5"}>
                  <Link to={PathController.getAbsolutePath("bugs")} className={"text-decoration-none"}>
                    <span className={"text-urbanist fs-5 text-decoration-none text-primary text-link"}>Bug Reports</span>
                  </Link>
                </NavItem>
                <NavItem className={"flex-fill"}/>
                <NavItem className={"d-none d-md-flex flex-column justify-content-center align-items-center ps-5"}>
                  <a href={PathController.getAbsolutePath("download")} className={"text-decoration-none"}>
                    <span className={"text-urbanist fs-5 text-decoration-none text-primary btn btn-primary text-light nav-btn-height"}>Download</span>
                  </a>
                </NavItem>
                <NavItem className={"d-flex d-md-none flex-column justify-content-center align-items-center ps-5"}>
                  <button className={"btn btn-link text-light"} onClick={toggleSidebar}>
                    <Icon font={"Material"} icon={"menu"} className={"cursor-pointer"}/>
                  </button>
                </NavItem>
              </Nav>
            </Navi>
          </Container>
        </nav>
        {sidebar && (
          <nav className={"d-md-none"}>
            <div className={"position-absolute top-0 left-0 w-100 min-vh-100"} onClick={toggleSidebar}/>
          </nav>
        )}
        <AnimatePresence>
          {sidebar && (
            <motion.div className={"position-fixed top-0 left-0 sidebar z-1 d-md-none overflow-auto"} key={"sidebar"} initial={{ x: -320 }} animate={{ x: 0 }} transition={{ type: "spring", bounce: 0 }} exit={{ x: -320 }}>
              <div className={"w-100 h-100 d-flex flex-column"}>
                <Link to={PathController.getAbsolutePath("about")} className={"sidebar-content d-flex flex-row w-100"} onClick={toggleSidebar}>
                  <span className={"text-poppins d-flex flex-column justify-content-center align-items-center"}>About</span><span className={"flex-fill"}/><Icon font={"Material"} icon={"chevron_right"} className={"d-flex flex-column justify-content-center align-items-center sidebar-icon"}/>
                </Link>
                <Link to={PathController.getAbsolutePath("features")} className={"sidebar-content d-flex flex-row w-100"} onClick={toggleSidebar}>
                  <span className={"text-poppins d-flex flex-column justify-content-center align-items-center"}>Features</span><span className={"flex-fill"}/><Icon font={"Material"} icon={"chevron_right"} className={"d-flex flex-column justify-content-center align-items-center sidebar-icon"}/>
                </Link>
                <Link to={PathController.getAbsolutePath("docs")} className={"sidebar-content d-flex flex-row w-100"} onClick={toggleSidebar}>
                  <span className={"text-poppins d-flex flex-column justify-content-center align-items-center"}>Docs</span><span className={"flex-fill"}/><Icon font={"Material"} icon={"chevron_right"} className={"d-flex flex-column justify-content-center align-items-center sidebar-icon"}/>
                </Link>
                <Link to={PathController.getAbsolutePath("bugs")} className={"sidebar-content d-flex flex-row w-100"} onClick={toggleSidebar}>
                  <span className={"text-poppins d-flex flex-column justify-content-center align-items-center"}>Bug Reports</span><span className={"flex-fill"}/><Icon font={"Material"} icon={"chevron_right"} className={"d-flex flex-column justify-content-center align-items-center sidebar-icon"}/>
                </Link>
                <div className={"flex-fill"}/>
                <a href={PathController.getAbsolutePath("download")} className={"sidebar-content d-flex flex-row btn btn-primary w-100 rounded-0"} onClick={toggleSidebar}>
                  <span className={"text-poppins d-flex flex-column justify-content-center align-items-center"}>Download</span><span className={"flex-fill"}/><Icon font={"Material"} icon={"chevron_right"} className={"d-flex flex-column justify-content-center align-items-center sidebar-icon"}/>
                </a>
              </div>
            </motion.div>
          )}
        </AnimatePresence>
      </header>
    </React.Fragment>
  );
};

export default Navbar;