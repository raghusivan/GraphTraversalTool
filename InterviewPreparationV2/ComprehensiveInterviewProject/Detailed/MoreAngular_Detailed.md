# More Angular Interview Questions with Explanations

These questions address advanced Angular topics with explanations and sample snippets.

## 1. What is Ahead-of-Time (AOT) compilation?
AOT compiles the Angular application during the build process, producing smaller bundles and faster rendering. Run `ng build --prod` to enable it.

## 2. How do you create a reusable library in Angular?
Use the Angular CLI to generate a library project with `ng generate library my-lib`. Export components and services, then publish the package to npm for reuse across applications.

## 3. When would you implement custom structural directives?
Create a structural directive to modify the DOM tree—for example, an `appIfFeature` directive that displays content only if a feature flag is enabled.
