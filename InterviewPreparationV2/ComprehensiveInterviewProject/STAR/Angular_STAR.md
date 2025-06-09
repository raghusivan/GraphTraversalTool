# Angular Interview Questions in STAR Format

Below are key Angular interview questions following the **Situation, Task, Action, Result** method.

## Questions and Answers

### 1. How did you migrate an AngularJS app to Angular?
**Situation:** A legacy AngularJS application required modernization.
**Task:** Incrementally upgrade while maintaining functionality.
**Action:** Used the `ngUpgrade` library to run Angular and AngularJS side by side, rewriting modules gradually.
**Result:** The new Angular modules improved performance without a full rewrite.

### 2. Describe your strategy for lazy loading modules.
**Situation:** The initial bundle size slowed down page load time.
**Task:** Split the application into feature modules loaded on demand.
**Action:** Configured the router with `loadChildren` and used the Angular CLI to generate modules with their own routing.
**Result:** Reduced initial payload and improved navigation performance.

### 3. Explain how you implemented state management using NgRx.
**Situation:** Components had complex shared state across the application.
**Task:** Introduce a predictable state container.
**Action:** Implemented NgRx with actions, reducers, and selectors; leveraged Effects for side‑effects.
**Result:** Centralized state management simplified debugging and unit testing.
