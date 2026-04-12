# Meeting Room Reservation System

## Team Members
- Mira Halawa
- Jana Kharma  
- Sadeel Khuffash

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

## Email Notification Setup

To enable real email notifications, create a file at:
`src/main/resources/.env`

With the following content:

EMAIL_USERNAME=your_gmail@gmail.com
EMAIL_PASSWORD=your_app_password

### Steps to get Gmail App Password:
1. Go to myaccount.google.com/security
2. Enable 2-Step Verification
3. Go to myaccount.google.com/apppasswords
4. Create a new app password named "JavaMail"
5. Copy the 16-character password into the .env file

## How to Run
1. Clone the repository
2. Open in Eclipse as Maven project
3. Run `mvn test` to execute all tests
4. Run `mvn javadoc:javadoc` for Javadoc report
5. Run the GUI from `MainApp.java` in `com.appointment.presentation`

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