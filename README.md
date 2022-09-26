## ⚡ Setup
After cloning this project, add your API key to local.properties under the variable `rijks_api_key`. Make sure it's in quotation marks. E.g.
`rijks_api_key="xxxxxx"`.

Api key is retrievable from [the rijksmuseum website](https://data.rijksmuseum.nl/object-metadata/api/)

## ℹ️ Info

### Functionality
The app makes use of the API by allowing users to query authors and art pieces. A result can be saved to local database by favoriting it. Favoriting multiple art pieces from the same authors can be seen in the favorites tab, grouped by author.

Example queries: `Rembrandt`, `Night watch` and `Vermeer`.

### LiveData
Objects are wrapped in LiveData to ensure constant and async flow. Objects stored in the database are also wrapped in LiveData, causing any database operations to be reflected into the UI instantly.

When unfavoriting an object from the favorites tab, it will be removed from the `RecyclerView smoothly.

### Testing
The room database and repository have test coverage. See `androidTest` source set for the test

### Libraries/Technologies
The following technologies are used:
- `Kotlin` programming language
- `LiveData` for the observer pattern
- `MVVM` architecture
- `Room` persistence library
- `Navigation` component
- `Dagger-Hilt` for dependency injection
- `Paging 3` paging library
- `Moshi` for JSON parsing
- `Coil` image library
- `JUnit4` and `MockK` for testing

