
# More Angular Interview Questions in STAR Format

The questions below continue the STAR (Situation, Task, Action, Result) approach. They are designed to be unique and cover a wide range of Angular topics. Code snippets accompany many answers for clarity.

## Questions and Answers

### 1. How do you create a reactive form in Angular?
**Situation:** You need a form with dynamic validation.
**Task:** Build a form that captures user credentials.
**Action:** Use `FormBuilder` to define a `FormGroup` with controls and validators.
**Result:** The form can react to changes and display validation states.
```ts
constructor(private fb: FormBuilder) {}
loginForm = this.fb.group({
  username: ['', Validators.required],
  password: ['', [Validators.required, Validators.minLength(6)]]
});
```

### 2. Explain how Angular modules facilitate lazy loading.
**Situation:** The app grows and initial load slows down.
**Task:** Load feature modules only when required.
**Action:** Use the router to define lazy-loaded routes with the `loadChildren` syntax.
**Result:** The main bundle stays small and modules load on demand.
```ts
{ path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) }
```

### 3. What is the difference between `ngOnInit` and constructor in a component?
**Situation:** A component initializes data from a service.
**Task:** Decide where to place initialization logic.
**Action:** Use the constructor for dependency injection and `ngOnInit` for running initialization code.
**Result:** Services are available in `ngOnInit` and logic executes once after component creation.
```ts
constructor(private service: DataService) {}
ngOnInit(): void {
  this.service.load();
}
```

### 4. How do you implement custom validators in reactive forms?
**Situation:** A password field must meet specific complexity rules.
**Task:** Provide form validation beyond built-in validators.
**Action:** Create a function that returns a `ValidatorFn` and attach it to the control.
**Result:** The form will show errors when the rule isn't satisfied.
```ts
export function specialCharValidator(): ValidatorFn {
  return (control: AbstractControl) =>
    /[^a-zA-Z0-9]/.test(control.value) ? null : { missingSpecial: true };
}
this.fb.control('', [Validators.required, specialCharValidator()]);
```

### 5. Describe how Angular's `ChangeDetectionStrategy.OnPush` works.
**Situation:** A component rerenders more than necessary.
**Task:** Improve performance by limiting change detection cycles.
**Action:** Add `changeDetection: ChangeDetectionStrategy.OnPush` to the component metadata.
**Result:** Angular checks for changes only when inputs reference change or events occur inside the component.

### 6. What are Angular interceptors and how are they used?
**Situation:** Every HTTP call needs an authorization header.
**Task:** Attach a token to each request.
**Action:** Implement `HttpInterceptor` to modify requests and provide it in the app module.
**Result:** The header is automatically added, centralizing request configuration.
```ts
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const cloned = req.clone({ setHeaders: { Authorization: 'Bearer token' } });
    return next.handle(cloned);
  }
}
```

### 7. Explain how to use `ng-content` for content projection.
**Situation:** A reusable dialog component must display custom content.
**Task:** Allow parent components to project markup into the dialog.
**Action:** Place `<ng-content></ng-content>` inside the dialog's template.
**Result:** Anything inside `<app-dialog>` tags is projected into the dialog.

### 8. What is `FormArray` and when would you use it?
**Situation:** A survey has a varying number of questions.
**Task:** Capture an array of dynamic form controls.
**Action:** Use `FormArray` to manage a list of controls that can grow or shrink.
**Result:** The form remains reactive regardless of how many questions are added.
```ts
questions = this.fb.array([
  this.fb.control('')
]);
addQuestion() {
  this.questions.push(this.fb.control(''));
}
```

### 9. How do you implement route guards in Angular?
**Situation:** Users must be authenticated before accessing certain routes.
**Task:** Prevent navigation if the user isn't logged in.
**Action:** Implement `CanActivate` and return a boolean or `UrlTree` from the guard.
**Result:** Unauthorized users are redirected to the login page.
```ts
@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private auth: AuthService, private router: Router) {}
  canActivate(): boolean | UrlTree {
    return this.auth.isLoggedIn() || this.router.createUrlTree(['/login']);
  }
}
```

### 10. Describe the `async` pipe and its benefits.
**Situation:** Components subscribe to observables and manually manage subscriptions.
**Task:** Simplify subscription management in templates.
**Action:** Use the `| async` pipe to unwrap the observable and automatically handle unsubscribing.
**Result:** Cleaner templates and reduced memory leaks.
```html
<div *ngIf="user$ | async as user">{{ user.name }}</div>
```

### 11. How do you share data between sibling components?
**Situation:** Two components under the same parent need to exchange data.
**Task:** Implement a mechanism to pass information without tight coupling.
**Action:** Use a shared service with a `Subject` to emit and subscribe to events.
**Result:** The components remain independent and share updates through the service.
```ts
@Injectable({ providedIn: 'root' })
export class MessageService {
  private msgSource = new Subject<string>();
  message$ = this.msgSource.asObservable();
  send(msg: string) { this.msgSource.next(msg); }
}
```

### 12. What is a structural directive?
**Situation:** The template must show or hide elements based on a condition.
**Task:** Build a custom directive similar to `*ngIf`.
**Action:** Implement `Directive` with `TemplateRef` and `ViewContainerRef` to add or remove elements.
**Result:** The DOM updates dynamically depending on conditions.
```ts
@Directive({ selector: '[appUnless]' })
export class UnlessDirective {
  constructor(private tpl: TemplateRef<any>, private vcr: ViewContainerRef) {}
  @Input() set appUnless(condition: boolean) {
    condition ? this.vcr.clear() : this.vcr.createEmbeddedView(this.tpl);
  }
}
```

### 13. Explain the purpose of `ng-template`.
**Situation:** A template needs to be defined but not rendered immediately.
**Task:** Use markup as a template reference for later display.
**Action:** Wrap content in `<ng-template>` and reference it with `ViewContainerRef` or structural directives.
**Result:** Angular can instantiate or render the template at the appropriate time.

### 14. How do you perform unit testing of a component with dependencies?
**Situation:** A component relies on a service to fetch data.
**Task:** Write an isolated unit test.
**Action:** Create a testing module using `TestBed` and provide a mock service via `useValue` or `useClass`.
**Result:** The component is tested without hitting real services.
```ts
beforeEach(async () => {
  await TestBed.configureTestingModule({
    declarations: [MyComp],
    providers: [{ provide: DataService, useValue: mockService }]
  }).compileComponents();
});
```

### 15. What does `ngZone` do and when would you use it?
**Situation:** Some asynchronous work occurs outside Angular's zone, so change detection doesn't run.
**Task:** Trigger a UI update when external code finishes.
**Action:** Inject `NgZone` and run the code inside `zone.run`.
**Result:** Angular recognizes the update and refreshes the view.
```ts
constructor(private zone: NgZone) {}
someCallback() {
  this.zone.run(() => this.value = 'updated');
}
```

### 16. How do you internationalize an Angular application?
**Situation:** An app must support multiple languages.
**Task:** Provide translations for UI texts.
**Action:** Use Angular's `@angular/localize` package and i18n attributes, build separate bundles with the `--localize` option.
**Result:** Users can switch to their preferred language with translated content.

### 17. Explain the role of `ViewChild` and `ContentChild`.
**Situation:** A parent component needs to access a child component's API and projected content.
**Task:** Obtain references to elements in templates.
**Action:** Use `@ViewChild` to grab a child component instance and `@ContentChild` for projected content.
**Result:** The parent can control or read properties from children.
```ts
@ViewChild(ChildComp) child!: ChildComp;
@ContentChild('projected') tpl!: TemplateRef<any>;
```

### 18. What is an Angular schematic?
**Situation:** Teams want consistent scaffolding for new features.
**Task:** Automate project setup with custom templates.
**Action:** Create a schematic collection using the Angular DevKit to generate code.
**Result:** Developers run a command to produce standardized structures.

### 19. How does the `HttpClient` handle typed responses?
**Situation:** You expect a specific data shape from an API.
**Task:** Ensure type safety when making HTTP calls.
**Action:** Pass a generic type to `get<T>` or other HTTP methods.
**Result:** TypeScript provides compile-time checks and intellisense.
```ts
this.http.get<User[]>('/api/users').subscribe(users => this.list = users);
```

### 20. Describe the difference between `Renderer2` and direct DOM access.
**Situation:** A component manipulates the DOM for animations.
**Task:** Maintain platform independence and security.
**Action:** Inject `Renderer2` and use its methods to modify elements instead of native DOM APIs.
**Result:** Code runs on the server (Angular Universal) and other platforms without direct DOM dependency.

### 21. How do you dynamically load a component at runtime?
**Situation:** A dashboard must display widgets chosen by the user.
**Task:** Add components to the view after compilation.
**Action:** Use `ViewContainerRef.createComponent` or the `ComponentFactoryResolver` in older versions.
**Result:** Components instantiate on demand with their own injectors.
```ts
const view = this.container.createComponent(MyWidgetComponent);
view.instance.data = widgetData;
```

### 22. What is a pipe and how do you create a custom one?
**Situation:** You need to format phone numbers consistently.
**Task:** Implement a reusable transformation in templates.
**Action:** Decorate a class with `@Pipe` and implement `PipeTransform`.
**Result:** The pipe can be used as `{{ phone | formatPhone }}`.
```ts
@Pipe({ name: 'formatPhone' })
export class FormatPhonePipe implements PipeTransform {
  transform(value: string) {
    return value.replace(/(\d{3})(\d{3})(\d{4})/, '($1) $2-$3');
  }
}
```

### 23. How do you manage environment-specific variables in Angular?
**Situation:** The app should use different API endpoints for development and production.
**Task:** Configure variables per environment.
**Action:** Place settings in `environment.ts` and `environment.prod.ts`, referencing them via `environment` import.
**Result:** Builds automatically substitute the right values for each environment.

### 24. Explain how to implement server-side rendering with Angular Universal.
**Situation:** The app needs better SEO and faster first paint.
**Task:** Render pages on the server initially.
**Action:** Use Angular Universal to create a server module and configure Node for SSR.
**Result:** Users see content sooner and crawlers index pages effectively.

### 25. How does dependency injection hierarchy work in Angular?
**Situation:** A service should be singleton or scoped to a module.
**Task:** Manage service instances across modules and components.
**Action:** Provide the service at the root level for a singleton or within a lazy-loaded module for separate instances.
**Result:** Service lifetime is controlled by where it's provided.

### 26. What is `ChangeDetectorRef` used for?
**Situation:** A value updates outside Angular's change detection.
**Task:** Notify Angular that it needs to check the component.
**Action:** Inject `ChangeDetectorRef` and call `markForCheck()` or `detectChanges()`.
**Result:** The component's view refreshes with the latest data.

### 27. Explain the difference between template-driven and reactive forms.
**Situation:** Choosing a form strategy for a simple contact form.
**Task:** Compare the two approaches.
**Action:** Template-driven forms rely on directives in the template, while reactive forms define the model in code.
**Result:** Template-driven forms suit small forms; reactive forms provide more explicit control.

### 28. How do you optimize an Angular application for production?
**Situation:** Performance should be maximized before deployment.
**Task:** Enable optimizations and remove development tooling.
**Action:** Run `ng build --prod` (Angular 11) or the recommended production build command in later versions.
**Result:** Bundles are minified, Ahead-of-Time compilation is enabled, and debugging information is removed.

### 29. What are Angular animations and how do you create them?
**Situation:** You want to animate items entering and leaving a list.
**Task:** Define animations that trigger on state changes.
**Action:** Use `@angular/animations` to define `trigger`, `state`, and `transition` metadata.
**Result:** Elements smoothly animate as they are added or removed.
```ts
@Component({
  animations: [
    trigger('fade', [
      transition(':enter', [style({ opacity: 0 }), animate('300ms', style({ opacity: 1 }))]),
      transition(':leave', [animate('300ms', style({ opacity: 0 }))])
    ])
  ]
})
```

### 30. How do you create a custom Angular library?
**Situation:** You want to share components across multiple projects.
**Task:** Publish reusable code as a library package.
**Action:** Use `ng generate library` to create a library project, build it, and publish the resulting package to npm or a private registry.
**Result:** The library can be imported into other applications using standard package management.

### 31. Describe the difference between `Observable` and `Promise` in Angular.
**Situation:** A service fetches data asynchronously.
**Task:** Choose between `Observable` and `Promise`.
**Action:** `Observable` supports multiple values and cancellation, while `Promise` resolves once. Angular's `HttpClient` returns observables by default.
**Result:** Observables provide powerful operators and are used widely in Angular for event streams.

### 32. How do you detect performance issues with the Angular DevTools?
**Situation:** The application stutters during updates.
**Task:** Profile change detection and component rendering.
**Action:** Use the Angular DevTools browser extension to inspect the component tree and performance timeline.
**Result:** Bottlenecks are identified, allowing targeted optimizations.

### 33. Explain `trackBy` in `*ngFor`.
**Situation:** A list of items updates frequently.
**Task:** Avoid unnecessary DOM manipulation.
**Action:** Provide a `trackBy` function that returns a unique identifier for each item.
**Result:** Angular reuses existing DOM elements, improving rendering performance.
```html
<li *ngFor="let item of items; trackBy: trackById">{{ item.name }}</li>
```

### 34. How do you provide configuration data to a service using `InjectionToken`?
**Situation:** A service needs API URLs defined externally.
**Task:** Inject configuration values at runtime.
**Action:** Create an `InjectionToken` and provide a value in a module or environment file.
**Result:** Services depend on typed configuration rather than hardcoded strings.
```ts
export const API_CONFIG = new InjectionToken<ApiConfig>('api.config');
providers: [{ provide: API_CONFIG, useValue: { endpoint: '/api' } }]
```

### 35. What is the purpose of `Resolver` in Angular routing?
**Situation:** Some routes require data before activation.
**Task:** Fetch data ahead of route initialization.
**Action:** Implement `Resolve<T>` to load data, returning an observable that the router waits for.
**Result:** Components receive prepared data when they are created.
```ts
@Injectable({ providedIn: 'root' })
export class UserResolver implements Resolve<User> {
  constructor(private svc: UserService) {}
  resolve(route: ActivatedRouteSnapshot) {
    return this.svc.getUser(route.paramMap.get('id')!);
  }
}
```

### 36. Explain differential loading in Angular.
**Situation:** You want modern browsers to load smaller bundles while maintaining compatibility with older browsers.
**Task:** Generate separate bundles for legacy and modern JS.
**Action:** Enable differential loading (default in Angular 8+). Angular builds ES5 bundles for older browsers and ES2015+ for modern ones.
**Result:** Modern browsers download smaller, faster code while older browsers remain supported.

### 37. How do you use `HttpParams` in a GET request?
**Situation:** A request must include query parameters.
**Task:** Build the query string in a type-safe way.
**Action:** Create `HttpParams` and append key-value pairs, passing it to the `get` method.
**Result:** The URL is constructed correctly with encoded parameters.
```ts
const params = new HttpParams().set('page', '1').set('size', '10');
this.http.get<Data[]>('/api/data', { params });
```

### 38. How does Angular handle errors in observables?
**Situation:** HTTP requests might fail and should not crash the app.
**Task:** Gracefully handle errors and provide feedback.
**Action:** Use the `catchError` operator in RxJS pipelines and return a fallback value or throw a custom error.
**Result:** The application remains stable and can display meaningful error messages.
```ts
this.http.get('/api').pipe(
  catchError(err => {
    console.error(err);
    return of([]);
  })
);
```

### 39. What are `Signals` (Angular 17) and why use them?
**Situation:** You want fine-grained reactivity with minimal overhead.
**Task:** Respond to reactive state changes without full component detection.
**Action:** Create `Signal` objects using `signal()` and compute derived signals.
**Result:** Components update precisely when their dependencies change.
```ts
const counter = signal(0);
const double = computed(() => counter() * 2);
```

### 40. Describe the role of `APP_INITIALIZER`.
**Situation:** Configuration data must load before the app starts.
**Task:** Execute a function during app initialization.
**Action:** Provide an `APP_INITIALIZER` that returns a promise; Angular waits for it to resolve.
**Result:** The app only bootstraps after the initialization logic completes.
```ts
providers: [{
  provide: APP_INITIALIZER,
  useFactory: (svc: ConfigService) => () => svc.load(),
  deps: [ConfigService],
  multi: true
}]
```

### 41. How do you use Web Workers in Angular?
**Situation:** CPU-intensive tasks block the UI.
**Task:** Run heavy computation off the main thread.
**Action:** Use Angular CLI's `ng generate web-worker` command, then communicate with `postMessage` and `onmessage`.
**Result:** The UI remains responsive during computations.

### 42. Explain how to implement drag and drop with Angular CDK.
**Situation:** A list needs reordering via drag-and-drop.
**Task:** Provide user-friendly drag interactions.
**Action:** Import `DragDropModule` from `@angular/cdk/drag-drop` and apply `cdkDropList` and `cdkDrag` directives.
**Result:** Items can be rearranged visually with minimal code.
```html
<div cdkDropList (cdkDropListDropped)="drop($event)">
  <div *ngFor="let item of items" cdkDrag>{{ item }}</div>
</div>
```

### 43. What is the purpose of the `Injector` class?
**Situation:** You need to dynamically create a component with dependencies.
**Task:** Provide custom tokens when instantiating the component.
**Action:** Use `Injector.create` to create a new injector with providers.
**Result:** The dynamic component receives the provided dependencies.

### 44. How can you schedule a recurring task with RxJS?
**Situation:** The app must poll a server every minute.
**Task:** Emit requests on an interval.
**Action:** Use `interval` and `switchMap` to call the service repeatedly.
**Result:** The observable polls the server while subscriptions are active.
```ts
interval(60000).pipe(switchMap(() => this.http.get('/api'))).subscribe();
```

### 45. Describe the use of `@HostListener` and `@HostBinding`.
**Situation:** You need to react to host element events and bind properties.
**Task:** Handle DOM events and classes from within a directive.
**Action:** Decorate methods with `@HostListener` and fields with `@HostBinding` to listen and bind values on the host.
**Result:** Directives respond to host events and update host styles or attributes.
```ts
@Directive({ selector: '[appHighlight]' })
export class HighlightDirective {
  @HostBinding('style.background') bg = '';
  @HostListener('mouseenter') onEnter() { this.bg = 'yellow'; }
  @HostListener('mouseleave') onLeave() { this.bg = ''; }
}
```

### 46. How do you create unit tests for an Angular pipe?
**Situation:** A custom pipe transforms text.
**Task:** Verify its output for different inputs.
**Action:** Instantiate the pipe directly and call its `transform` method in a Jasmine test.
**Result:** The pipe's logic is validated without running a full Angular testbed.
```ts
it('transforms to uppercase', () => {
  const pipe = new FormatPipe();
  expect(pipe.transform('a')).toBe('A');
});
```

### 47. Explain the `HttpClientTestingModule`.
**Situation:** You want to test services that perform HTTP requests without hitting the network.
**Task:** Mock and verify outgoing requests.
**Action:** Import `HttpClientTestingModule` and use `HttpTestingController` to expect and flush requests.
**Result:** Tests simulate server responses in isolation.
```ts
let ctrl: HttpTestingController;
beforeEach(() => {
  TestBed.configureTestingModule({
    imports: [HttpClientTestingModule]
  });
  ctrl = TestBed.inject(HttpTestingController);
});
```

### 48. How do you create a responsive design with Angular Flex Layout?
**Situation:** The app must adjust its layout across devices.
**Task:** Use declarative CSS utilities.
**Action:** Install `@angular/flex-layout` and apply directives like `fxLayout` and `fxFlex` in templates.
**Result:** Components flexibly align and resize based on viewport size.

### 49. What is the role of `RouterOutlet`?
**Situation:** You need to display different components based on the route.
**Task:** Provide a placeholder for routed components.
**Action:** Include `<router-outlet></router-outlet>` in your template.
**Result:** Angular displays the component associated with the active route.

### 50. Explain how to debug change detection with `ChangeDetectionStrategy`.
**Situation:** You're unsure why a component rerenders.
**Task:** Track when change detection runs.
**Action:** Switch to `ChangeDetectionStrategy.OnPush` and log inside `ngDoCheck` or `ngOnChanges`.
**Result:** You can see exactly when Angular checks for updates, aiding performance tuning.


