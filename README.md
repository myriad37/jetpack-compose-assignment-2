bjective
Create an Android application that fetches a list of TODO items from the JSONPlaceholder API. The app should display the list of todos in a scrollable Compose UI. When a user selects a todo item, the app navigates to a detail screen that shows all the item information. In the background, the app will store the fetched data using a Room database so that the data persists for offline use and faster loading on subsequent launches.
Functional Requirements
Data Retrieval & Caching
Remote API: Use Retrofit to make network calls to the JSONPlaceholder API (endpoint:  https://jsonplaceholder.typicode.com/todos).
Local Caching: Implement a Room database to cache the results. When the app launches, load data from the cache first and refresh from the network if possible.
User Interface
List Screen:
Display a list of TODO items (e.g., title and completion status).
Implement efficient scrolling using Jetpack Compose LazyColumn or similar component.
The UI should clearly indicate the loading state and provide error messages when the network request fails.
Detail Screen:
When a user taps on a list item, navigate to a detail screen showing full details of the selected todo (e.g., title, description, status, and any other relevant fields returned by the API).
Include a simple back navigation to return to the list screen.
Navigation
Use Jetpack Compose Navigation components to transition between the list and detail screens gracefully.
Error Handling
Include error handling for network failures, such as displaying an error message or a retry option.
If the cached data is available (data present in Room), display it even when the network call fails.
Data Synchronization
On app start, show cached data immediately (if available) and update the UI when fresh data is loaded.
Handle data consistency between the remote server and the local database.
Technical Requirements
Tech Stack
UI Layer: Jetpack Compose
Networking: Retrofit (using Kotlin coroutines for asynchronous operations)
Persistence: Room Database
Architecture: MVVM (Model-View-ViewModel) is highly encouraged for separating concerns.
Project Setup
Create a new Android project in Android Studio.
Add dependencies for Jetpack Compose, Retrofit, Room, and any lifecycle components required.
Use Kotlin throughout the project.
Implementation Details
Data Model: Define Kotlin data classes that map to the JSON structure of the TODO items.
Retrofit Interface: Create an API interface in Retrofit to define a method for fetching the list of todos.
Room Entities & DAO:
Define an entity class for the TODO item.
Create a DAO (Data Access Object) that provides methods to insert, fetch, and update todo items.
Repository Pattern: Implement a repository that abstracts data sources (network and database) such that the ViewModel interacts with this repository.
ViewModels:
Develop a ViewModel for the list screen that exposes LiveData or State objects for the UI to observe.
Develop a second ViewModel (if needed) for the detail screen.
UI Composition:
Use LazyColumn for the list screen.
Use Compose layout components (Column, Row, Card, etc.) for designing individual list items and the detail view.
Navigation:
Implement Compose Navigation to control screen transitions.
Pass the selected todo’s ID (or entire object if using serialization) from the list screen to the detail screen.
