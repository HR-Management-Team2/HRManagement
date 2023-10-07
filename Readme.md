# Human Resources Management Project

## About
This project is a Human Resources Management system designed to streamline HR processes for companies. With this web-based application, organizations can efficiently record and manage employees and managers, enabling various HR-related tasks and operations.

## Project Architecture
<img src="\kubernetes.png">

## Requirements & Usages
- JDK 17 & IDEs
- Spring Frameworks
- Gradle
- RabbitMq
- Docker
- PostgreSQL
- MongoDB

## Getting Started
To get started with the HRMS backend services, follow these steps:

1. Clone this repository to your local machine using `git clone https://github.com/HR-Management-Team2/HRManagement.git`.

2. Navigate to the project directory: `cd HRManagement`.

3. Configure the backend components:
    - Open the `src/main/resources/application.yml` file and update the necessary configuration settings for database connections, RabbitMQ, etc.

4. Run the backend services:
    - Config Server
    - Auth Micro Service
    - User Micro Service
    - Company Micro Service
    - Mail Micro Service

Note: Make sure to start each microservice in the correct order as per the dependencies they have.

## Project Deployment with Docker

1. Building the application is essential using Gradle. 
   - Click on the Gradle tab on the right.
   - Select the project's name as "config-server-git" since it has multiple configurations.
   - Double-click on Tasks > build > build to create the project.
   - Double-click on Tasks > build > buildDependents.
2. After these steps, your project's JAR file will be added to the "libs" folder inside the "build" folder generated under the project directory. This JAR file is executable.
3. Create a Dockerfile.
4. Build the image using the terminal (console). 
```
   docker build -t config-server-git .  # NOTE: Use the repository name to push this image to the cloud.
```
Note: You can explore Kubernetes files for further reference.

## Contact

- Mehmet Arif Esen - [LinkedIn](https://www.linkedin.com/in/mehmet-arif-esen-5b8aa722a/) - [GitHub](https://github.com/MehmetArifESEN)
- Hivda Aydoğan - [LinkedIn](https://www.linkedin.com/in/hivdaaydogan/) - [GitHub](https://github.com/HivdaAydogan)
- Damla Erişmiş - [LinkedIn](https://www.linkedin.com/in/damla-erismis/) - [GitHub](https://github.com/damlaErismis)
