# Angular Interview Questions in STAR Format

The following questions use the **Situation, Task, Action, Result** (STAR) approach to explain common Angular interview topics. Code snippets demonstrate important concepts.

## Questions and Answers

### 1. How do you define a component in Angular?
**Situation:** Building a UI element as a reusable component.
**Task:** Create a new component with a template and logic.
**Action:** Use the CLI command `ng generate component my-comp` to scaffold files. Implement the selector, template, and class logic in TypeScript.
**Result:** The component can be reused across the app and referenced with `<app-my-comp></app-my-comp>`.
```ts
@Component({
  selector: 'app-my-comp',
  template: '<h1>Hello</h1>'
})
export class MyComp {}
```

### 2. Explain dependency injection in Angular.
**Situation:** Components require services for data or logic.
**Task:** Provide those services in a decoupled manner.
**Action:** Declare services with `@Injectable` and register them in a module or component provider array. Angular's injector supplies instances where needed.
**Result:** Modules remain modular and services can be mocked for testing.
```ts
@Injectable({ providedIn: 'root' })
export class DataService {}
```

### 3. Describe Observables vs Promises.
**Situation:** Handling asynchronous operations like HTTP requests.
**Task:** Decide whether to use Observables or Promises.
**Action:** Observables from RxJS support multiple values and operators, e.g. `http.get('/api').subscribe(...)`; Promises represent a single future value, e.g. `fetch('/api').then(...)`.
**Result:** Observables allow complex async flows, while Promises suit one-off operations.

### 4. How does Angular change detection work?
**Situation:** Updates to component state must reflect in the view.
**Task:** Trigger view updates efficiently.
**Action:** Angular's change detector checks data bindings when events occur or when `markForCheck()` is called. The `OnPush` strategy limits checks to input changes.
**Result:** The UI stays in sync while minimizing unnecessary DOM updates.
```ts
@Component({
  selector: 'app-example',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: '{{count}}'
})
export class ExampleComponent {
  @Input() count = 0;
}
```

### 5. What are reactive forms?
**Situation:** Building forms that scale with dynamic validation.
**Task:** Manage complex form state in code.
**Action:** Use `FormGroup`, `FormControl`, and `FormBuilder` to construct the form model in TypeScript and subscribe to value or status changes.
**Result:** Reactive forms provide explicit control, easy unit testing, and dynamic validation rules.
```ts
form = this.fb.group({
  name: ['', Validators.required]
});
```

### 6. Give an example of a structural directive.
**Situation:** Conditionally displaying or repeating elements.
**Task:** Use Angular syntax to manipulate DOM layout.
**Action:** Apply `*ngIf` to show/hide markup or `*ngFor` to iterate over a list.
**Result:** Structural directives change the DOM tree based on application state.
```html
<div *ngIf="loggedIn">Welcome</div>
```

### 7. Explain pipes in Angular.
**Situation:** Formatting data for display.
**Task:** Transform values in templates.
**Action:** Use built-in pipes like `date` or create custom pipes implementing `transform`.
**Result:** Data appears formatted without altering the underlying model.
```ts
{{ birthday | date:'longDate' }}
```

### 8. How do you handle HTTP errors?
**Situation:** API calls may fail due to network or server issues.
**Task:** Gracefully respond to errors.
**Action:** Use HttpClient with `catchError` from RxJS to intercept errors and return a friendly message or fallback value.
**Result:** The application remains robust and informs the user appropriately.
```ts
this.http.get(url).pipe(catchError(() => of([]))).subscribe();
```

### 9. What is lazy loading of modules?
**Situation:** Large apps need faster initial load time.
**Task:** Load feature modules only when required.
**Action:** Configure the router with `loadChildren` to import modules asynchronously.
**Result:** Only necessary code downloads, improving startup performance.
```ts
{ path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) }
```

### 10. Describe a guard in routing.
**Situation:** Restricting access to certain routes.
**Task:** Check conditions before navigation.
**Action:** Implement `CanActivate` or other guard interfaces and return `true`/`false` or a `UrlTree`.
**Result:** Users are redirected if they do not meet requirements, improving security.
```ts
canActivate(): boolean {
  return this.auth.isLoggedIn();
}
```

### 11. How do you share data between components?
**Situation:** Sibling components must communicate.
**Task:** Pass data without tight coupling.
**Action:** Use a shared service with an RxJS `Subject`, or pass inputs/outputs via parent components.
**Result:** Components remain modular while still exchanging information.
```ts
// in service
message$ = new Subject<string>();
```

### 12. What is the purpose of NgZone?
**Situation:** Some asynchronous operations happen outside Angular's zone.
**Task:** Ensure change detection runs after those operations.
**Action:** Inject `NgZone` and run code inside `ngZone.run()` so Angular notices changes.
**Result:** The UI updates correctly even when external APIs trigger callbacks.
```ts
this.zone.run(() => this.value = newValue);
```

### 13. Explain unit testing a component with TestBed.
**Situation:** A component has logic that should be verified automatically.
**Task:** Write a unit test using Angular utilities.
**Action:** Configure TestBed with the component and dependencies, create the component via `TestBed.createComponent`, and assert on its behavior.
**Result:** Regressions are caught early, increasing confidence in code changes.
```ts
testBed.configureTestingModule({ declarations: [MyComponent] });
```

### 14. Give an example of a custom validator.
**Situation:** Need to enforce domain-specific input rules.
**Task:** Create a reusable validator for reactive forms.
**Action:** Define a function that returns a validation error object when rules fail.
**Result:** Form controls using this validator display errors when invalid.
```ts
const noSpecialChars: ValidatorFn = control => /[^a-zA-Z0-9]/.test(control.value) ? {special: true} : null;
```

### 15. How do you configure global error handling?
**Situation:** Unhandled exceptions should be logged centrally.
**Task:** Provide an implementation of `ErrorHandler`.
**Action:** Create a class extending `ErrorHandler` and override `handleError` to send errors to a logging service. Provide it in the root module.
**Result:** Errors are captured and reported consistently across the app.
```ts
@Injectable()
export class GlobalHandler extends ErrorHandler {
  override handleError(err: any) { /* log to server */ }
}
```

### 16. What is ahead-of-time (AOT) compilation?
**Situation:** Angular templates are normally compiled at runtime.
**Task:** Improve startup performance and detect template errors earlier.
**Action:** Use the Angular CLI with the `--aot` flag or enable AOT in build options.
**Result:** Faster rendering in the browser and earlier detection of template issues.
```bash
ng build --aot
```

