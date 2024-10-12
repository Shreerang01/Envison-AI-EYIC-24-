# Envision AI Mobile App - Smart Crime Detection System

**Project Duration**: November 2023 - April 2024  
**Team**: Envision AI  
**Award**: Best Implementation Award at IIT Bombay e-Yantra Innovation Challenge (EYIC) 2024  

This repository contains the development work for the **Envision AI Mobile App**, an Android application built using **Kotlin** as part of the **AI-Driven Crime Detection System**. This smart crime detection solution leverages existing CCTV infrastructure to enhance public safety by detecting criminal activities with greater accuracy and reducing response times. The app enables real-time crime alerts and includes a Human Validation Mechanism to minimize false positives.

# Best implementation Award Certificate
![Best Implementation Award Certificate ](https://github.com/user-attachments/assets/632e8a77-20df-4e9e-89a8-37ba96d905b3)

# Envision AI Team at IIT Bombay
![Team Envision AI ](https://github.com/user-attachments/assets/fccb5dc2-7360-47f5-b0f1-c915ef73742c)

# Award Ceremony 
![Team Envision AI Felicitation](https://github.com/user-attachments/assets/fe688c9f-ebed-4aff-aba5-42130223cb17)

# Memento of Team Envision AI
![Team Appreciation certificates and goodies](https://github.com/user-attachments/assets/f3cf530b-40c5-4c7c-89cf-e7b854b40d24)


## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [System Architecture](#system-architecture)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [License](#license)

## Introduction
The **Envision AI Mobile App** was developed as part of a comprehensive AI-driven system to improve public safety by enhancing the accuracy of crime detection and reducing response times. The app is designed to work with existing CCTV networks, offering real-time monitoring, automated crime detection alerts, and secure cloud-based data handling. The system achieved:
- **40% improvement in crime detection accuracy**.
- **25% reduction in response time**.

The app received the **Best Implementation Award** at IIT Bombay's e-Yantra Innovation Challenge (EYIC) 2024 among 299 competing teams for its innovative features and outstanding results in public safety.

## Features
- **Live CCTV Feeds**: Access to real-time CCTV footage for monitoring critical locations.
- **Automated Crime Detection Alerts**: AI-driven detection of suspicious activities with instant notifications.
- **Human Validation Mechanism**: Reduces false errors by incorporating a validation process involving human oversight.
- **Secure Cloud Storage**: Metadata and CCTV footage are securely stored in the cloud for further analysis and reporting.
- **Reduced Response Times**: The system increases the speed of anti-crime response by 25% through real-time notifications and precise geolocation of incidents.

## System Architecture
The app follows a clean and modular architecture designed for scalability and real-time performance. Key components of the architecture include:
1. **Frontend (View)**: Built using Kotlin and Jetpack Compose for dynamic and responsive UI.
2. **Backend (Model)**: Integrated with cloud services for secure data storage and communication with crime detection algorithms.
3. **AI Models**: Advanced machine learning algorithms for detecting and analyzing criminal activities.
4. **Human Validation**: A feedback loop allows human operators to verify AI-generated alerts, reducing false positives and improving system reliability.

## Technologies Used
- **Kotlin**: Used for Android development and implementing app features.
- **Jetpack Compose**: For building a modern, responsive UI.
- **AWS S3 and Dynamodb**: For secure cloud storage and user authentication.
- **Machine Learning**: AI models to detect suspicious activities in CCTV footage.

Sure, here’s the updated **Setup and Installation** section reflecting the use of **AWS S3** and **DynamoDB** for cloud storage instead of Google Places API.

---

## Setup and Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Shreerang01/Envison-AI-EYIC-24-.git
   cd Envison-AI-EYIC-24-
   ```

2. Open the project in **Android Studio**.

3. Install the required dependencies using Gradle:
   ```bash
   ./gradlew build
   ```

4. Configure **AWS S3** for file storage:
   - Create an **S3 bucket** in the [AWS Management Console](https://aws.amazon.com/console/).
   - Configure the app to use your bucket for storing CCTV footage and metadata. Update the necessary configuration in the app's settings (e.g., API keys, bucket name).
   
5. Configure **AWS DynamoDB** for database storage:
   - Create a **DynamoDB table** for storing metadata related to crime detection.
   - Update the app with your **DynamoDB** table details and credentials.

6. Configure Firebase:
   - Connect the app to your Firebase project for authentication and other services.
   - Download the `google-services.json` file from Firebase and add it to the `app/` directory.

7. Run the app on an Android device or emulator:
   ```bash
   ./gradlew installDebug
   ```

---
## Usage

1. **Monitor CCTV Feeds**: Use the app to access live CCTV feeds and monitor high-risk locations in real time.
2. **Receive Alerts**: Get instant alerts when the AI detects potential criminal activities.
3. **Validate Alerts**: Human operators can review and validate the alerts generated by the AI, ensuring accuracy.
4. **Historical Data**: Access stored footage and metadata from the cloud for review or analysis.
5. **Multi-Version Support**: The repository includes all versions of the app, allowing you to explore development progress or use specific versions.


## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

---
