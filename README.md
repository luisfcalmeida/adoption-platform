# Animal Adoption Platform

A web platform that connects animal rescue associations with people looking to adopt.

## How to run locally
- Prerequisites: Java 21, Docker
- Copy `.env.example` to `.env`
- Run `docker compose up -d`
- Run `./gradlew bootRun`
- Open `http://localhost:8080/api/animals`

## API endpoints
- `GET /api/animals`: returns all animals
- `GET /api/animals/{id}`: returns a single animal by its id (200 if it exists, 404 if it doesn't)
- `POST /api/animals`: creates an animal (201 if created, 400 if the data is invalid)

## MVP Scope

### Public users (no account)
- [ ] Browse all animals (adopted ones are marked as adopted)
- [ ] View an animal's profile (name, species, age, description, photo...)
- [ ] Submit an adoption application

### Rescue associations (authenticated)
- [ ] Log in
- [ ] Add, edit and remove animals
- [ ] Mark an animal as adopted
- [ ] Review adoption applications (approve / reject)

## Out of Scope (for now)
- Association self-registration (accounts created manually)
- Image upload (photo URL only)
- Search and filters
- Email notifications
- Adopter accounts
 
## Tech Stack
- Backend: Java, Spring Boot, Spring Data JPA, Spring Security (planned), Flyway
- Database: PostgreSQL
- Frontend: React (planned)
- Tooling: Docker, Gradle
- Hosting: TBD

## Milestones
1. Animal CRUD + PostgreSQL + first deploy (in progress: read endpoints done)
2. React: animal list + profile, connected to the API
3. Association login + animal management
4. Adoption applications (submit + review)
5. Unit tests, README polish, screenshots

## Definition of Done
- All MVP features work end-to-end
- Live at a public URL
- Unit tests for the adoption application service
- README with setup instructions and screenshots