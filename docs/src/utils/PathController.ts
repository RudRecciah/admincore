export default class PathController {

  //TODO: probably wont be able to use environment variables in github pages, so instead of throwing an error when one doesnt exist, just return ./admincore/path and pray

  /**Returns the absolute path from a non-root path relative to the public path*/
  public static getAbsolutePath(path: string): string {
    if(typeof process.env.PUBLIC_URL !== "string") {
      throw "You need to specify the environment variable PUBLIC_URL to get a path relative to it!";
    }else{
      //removes root and relative path, this forces the path to be a non-root path and makes sure the path will return as %PUBLIC_URL%/path instead of %PUBLIC_URL/./path
      if(path.startsWith("./")) {
        path = path.replace("./", "");
      }
      if(path.startsWith("/")) {
        path = path.replace("/", "");
      }
      return `${process.env.PUBLIC_URL}/${path}`;
    }
  }

  /**Returns the absolute path of the root path relative to the public path*/
  public static getAbsoluteRootPath(): string {
    if(typeof process.env.PUBLIC_URL !== "string") {
      throw "You need to specify the environment variable PUBLIC_URL to get a path relative to it!";
    }else{
      return `${process.env.PUBLIC_URL}/`;
    }
  }

  /**Returns the absolute path of the 404 path relative to the public path*/
  public static getAbsoluteNotFoundPath(): string {
    if(typeof process.env.PUBLIC_URL !== "string") {
      throw "You need to specify the environment variable PUBLIC_URL to get a path relative to it!";
    }else{
      return `${process.env.PUBLIC_URL}/*`;
    }
  }

}
