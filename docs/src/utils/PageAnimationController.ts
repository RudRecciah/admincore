export class PageAnimationController {

  /** Default state of page animations. */
  public static initial = {
    scale: 1.2,
    opacity: 0
  }

  /** Visible state of page animations. */
  public static animate = {
    scale: 1,
    opacity: 1
  }

  /** Invisible state of page animations. */
  public static exit = {
    scale: 0.8,
    opacity: 0
  }

  /** Transition of page animations. */
  public static transition = {
    type: "spring",
    duration: 0.5,
    bounce: 0
  }

}
