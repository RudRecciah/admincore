import { useState } from "react";

export const useForceUpdate = (): () => void => {
  const [value, setState] = useState(true);
  return (): void => setState(!value);
};