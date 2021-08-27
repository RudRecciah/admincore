export class PageAnimationController {

  /** Default state of page animations. */
  public static initial = {
    x: -500,
    opacity: 0
  }

  /** Visible state of page animations. */
  public static animate = {
    x: 0,
    opacity: 1
  }

  /** Invisible state of page animations. */
  public static exit = {
    x: 500,
    opacity: 0
  }

  /** Transition of page animations. */
  public static transition = {
    type: "spring",
    duration: 0.7,
    bounce: 0
  }

}
