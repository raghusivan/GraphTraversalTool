# Additional Angular Questions in STAR Format

These unique questions continue the **Situation, Task, Action, Result** style.

## Questions and Answers

### 1. How did you set up server-side rendering?
**Situation:** The application needed better SEO.
**Task:** Render pages on the server for faster initial load.
**Action:** Implemented Angular Universal and configured an Express server to deliver pre-rendered pages.
**Result:** Search engines indexed the app correctly and initial load times improved.

### 2. Explain your approach to unit testing Angular services.
**Situation:** Services contained complex business logic.
**Task:** Write reliable unit tests.
**Action:** Used Jasmine with the TestBed utility to mock dependencies and assert service output.
**Result:** Achieved high test coverage and caught regressions early.

### 3. Describe how you used Web Workers for heavy calculations.
**Situation:** CPU-intensive tasks blocked the UI.
**Task:** Offload processing from the main thread.
**Action:** Created Web Workers and leveraged `worker-plugin` to integrate with the Angular CLI build.
**Result:** Smooth user interface with no lag during computations.
