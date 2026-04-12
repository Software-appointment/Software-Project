# Meeting Room Reservation System

A Java-based Meeting Room Reservation System built with Maven, JUnit 5, Mockito, and JaCoCo.

## Team
- Student A – Jana (Domain + Auth + Observer + Tests)
- Student B – Sadeel (Booking + Rules + ReservationService)
- Student C – Meera (Reservation Types + Notifications)

## Technologies
- Java 8+
- Maven
- JUnit 5
- Mockito
- JaCoCo

## Architecture
Layered N-Tier Architecture:
- Presentation Layer (Swing GUI)
- Service Layer
- Domain Layer
- Repository Layer (File-based storage)

## Design Patterns
- Strategy Pattern – Booking Rules
- Observer Pattern – Notifications

## How to Run
1. Clone the repository
2. Open in Eclipse as Maven project
3. Run `mvn test` to execute all tests
4. Run `mvn javadoc:javadoc` for Javadoc report
5. Run the GUI from `Main.java` in `com.appointment.presentation`

## Test Coverage
- Total: 93% (JaCoCo)
- 63+ tests all passing

## Features
- Admin login/logout
- View available rooms
- Book meeting rooms (Individual, Group, Virtual, In-Person, Urgent, Follow-Up, Assessment)
- Modify/Cancel reservations
- Email & SMS notifications
- Booking rules validation (Capacity, Duration, Equipment)