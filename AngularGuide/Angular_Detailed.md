# Detailed Angular Interview Questions and Answers

These questions provide expanded explanations with brief code snippets.

## 1. What is change detection and how can you optimize it?
Angular's change detection checks component templates for updates. You can optimize it by using `OnPush` strategy, which triggers checks only when input references change or events occur.

```ts
@Component({
  selector: 'app-sample',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: '{{value}}'
})
export class SampleComponent {
  @Input() value!: string;
}
```

## 2. How do you create a dynamic form using `FormBuilder`?
Use `FormBuilder` to programmatically create form controls and validators. Dynamic forms are useful for reactive patterns.

```ts
constructor(private fb: FormBuilder) {}

form = this.fb.group({
  name: ['', Validators.required],
  age: [0, Validators.min(0)]
});
```

## 3. Explain Dependency Injection tokens.
Tokens identify dependencies in Angular's DI system. You can create custom tokens using `InjectionToken` when injecting primitives or multiple implementations.
