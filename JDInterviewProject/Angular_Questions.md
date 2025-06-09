# Angular Interview Questions

These questions cover key concepts for front-end development using Angular.

## Questions and Answers

### 1. What is the purpose of Angular modules?
**Answer:** Modules organize related components, directives, and services. They help with lazy loading and encapsulation of features, improving maintainability.

### 2. How do you handle state management in Angular applications?
**Answer:** Common approaches include using services to share data, leveraging `BehaviorSubject` for reactive updates, or integrating libraries such as NgRx when applications become complex.

### 3. Explain the difference between template-driven and reactive forms.
**Answer:** Template-driven forms use Angular directives in the template with minimal code in the component. Reactive forms are driven by explicit form models in the component class, providing more control and easier unit testing.

### 4. Describe how Angular's dependency injection works.
**Answer:** Angular's injector creates and provides services to components and other services. By defining providers, Angular instantiates dependencies and ensures each component receives the correct instance.

### 5. How do you optimize the performance of an Angular app?
**Answer:** Techniques include using Ahead-of-Time compilation, enabling change detection strategies like `OnPush`, and lazy loading feature modules to reduce the initial bundle size.
