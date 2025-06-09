# Simple script to generate a PDF with Angular interview questions.
import textwrap

# We'll define questions in STAR format
qa_pairs = [
    (
        "How do you define a component in Angular?",
        "Situation: Building a UI element as a reusable component.\n"
        "Task: Create a new component with a template and logic.\n"
        "Action: Use the CLI command `ng generate component my-comp` to scaffold. Then implement the selector, template, and logic in TypeScript.\n"
        "Result: The component can be reused across the application and referenced in templates via `<app-my-comp></app-my-comp>`."
    ),
    (
        "Explain dependency injection in Angular.",
        "Situation: Components require services for data or logic.\n"
        "Task: Provide those services in a decoupled manner.\n"
        "Action: Declare services with `@Injectable` and register them in a module or component provider array. Angular's injector supplies instances where needed.\n"
        "Result: This encourages modular design and simplifies testing by allowing service mocking."
    ),
    (
        "Describe Observables vs Promises.",
        "Situation: Handling asynchronous operations like HTTP requests.\n"
        "Task: Decide whether to use Observables or Promises.\n"
        "Action: Observables from RxJS support streams with multiple values and operators, e.g. `http.get('/api').subscribe(...)`; Promises represent a single future value, e.g. `fetch('/api').then(...)`.\n"
        "Result: Observables provide more flexibility for complex async flows, while Promises are simpler for one-off operations."
    ),
    (
        "How does Angular change detection work?",
        "Situation: Updates to component state must reflect in the view.\n"
        "Task: Trigger view updates efficiently.\n"
        "Action: Angular's change detector checks component data bindings when triggered by events or `markForCheck`. `OnPush` strategy can limit checks to input changes.\n"
        "Result: The view stays in sync with data while minimizing unnecessary DOM operations."
    ),
    (
        "What are reactive forms?",
        "Situation: Building forms that scale with dynamic validation.\n"
        "Task: Manage complex form state in code.\n"
        "Action: Use `FormGroup`, `FormControl`, and `FormBuilder` to construct the form model in TypeScript, subscribing to value and status changes.\n"
        "Result: Reactive forms provide explicit control, easier unit testing, and dynamic validation rules."
    ),
    (
        "Give an example of a structural directive.",
        "Situation: Conditionally displaying or repeating elements.\n"
        "Task: Use Angular syntax to manipulate DOM layout.\n"
        "Action: Apply `*ngIf` to show/hide markup, or `*ngFor` to iterate over a list. Example: `<div *ngIf=\"loggedIn\">Welcome</div>`.\n"
        "Result: Structural directives change the DOM tree based on application state."
    ),
    (
        "Explain pipes in Angular.",
        "Situation: Formatting data for display.\n"
        "Task: Transform values in templates.\n"
        "Action: Use built-in pipes like `date` or create custom pipes implementing `transform`. Example: `{{ birthday | date:'longDate' }}`.\n"
        "Result: Data appears formatted without altering the underlying model."
    ),
    (
        "How do you handle HTTP errors?",
        "Situation: API calls may fail due to network or server issues.\n"
        "Task: Gracefully respond to errors.\n"
        "Action: Use HttpClient with `catchError` from RxJS to intercept errors and return a user-friendly message or fallback value.\n"
        "Result: The application remains robust and provides feedback to the user."
    ),
    (
        "What is lazy loading of modules?",
        "Situation: Large apps need faster initial load time.\n"
        "Task: Load feature modules only when required.\n"
        "Action: Configure the router with `loadChildren` to import modules asynchronously, e.g. `{ path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) }`.\n"
        "Result: Only necessary code is downloaded, improving startup performance."
    ),
    (
        "Describe a guard in routing.",
        "Situation: Restricting access to certain routes.\n"
        "Task: Check conditions before navigation.\n"
        "Action: Implement `CanActivate` or other guard interfaces and return true/false or a UrlTree. Example guard: `canActivate(route, state) { return this.auth.isLoggedIn(); }`\n"
        "Result: Users are redirected if they don't meet requirements, enhancing security."
    ),
    (
        "How do you share data between components?",
        "Situation: Sibling components must communicate.\n"
        "Task: Pass data without tight coupling.\n"
        "Action: Use a shared service with RxJS `Subject`, or pass inputs/outputs via parent components. Example: `this.messageService.send(msg)`.\n"
        "Result: Components remain modular while still exchanging information."
    ),
    (
        "What is the purpose of NgZone?",
        "Situation: Some asynchronous operations happen outside Angular's zone.\n"
        "Task: Ensure change detection runs after those operations.\n"
        "Action: Inject `NgZone` and run code inside `ngZone.run(() => { ... })` so Angular notices changes.\n"
        "Result: UI updates correctly even when external APIs trigger callbacks."
    ),
    (
        "Explain unit testing a component with TestBed.",
        "Situation: A component has logic that should be verified automatically.\n"
        "Task: Write a unit test using Angular's utilities.\n"
        "Action: Configure TestBed with the component and dependencies, create the component via `TestBed.createComponent`, and assert on the component's behavior.\n"
        "Result: Regressions are caught early, increasing confidence in code changes."
    ),
    (
        "Give an example of a custom validator.",
        "Situation: Need to enforce domain-specific input rules.\n"
        "Task: Create a reusable validator for reactive forms.\n"
        "Action: Define a function that returns a validation error object when rules fail. Example: `const noSpecialChars: ValidatorFn = control => /[^a-zA-Z0-9]/.test(control.value) ? { special: true } : null;`\n"
        "Result: Form controls using this validator automatically display errors when invalid."
    ),
    (
        "How do you configure global error handling?",
        "Situation: Unhandled exceptions should be logged centrally.\n"
        "Task: Provide an implementation of `ErrorHandler`.\n"
        "Action: Create a class extending `ErrorHandler` and override `handleError` to send errors to an API or logging service. Provide it in the root module.\n"
        "Result: Errors are captured and reported consistently across the app."
    ),
    (
        "What is ahead-of-time (AOT) compilation?",
        "Situation: Angular templates are normally compiled at runtime.\n"
        "Task: Improve startup performance and detect template errors earlier.\n"
        "Action: Use the Angular CLI with the `--aot` flag or enable AOT in build options. This compiles templates during the build step.\n"
        "Result: Faster rendering in the browser and earlier detection of template issues."
    )
]

# Convert questions to lines with simple wrapping
lines = []
for idx, (q, a) in enumerate(qa_pairs, 1):
    lines.append(f"{idx}. {q}")
    for part in textwrap.wrap(a, 90):
        lines.append(part)
    lines.append("")

page_width = 612
page_height = 792
margin = 50
line_height = 14

pages = []
current_page = []
y_position = page_height - margin
for line in lines:
    if y_position - line_height < margin:
        pages.append(current_page)
        current_page = []
        y_position = page_height - margin
    current_page.append((margin, y_position, line))
    y_position -= line_height
if current_page:
    pages.append(current_page)

objects = []

# Catalog and pages will reference later
objects.append("<< /Type /Catalog /Pages 2 0 R >>")

# Pages object with kids to be filled
kids = []
page_objs = []
for i, page in enumerate(pages):
    kids.append(f"{len(objects) + 2*i + 3} 0 R")
    page_objs.append(page)
# Build pages object after we know kids
# We'll fill later after loops

# Font object
font_obj_index = len(objects) + len(page_objs)*2 + 2

# Build page content objects
content_refs = []
for page in page_objs:
    contents_lines = ["BT", "/F1 12 Tf"]
    for x, y, text in page:
        text = text.replace("(", "\\(").replace(")", "\\)")
        contents_lines.append(f"{x} {y} Td ({text}) Tj")
    contents_lines.append("ET")
    content_stream = "\n".join(contents_lines)
    objects.append(f"<< /Length {len(content_stream)} >>\nstream\n{content_stream}\nendstream")
    content_refs.append(f"{len(objects)} 0 R")

# Now create page objects referencing content
page_indices = []
for content_ref in content_refs:
    page_obj = f"<< /Type /Page /Parent 2 0 R /MediaBox [0 0 {page_width} {page_height}] /Contents {content_ref} /Resources << /Font << /F1 {font_obj_index} 0 R >> >> >>"
    objects.append(page_obj)
    page_indices.append(f"{len(objects)} 0 R")

# Now pages object
pages_obj = f"<< /Type /Pages /Kids [{' '.join(page_indices)}] /Count {len(page_indices)} >>"
objects.insert(1, pages_obj)

# Font object at the end
objects.append("<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica >>")

# Build PDF file with xref
offsets = []
result = ["%PDF-1.4"]
for obj_id, obj in enumerate(objects, 1):
    offsets.append(sum(len(l)+1 for l in result))
    result.append(f"{obj_id} 0 obj\n{obj}\nendobj")

xref_offset = sum(len(l)+1 for l in result)
result.append("xref")
result.append(f"0 {len(objects)+1}")
result.append("0000000000 65535 f ")
for off in offsets:
    result.append(f"{off:010d} 00000 n ")
result.append("trailer")
result.append(f"<< /Size {len(objects)+1} /Root 1 0 R >>")
result.append("startxref")
result.append(str(xref_offset))
result.append("%%EOF")

with open("AngularInterviewQuestions.pdf", "wb") as f:
    f.write("\n".join(result).encode("latin1"))
