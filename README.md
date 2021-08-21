# Atypon Java and DevOps Training  
Atypon Web Project Using Spring, Spring Security, Spring Data JPA, Thymeleaf with Spring extras security extension. 

![](Screenshots/login.png)

## Overview

The system has 3 levels of users: Students, Instructors, Admins. 

**Administrator**: 
- Add, Update, Delete, View all users.
- Add, Update, Delete, View all courses.
- Assign/Remove instructors to/from courses.
- Add/Remove students to/from courses.

**Instructor**: 
- Can only see their courses, students enrolled in these courses.
- Assign grades to their students.
- 
**Student**: 
- Can only view thier courses marks.

## Technology Stack
- Java 16
- Spring Core, Boot, MVC, Security, Data JPA
- MySQL database (on a Docker Contatiner)
- Thymeleaf rendring engine and Bootstrap CSS framework for the front-end
- Maven Build Tool
- Jenkins for CI/CD
- GitHub for VS
- AWS and Docker for deployment.

![](Screenshots/Pipeline.png)

