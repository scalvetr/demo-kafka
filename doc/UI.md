# UI

# Build the UI project

```shell script
ng new ui && cd ui
ng generate component chat
ng generate component login

mkdir src/app/shared/
cat > src/app/shared/message.model.ts << EOF
export class Message {
  constructor(public origin: string, public title: string, public message: string) {}
}
EOF

```

edit: `src/app/app-routing.module.ts`

```typescript
const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: '', component: ChatComponent }
];
```

