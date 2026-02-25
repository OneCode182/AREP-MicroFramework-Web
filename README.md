# AREP-MicroFramework-Web

![Java](https://img.shields.io/badge/Java-17+-007396?logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?logo=apache-maven&logoColor=white)
![Git](https://img.shields.io/badge/Git-Project-F05032?logo=git&logoColor=white)

> A lightweight, fully functional web microframework developed in Java for serving RESTful services and static resources.

---

## Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Execution](#execution)
- [Features](#features)
- [Testing](#testing)
- [Evaluation Rubric](#evaluation-rubric)
- [Author](#author)

---

## Overview

This project focuses on the development of a web microframework from scratch. It extends a basic web server to support:
1. **REST Services**: Defining routes using lambda functions (e.g., `get("/path", (req, res) -> ...)`).
2. **Query Parameter Management**: Extracting values from the request URL.
3. **Static File Serving**: Configuring a specific directory for static assets (HTML, JS, CSS, images).

The objective is to understand the underlying architecture of web protocols (HTTP), distributed applications, and the internet.

---

## Architecture

*Add a detailed description or diagram of your system architecture here.*

### Components
- **Web Server Core**: Handles socket connections and HTTP parsing.
- **Request/Response Wrappers**: Abstractions for managing HTTP data.
- **MicroFramework API**: The user-facing interface for service definition.
- **Static File Manager**: Handles resource retrieval from the file system.

---

## Project Structure

```text
/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── edu/escuelaing/arep/
│   │   │       ├── MicroFramework.java
│   │   │       ├── server/
│   │   │       └── testapp/
│   │   └── resources/
│   │       └── webroot/
│   └── test/
│       └── java/
```

---

## Getting Started

### Prerequisites

- **Java SDK 17+**
- **Apache Maven 3+**

<details>
<summary>Installation and Execution Guide</summary>

### Installation

```bash
# Clone the repository
git clone https://github.com/USER/AREP-MicroFramework-Web.git

# Navigate to the project directory
cd AREP-MicroFramework-Web

# Build the project
mvn clean install
```

### Execution

```bash
# Run the application
java -cp target/classes:target/dependency/* edu.escuelaing.arep.Main
```

</details>


---

## Features

- [ ] **lambda-based REST services**: via the `get()` method.
- [ ] **Query parameters**: accessible through the request object.
- [ ] **Static file location**: configurable via `staticfiles()`.
- [ ] **Professional structure**: Maven-compatible layout.

---

## Testing

### Automated Tests
*Explain your testing strategy here.*

Run the tests using Maven:
```bash
mvn test
```

### Manual Testing
- Accessing `http://localhost:8080/App/hello?name=User`
- Accessing static files at `http://localhost:8080/index.html`

---

## Evaluation Rubric

<details>
<summary>View Rubric</summary>

| Reference Criterion | Points |
| :--- | :---: |
| **Deliverables** | **7** |
| Deployed on GitHub | 1 |
| Complete .gitignore file | 1 |
| Has README.md | 1 |
| Contains no unnecessary files or folders | 1 |
| Has a POM.xml | 1 |
| Respects Maven structure | 1 |
| Does not contain the target folder | 1 |
| **Design and Architecture** | **35** |
| Implement a `get()` method with lambda functions | 3 |
| Mechanism to extract query parameters | 3 |
| Introduce a `staticfiles()` method | 3 |
| Meets all other functional requirements | 3 |
| Meets quality attributes | 5 |
| System design seems reasonable for the problem | 3 |
| Design is well documented in the README.md | 3 |
| README contains installation and usage instructions | 3 |
| README shows evidence of tests | 3 |
| Has automated tests | 3 |
| Repository can be cloned and executed | 3 |
| **Total** | **42** |

</details>

---

## Author

**Sergio Andrey Silva Rodriguez**  
*Systems Engineering Student*  
Escuela Colombiana de Ingeniería Julio Garavito

---

<details>
<summary>License</summary>

This project is for educational purposes as part of the AREP course at Escuela Colombiana de Ingeniería Julio Garavito.

</details>
