## AI Usage Summary

AI tools, like ChatGPT, were used as helpers during the project's development. The suggestions that were created were reviewed, changed if needed and tested before being included in the final solution.

---

## 1. AI-Generated vs. Self-Written Code

### AI Assistance

ChatGPT was used to assist with:

- Understanding the project requirements and planning the implementation.
- Suggesting the overall project structure (Controller → Service → Repository).
- Providing guidance on designing REST endpoints.
- Explaining Spring Boot annotations and best practices.
- Suggesting the Swagger/OpenAPI integration.
- Reviewing the README.md and AI_NOTES.md structure.

### Written by Me

The following were implemented and written by me:

- Expense model
- Repository implementation using an in-memory `List<Expense>`
- Service layer business logic
- REST controller implementation
- Global exception handling
- Custom exception classes
- Swagger configuration
- Unit and controller tests
- Project documentation (README.md)

---

## 2. Validation and Changes Made

I reviewed and validated all AI suggestions before using them.

The following changes and validations were performed:

- Implemented the repository using an in-memory `List` instead of a database, as required by the assignment.
- Simplified suggested code where unnecessary complexity was introduced.
- Improved exception handling to return meaningful HTTP status codes and error responses.
- Verified every REST endpoint using Swagger UI and Postman.
- Executed the complete test suite using Maven (`mvn test`) and resolved any issues before submission.
- Ensured the project builds successfully using `mvn clean install`.

---

## 3. AI Suggestions Not Used

The following AI suggestions were intentionally not used:

- Database integration (MySQL/JPA), because the assignment explicitly specifies that data may be stored in memory.
- Additional features beyond the assignment requirements (such as authentication and persistence) to keep the project focused on the requested functionality.
- Using Lombok to reduce boilerplate code, since I preferred explicit Java code for better readability and easier understanding.

---

## Final Note

AI was used to help with learning and doing work more efficiently, but it wasn't used as a replacement for implementation. All generated suggestions were reviewed, adapted where necessary and tested before being included in the final project.
