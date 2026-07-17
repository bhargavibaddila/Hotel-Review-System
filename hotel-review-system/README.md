# StayRate — Hotel Review System (Fullstack)

A fullstack web app where users can browse hotels, view average ratings, and submit reviews.

## Tech Stack
- **Backend:** Spring Boot 3, Spring Data JPA, H2 in-memory database, Bean Validation
- **Frontend:** HTML, CSS, vanilla JavaScript (fetch API)

## Project Structure
```
hotel-review-system/
├── backend/                          # Spring Boot app
│   ├── pom.xml
│   └── src/main/java/com/hotelreview/
│       ├── HotelReviewApplication.java
│       ├── model/       (Hotel, Review entities)
│       ├── repository/  (HotelRepository, ReviewRepository)
│       ├── controller/  (HotelController, ReviewController)
│       └── config/      (CorsConfig, GlobalExceptionHandler)
│   └── src/main/resources/
│       ├── application.properties
│       └── data.sql     (sample seed data)
└── frontend/
    └── index.html        (self-contained UI)
```

## How to Run

### 1. Backend (Spring Boot)
You need **Java 17+** and **Maven** installed.

```bash
cd backend
mvn spring-boot:run
```
The API starts on **http://localhost:8080**.

No database setup needed — it uses H2 in-memory DB and auto-loads 4 sample hotels with reviews on startup.

You can browse the DB directly at `http://localhost:8080/h2-console`
(JDBC URL: `jdbc:h2:mem:hoteldb`, username: `sa`, password: blank).

### 2. Frontend
Just open `frontend/index.html` directly in your browser (double-click it, or
right-click → Open with browser). It talks to the backend at `localhost:8080`.

> If your browser blocks the fetch calls from a `file://` page, instead serve it
> with a tiny local server, e.g. run `python3 -m http.server 5500` inside the
> `frontend` folder and visit `http://localhost:5500`.

## API Endpoints

| Method | Endpoint                          | Description                  |
|--------|------------------------------------|-------------------------------|
| GET    | /api/hotels                       | List all hotels               |
| GET    | /api/hotels/{id}                  | Get one hotel                 |
| GET    | /api/hotels/search?query=goa      | Search hotels by name/location|
| POST   | /api/hotels                       | Create a hotel                |
| PUT    | /api/hotels/{id}                  | Update a hotel                |
| DELETE | /api/hotels/{id}                  | Delete a hotel (+ its reviews)|
| GET    | /api/hotels/{id}/reviews          | List reviews for a hotel      |
| POST   | /api/hotels/{id}/reviews          | Add a review to a hotel       |
| DELETE | /api/reviews/{id}                 | Delete a review                |
| GET    | /api/reviews                      | List all reviews (all hotels) |

## Sample Request Body

**Create hotel** — `POST /api/hotels`
```json
{
  "name": "The Grand Palace",
  "location": "Hyderabad, India",
  "description": "A luxurious 5-star hotel.",
  "imageUrl": "https://example.com/image.jpg"
}
```

**Add review** — `POST /api/hotels/1/reviews`
```json
{
  "reviewerName": "Ananya Rao",
  "rating": 5,
  "comment": "Absolutely wonderful stay!"
}
```

## Features Covered
- Full CRUD on hotels and reviews
- Average rating auto-computed per hotel (not stored, calculated live)
- Bean Validation with clean JSON error responses
- CORS configured so the frontend can call the API from any origin
- Seed data so the demo isn't empty on first run
- Clean separation: Controller → Repository → Entity (standard layered architecture)

## Notes for Viva / Demo
- Mention **H2 in-memory database** if asked why no MySQL setup was needed — it's
  production-swappable: just change `application.properties` to point at MySQL/Postgres
  and add the matching driver dependency.
- `averageRating` and `reviewCount` on `Hotel` are **computed getters**, not DB columns —
  good talking point on derived data vs. stored data.
- Layered architecture (Controller/Service-less-but-Repository/Entity) keeps it simple;
  mention you could add a Service layer for larger scale.
