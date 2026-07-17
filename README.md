# StayRate — Hotel Review System

A simple fullstack web application where users can view hotels and add reviews.

---

## Tech Stack

**Backend**

* Spring Boot
* Spring Data JPA
* H2 Database

**Frontend**

* HTML, CSS
* JavaScript (Fetch API)

---

## Project Structure

```
hotel-review-system/
├── backend/
│   ├── pom.xml
│   └── src/main/java/com/hotelreview/
│       ├── model/
│       ├── repository/
│       ├── controller/
│       └── config/
│   └── src/main/resources/
│       ├── application.properties
│       └── data.sql
└── frontend/
    └── index.html
```

---

## How to Run

### Backend

Make sure Java 17+ and Maven are installed.

```bash
cd backend
mvn spring-boot:run
```

Runs on:
http://localhost:8080

H2 Console:
http://localhost:8080/h2-console

```
JDBC URL: jdbc:h2:mem:hoteldb
Username: sa
Password: (leave empty)
```

---

### Frontend

Open `frontend/index.html` in a browser

If it doesn’t load data, run:

```bash
cd frontend
python3 -m http.server 5500
```

Then open:
http://localhost:5500

---

## API

* GET `/api/hotels`
* GET `/api/hotels/{id}`
* GET `/api/hotels/search?query=...`
* POST `/api/hotels`
* PUT `/api/hotels/{id}`
* DELETE `/api/hotels/{id}`
* GET `/api/hotels/{id}/reviews`
* POST `/api/hotels/{id}/reviews`
* DELETE `/api/reviews/{id}`
* GET `/api/reviews`

---

## Sample JSON

Create hotel:

```json
{
  "name": "The Grand Palace",
  "location": "Hyderabad, India",
  "description": "A luxurious 5-star hotel",
  "imageUrl": "https://example.com/image.jpg"
}
```

Add review:

```json
{
  "reviewerName": "Ananya",
  "rating": 5,
  "comment": "Great stay"
}
```

---

## Notes

* Uses in-memory database (data resets on restart)
* Average rating is calculated, not stored
* Simple structure without service layer
