# Unicorn Android - Engineering Roadmap

---

## Epic 1: Modern Build & Toolchain Upgrade

Outdated dependencies signal neglect. A senior engineer keeps the toolchain current and leverages convention plugins for consistency.

### Story 1.1: Migrate to Gradle Version Catalog & Convention Plugins
- [ ] **Task:** Create `gradle/libs.versions.toml` with all dependency versions centralized
- [ ] **Task:** Replace hardcoded dependency strings in `build.gradle` with catalog references
- [ ] **Task:** Migrate from Groovy (`build.gradle`) to Kotlin DSL (`build.gradle.kts`)
- [ ] **Task:** Create a convention plugin for shared Android config (compileSdk, minSdk, JVM target, compose compiler)

### Story 1.2: Upgrade All Dependencies to Latest Stable
- [ ] **Task:** Upgrade AGP to 8.7.x+, Kotlin to 2.0.x+, Compose BOM to 2024.12+
- [ ] **Task:** Migrate to Compose Compiler Gradle Plugin (replaces `kotlinCompilerExtensionVersion`)
- [ ] **Task:** Upgrade Navigation Compose, Lifecycle, Activity Compose to latest stable
- [ ] **Task:** Replace OpenCSV with kotlinx-serialization for data handling
- [ ] **Task:** Add Dependabot or Renovate config for automated dependency updates

### Story 1.3: Enable Static Analysis & Code Quality Gates
- [ ] **Task:** Add Detekt with a custom rule set tuned for Compose best practices
- [ ] **Task:** Add ktlint or Spotless for consistent code formatting
- [ ] **Task:** Configure Android Lint with `warningsAsErrors = true` and fix all warnings
- [ ] **Task:** Add a pre-commit Git hook that runs lint + format checks
- [ ] **Task:** Add a CI pipeline (GitHub Actions) that runs lint, tests, and builds on every PR

---

## Epic 2: Architecture & Clean Code Overhaul

The current code mixes concerns and lacks clear layering. 

We need clean architecture boundaries that make the codebase navigable and testable.

### Story 2.1: Establish Multi-Module Project Structure
- [ ] **Task:** Create `:core:model` module for domain models (`Expense`, `Transaction`, etc.)
- [ ] **Task:** Create `:core:data` module for repositories and data sources
- [ ] **Task:** Create `:core:domain` module for use cases (business logic)
- [ ] **Task:** Create `:core:ui` module for shared Compose components and theming
- [ ] **Task:** Create `:feature:home`, `:feature:expenses`, `:feature:auth` feature modules
- [ ] **Task:** Keep `:app` module as a thin shell that wires feature modules together

### Story 2.2: Implement Dependency Injection with Hilt
- [ ] **Task:** Apply `com.google.dagger.hilt.android` plugin to app module
- [ ] **Task:** Annotate `Application` class with `@HiltAndroidApp`
- [ ] **Task:** Create Hilt modules for data layer (database, network, repositories)
- [ ] **Task:** Inject ViewModels using `@HiltViewModel` instead of manual construction
- [ ] **Task:** Use `hiltViewModel()` in Compose screens for ViewModel scoping

### Story 2.3: Implement Repository Pattern with Offline-First Data Layer
- [ ] **Task:** Add Room database with `ExpenseDao`, `TransactionDao`
- [ ] **Task:** Define `ExpenseRepository` interface in `:core:domain`
- [ ] **Task:** Implement `OfflineFirstExpenseRepository` in `:core:data` backed by Room
- [ ] **Task:** Remove hardcoded sample data from `ExpensesViewModel`
- [ ] **Task:** Expose repository data as `Flow<List<Expense>>` for reactive UI updates

### Story 2.4: Clean Up Existing Code Smells
- [ ] **Task:** Remove all `TODO-FIXME-CLEANUP` comments and dead code
- [ ] **Task:** Separate `ScreenDrawer` from `Screen` into independent sealed hierarchies
- [ ] **Task:** Remove dual Material/Material3 imports; standardize on Material3 only
- [ ] **Task:** Fix `ExpensesViewModel` exposing `_expenses` MutableStateFlow publicly
- [ ] **Task:** Replace magic strings (route names) with constants or typed route objects
- [ ] **Task:** Introduce `UiState<T>` sealed class (Loading, Success, Error) for screen states

---

## Epic 3: Authentication & User Management

The current login screen is a dead end. A senior engineer implements a real auth flow with proper security.

### Story 3.1: Implement Firebase Authentication
- [ ] **Task:** Integrate Firebase Auth SDK and configure `google-services.json`
- [ ] **Task:** Implement email/password sign-up and login
- [ ] **Task:** Implement Google Sign-In using Credential Manager API
- [ ] **Task:** Store auth tokens securely using EncryptedSharedPreferences
- [ ] **Task:** Create `AuthRepository` that exposes `Flow<AuthState>` (Authenticated, Unauthenticated, Loading)

### Story 3.2: Auth-Gated Navigation
- [ ] **Task:** Create `AuthViewModel` that observes `AuthState` from repository
- [ ] **Task:** Implement conditional nav graph: unauthenticated users see Landing/Login, authenticated users go to Main
- [ ] **Task:** Add a sign-out option in the navigation drawer
- [ ] **Task:** Handle session expiry gracefully with re-authentication prompt

### Story 3.3: User Profile Screen
- [ ] **Task:** Create `ProfileScreen` with avatar, display name, email
- [ ] **Task:** Allow profile photo upload (camera/gallery) with Coil image loading
- [ ] **Task:** Add profile editing (display name, preferences)
- [ ] **Task:** Add profile menu item to navigation drawer

---

## Epic 4: Expense Tracking - Core Feature Build-Out

The app's raison d'etre is expense tracking. A senior engineer delivers a complete, polished feature with real data flows.

### Story 4.1: Expense List Screen
- [ ] **Task:** Build `ExpensesScreen` with a `LazyColumn` of expense cards
- [ ] **Task:** Each card shows: category icon, title, formatted amount, date, category chip
- [ ] **Task:** Add pull-to-refresh using `PullToRefreshBox`
- [ ] **Task:** Show empty state illustration when no expenses exist
- [ ] **Task:** Add FAB to navigate to Add Expense screen

### Story 4.2: Add/Edit Expense Flow
- [ ] **Task:** Create `AddExpenseScreen` with form: title, amount, date picker, category dropdown
- [ ] **Task:** Implement form validation (non-empty title, positive amount, valid date)
- [ ] **Task:** Use `DatePickerDialog` from Material3 for date selection
- [ ] **Task:** Support editing existing expenses (pre-fill form, update on save)
- [ ] **Task:** Add swipe-to-delete on expense list items with undo Snackbar

### Story 4.3: Expense Categories & Filtering
- [ ] **Task:** Define an `ExpenseCategory` enum with icon, color, and display name
- [ ] **Task:** Add filter chips row above expense list to filter by category
- [ ] **Task:** Add date range filter (This Week, This Month, Custom Range)
- [ ] **Task:** Add search bar for text-based expense filtering
- [ ] **Task:** Persist selected filters across navigation using SavedStateHandle

### Story 4.4: Expense Dashboard & Analytics
- [ ] **Task:** Add a summary card at top of HomeScreen: total spent this month, budget remaining
- [ ] **Task:** Integrate Vico (or equivalent) charting library for spend-by-category pie chart
- [ ] **Task:** Add a monthly spend trend bar chart on the dashboard
- [ ] **Task:** Create `DashboardViewModel` with aggregation logic and `Flow<DashboardUiState>`

---

## Epic 5: UI/UX Overhaul - Design System & Polish

Every screen currently looks like a placeholder. A senior engineer builds a cohesive design system and delivers delightful interactions.

### Story 5.1: Establish a Design System
- [ ] **Task:** Define a complete `Color.kt` with semantic tokens: `surface`, `onSurface`, `primary`, `onPrimary`, `error`, `success`, etc. for both light and dark themes
- [ ] **Task:** Define a full `Type.kt` typography scale: `displayLarge` through `labelSmall` using a custom font family (e.g., Inter or Plus Jakarta Sans via Google Fonts)
- [ ] **Task:** Define a `Dimens.kt` spacing scale (4dp, 8dp, 12dp, 16dp, 24dp, 32dp, 48dp)
- [ ] **Task:** Define `Shape.kt` with consistent corner radii (small=8dp, medium=12dp, large=16dp)
- [ ] **Task:** Create reusable Compose components: `UnicornButton`, `UnicornCard`, `UnicornTextField`, `UnicornTopBar`

### Story 5.2: Dark Theme & Dynamic Color
- [ ] **Task:** Implement full dark theme color scheme with proper contrast ratios (WCAG AA)
- [ ] **Task:** Support Android 12+ dynamic color (Material You) with graceful fallback
- [ ] **Task:** Add theme toggle in settings (System Default, Light, Dark)
- [ ] **Task:** Persist theme preference using DataStore

### Story 5.3: Redesign Landing & Login Screens
- [ ] **Task:** Replace gradient background with an illustrated hero image or Lottie animation
- [ ] **Task:** Add proper text hierarchy: headline, subheading, body text
- [ ] **Task:** Replace plain text buttons with styled `UnicornButton` (filled primary, outlined secondary)
- [ ] **Task:** Add onboarding carousel (3 slides) using `HorizontalPager` before landing
- [ ] **Task:** LoginScreen: add real text fields with validation states, password visibility toggle, loading indicator

### Story 5.4: Redesign Navigation & App Shell
- [ ] **Task:** Replace ModalNavigationDrawer with `NavigationBar` (bottom nav) for primary destinations
- [ ] **Task:** Add 4 bottom nav items: Home, Expenses, Analytics, Profile with icons and labels
- [ ] **Task:** Keep drawer for secondary items (Settings, Help, Sign Out)
- [ ] **Task:** Replace `CustomAppBar` with Material3 `TopAppBar` with proper elevation/scroll behavior
- [ ] **Task:** Implement `CenterAlignedTopAppBar` with app logo on home, back arrow on detail screens
- [ ] **Task:** Add smooth shared element transitions between screens (Compose Animation APIs)

### Story 5.5: Micro-Interactions & Motion Design
- [ ] **Task:** Add `AnimatedContent`/`Crossfade` transitions when switching bottom nav tabs
- [ ] **Task:** Add staggered entry animation for list items in ExpensesScreen
- [ ] **Task:** Add haptic feedback on FAB press, swipe actions, and successful operations
- [ ] **Task:** Add shimmer loading placeholder (using Compose shimmer library) for async content
- [ ] **Task:** Add Lottie animation for empty states and success confirmations

### Story 5.6: Accessibility & Inclusive Design
- [ ] **Task:** Add `contentDescription` to all icons and images
- [ ] **Task:** Ensure all touch targets are minimum 48dp
- [ ] **Task:** Support Dynamic Type / font scaling up to 200% without layout breaking
- [ ] **Task:** Test and fix screen reader (TalkBack) navigation order on all screens
- [ ] **Task:** Add sufficient color contrast for all text and interactive elements
- [ ] **Task:** Support landscape orientation and tablet layouts using `WindowSizeClass`

---

## Epic 6: Networking & Cloud Sync

Local-only data is fragile. A senior engineer builds a sync-capable data layer with proper error handling.

### Story 6.1: REST API Integration with Ktor Client
- [ ] **Task:** Add Ktor Client with kotlinx-serialization converter
- [ ] **Task:** Create `ExpenseApiService` interface with CRUD endpoints
- [ ] **Task:** Implement `RemoteExpenseDataSource` in `:core:data`
- [ ] **Task:** Add OkHttp logging interceptor for debug builds only
- [ ] **Task:** Add auth token interceptor that attaches Bearer token to requests

### Story 6.2: Offline-First Sync Strategy
- [ ] **Task:** Implement `SyncManager` using WorkManager for background sync
- [ ] **Task:** Add `SyncStatus` entity to Room to track pending local changes
- [ ] **Task:** Implement conflict resolution strategy (last-write-wins or prompt user)
- [ ] **Task:** Show sync status indicator in the UI (synced, pending, error)
- [ ] **Task:** Handle network errors gracefully with retry and exponential backoff

### Story 6.3: Robust Error Handling
- [ ] **Task:** Define a `Result<T>` wrapper (or use kotlin.Result) for all data operations
- [ ] **Task:** Create a global `SnackbarHostState` for consistent error display
- [ ] **Task:** Map API errors to user-friendly messages (no raw HTTP codes shown)
- [ ] **Task:** Add connectivity observer using `ConnectivityManager` callback
- [ ] **Task:** Show offline banner when network is unavailable

---

## Epic 7: Testing & Quality Assurance

Zero meaningful tests is a red flag. A senior engineer builds a test pyramid that instills confidence in every change.

### Story 7.1: Unit Test Foundation
- [ ] **Task:** Add MockK and Turbine to test dependencies
- [ ] **Task:** Write unit tests for `ExpensesViewModel` (loading, error, filtering states)
- [ ] **Task:** Write unit tests for `DashboardViewModel` (aggregation, date ranges)
- [ ] **Task:** Write unit tests for all repository implementations with fake data sources
- [ ] **Task:** Write unit tests for `filterLargeTransactions()` and any domain logic
- [ ] **Task:** Achieve 80%+ coverage on `:core:domain` and ViewModels

### Story 7.2: Compose UI Tests
- [ ] **Task:** Add `compose-ui-test-junit4` to androidTest dependencies
- [ ] **Task:** Write UI tests for `ExpensesScreen` (list rendering, empty state, click actions)
- [ ] **Task:** Write UI tests for `AddExpenseScreen` (form validation, submit)
- [ ] **Task:** Write UI tests for navigation flow (Landing -> Login -> Main -> Expenses)
- [ ] **Task:** Write UI tests for bottom navigation tab switching

### Story 7.3: Screenshot / Snapshot Testing
- [ ] **Task:** Integrate Roborazzi or Paparazzi for Compose screenshot tests
- [ ] **Task:** Add screenshot tests for all screens in light and dark theme
- [ ] **Task:** Add screenshot tests for key components at different font scales
- [ ] **Task:** Add golden image CI check that fails PR on visual regressions

### Story 7.4: Integration & End-to-End Tests
- [ ] **Task:** Write integration test for full expense CRUD flow (Room + Repository)
- [ ] **Task:** Write E2E test for onboarding -> login -> add expense -> verify in list
- [ ] **Task:** Add test fixtures and fake modules for hermetic testing

---

## Epic 8: Performance & Production Readiness

A senior engineer ships apps that are fast, small, and crash-resilient.

### Story 8.1: Performance Optimization
- [ ] **Task:** Enable R8 full mode with optimized ProGuard rules for release builds
- [ ] **Task:** Profile Compose recompositions using Layout Inspector; fix unnecessary recompositions with `remember`, `derivedStateOf`, `Immutable`/`Stable` annotations
- [ ] **Task:** Add `baseline-profiles` for optimized startup and scroll performance
- [ ] **Task:** Lazy-load images with Coil and implement memory/disk caching
- [ ] **Task:** Use `kotlinx.collections.immutable` for list states to prevent recomposition

### Story 8.2: App Startup & Splash
- [ ] **Task:** Replace 5-second artificial delay with real initialization work (DB, DI, auth check)
- [ ] **Task:** Use `SplashScreen` API correctly with `setKeepOnScreenCondition` tied to actual loading
- [ ] **Task:** Target cold start under 1 second with Baseline Profiles + App Startup library
- [ ] **Task:** Remove the `UnicornViewModel` splash delay entirely

### Story 8.3: Crash Reporting & Observability
- [ ] **Task:** Integrate Firebase Crashlytics for production crash reporting
- [ ] **Task:** Add Timber for structured logging (debug-only tree, crash-reporting tree)
- [ ] **Task:** Add Firebase Analytics (or equivalent) for key user events
- [ ] **Task:** Create a `CrashReportingTree` that sends Timber logs to Crashlytics

### Story 8.4: Release Engineering
- [ ] **Task:** Configure signing configs for debug and release builds
- [ ] **Task:** Set up product flavors (dev, staging, prod) with different API endpoints
- [ ] **Task:** Enable app bundle (`.aab`) for Play Store distribution
- [ ] **Task:** Add version name/code automation based on git tags
- [ ] **Task:** Add a Fastlane or Gradle Play Publisher config for automated releases

---

## Epic 9: Settings, Preferences & Polish Features

These features round out the app and demonstrate attention to detail.

### Story 9.1: Settings Screen
- [ ] **Task:** Create `SettingsScreen` with grouped preference items
- [ ] **Task:** Add theme preference (System / Light / Dark)
- [ ] **Task:** Add currency selection preference
- [ ] **Task:** Add notification preferences (daily reminder, weekly summary)
- [ ] **Task:** Persist all settings using Jetpack DataStore (Preferences)

### Story 9.2: Notifications
- [ ] **Task:** Implement daily expense reminder using `AlarmManager` + `NotificationCompat`
- [ ] **Task:** Create notification channel for expense reminders
- [ ] **Task:** Handle notification permission request (Android 13+)
- [ ] **Task:** Deep link from notification tap into Add Expense screen

### Story 9.3: Home Screen Widget
- [ ] **Task:** Create a Glance (Jetpack Compose) home screen widget
- [ ] **Task:** Widget shows: monthly total, last 3 expenses, quick-add button
- [ ] **Task:** Update widget data on expense changes using WorkManager

---

## Priority & Phasing

| Phase | Epics | Rationale |
|-------|-------|-----------|
| **Phase 1** | Epic 1 (Build), Epic 2 (Architecture) | Foundation must be solid before features |
| **Phase 2** | Epic 5 (UI/UX), Epic 4 (Expenses) | Visible impact; core feature completeness |
| **Phase 3** | Epic 3 (Auth), Epic 7 (Testing) | Security + quality gates before going public |
| **Phase 4** | Epic 6 (Networking), Epic 8 (Performance) | Production hardening |
| **Phase 5** | Epic 9 (Polish) | Differentiating nice-to-haves |
