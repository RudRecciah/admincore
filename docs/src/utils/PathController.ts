export default class PathController {

  /**Returns the path relative to ./docs/*/
  public static getDocsPath(path: string): string {
    if(path.startsWith("./")) {
      path = path.replace("./", "");
    }
    if(path.startsWith("/")) {
      path = path.replace("/", "");
    }
    return this.getAbsolutePath(`/docs/${path}`);
  }

  /**Returns the absolute path from a non-root path relative to the public path*/
  public static getAbsolutePath(path: string): string {

    //removes root and relative path, this forces the path to be a non-root path and makes sure the path will return as %PUBLIC_URL%/path instead of %PUBLIC_URL/./path
    if(path.startsWith("./")) {
      path = path.replace("./", "");
    }
    if(path.startsWith("/")) {
      path = path.replace("/", "");
    }

    if(typeof process.env.PUBLIC_URL !== "string") {
      return `/admincore/${path}`;
    }else{
      return `${process.env.PUBLIC_URL}/${path}`;
    }
  }

  /**Returns the absolute path of the root path relative to the public path*/
  public static getAbsoluteRootPath(): string {
    if(typeof process.env.PUBLIC_URL !== "string") {
      return "/admincore/";
    }else{
      return `${process.env.PUBLIC_URL}/`;
    }
  }

  /**Returns the absolute path of the 404 path relative to the public path*/
  public static getDocsNotFoundPath(): string {
    if(typeof process.env.PUBLIC_URL !== "string") {
      return "/admincore/docs/*";
    }else{
      return `${process.env.PUBLIC_URL}/*`;
    }
  }

  /**Returns the absolute path of the 404 path relative to the public path*/
  public static getAbsoluteNotFoundPath(): string {
    if(typeof process.env.PUBLIC_URL !== "string") {
      return "/admincore/*";
    }else{
      return `${process.env.PUBLIC_URL}/*`;
    }
  }

}
