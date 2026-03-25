# 🧪 UI Test Automation Using Selenide

[![UI Test Automation](https://github.com/Kalisha1234/UI-Test-Automation-Using-Selenide/actions/workflows/ui-tests.yml/badge.svg)](https://github.com/Kalisha1234/UI-Test-Automation-Using-Selenide/actions/workflows/ui-tests.yml)
[![Java](https://img.shields.io/badge/Java-11-orange.svg)](https://www.oracle.com/java/)
[![Selenide](https://img.shields.io/badge/Selenide-7.0.4-green.svg)](https://selenide.org/)
[![TestNG](https://img.shields.io/badge/TestNG-7.8.0-red.svg)](https://testng.org/)
[![Allure](https://img.shields.io/badge/Allure-2.20.1-blue.svg)](https://docs.qameta.io/allure/)

A comprehensive UI test automation framework for [Sauce Demo](https://www.saucedemo.com/) using Selenide, TestNG, and Allure reporting with CI/CD integration.

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Running Tests](#-running-tests)
- [Test Coverage](#-test-coverage)
- [Reporting](#-reporting)
- [CI/CD](#-cicd)
- [Docker Support](#-docker-support)
- [Configuration](#-configuration)
- [Contributing](#-contributing)

## ✨ Features

- ✅ **98 Comprehensive Test Cases** covering all major functionalities
- 🎯 **Page Object Model (POM)** design pattern
- 📊 **Allure Reports** with screenshots and detailed logs
- 🔄 **CI/CD Integration** with GitHub Actions
- 📧 **Email & Slack Notifications** for test results
- 🐳 **Docker Support** for containerized execution
- 🔐 **Security Testing** (SQL injection, XSS prevention)
- 🎨 **UI/UX Validation** tests
- ⚡ **Parallel Execution** support
- 📈 **Multiple User Scenarios** (standard, problem, performance, visual users)

## 🛠 Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 11 | Programming Language |
| Maven | 3.9+ | Build Tool |
| Selenide | 7.0.4 | UI Testing Framework |
| TestNG | 7.8.0 | Test Framework |
| Allure | 2.20.1 | Reporting |
| Docker | Latest | Containerization |
| GitHub Actions | - | CI/CD |

## 📁 Project Structure

```
UI-Test-Automation-Using-Selenide/
├── .github/
│   └── workflows/
│       ├── ui-tests.yml          # CI/CD pipeline
│       └── README.md             # CI/CD documentation
├── src/
│   ├── main/java/
│   │   └── com/saucedemo/pages/  # Page Object classes
│   │       ├── LoginPage.java
│   │       ├── ProductsPage.java
│   │       ├── CartPage.java
│   │       ├── CheckoutPage.java
│   │       └── ProductDetailsPage.java
│   └── test/java/
│       └── com/saucedemo/
│           ├── config/           # Test configuration
│           │   ├── BaseTest.java
│           │   └── DriverManager.java
│           ├── tests/            # Test classes
│           │   ├── LoginPageTest.java
│           │   ├── ProductsPageTest.java
│           │   ├── CartPageTest.java
│           │   ├── CheckoutPageTest.java
│           │   ├── SortingTest.java
│           │   ├── NavigationTest.java
│           │   ├── SecurityTest.java
│           │   ├── UIValidationTest.java
│           │   ├── EdgeCaseTest.java
│           │   └── user/         # User-specific tests
│           │       ├── StandardUserTest.java
│           │       ├── ProblemUserTest.java
│           │       ├── PerformanceGlitchUserTest.java
│           │       ├── ErrorUserTest.java
│           │       ├── VisualUserTest.java
│           │       └── LockedOutUserTest.java
│           └── utils/
│               └── TestDataReader.java
├── src/test/resources/
│   ├── testdata/
│   │   └── testdata.properties   # Test data
│   ├── suites/
│   │   ├── smoke-suite.xml       # Smoke test suite
│   │   └── regression-suite.xml  # Regression test suite
│   ├── allure.properties         # Allure configuration
│   └── testng.xml                # TestNG configuration
├── docker-compose.yml            # Docker Compose configuration
├── Dockerfile                    # Docker image definition
├── pom.xml                       # Maven dependencies
├── NOTIFICATION_SETUP.md         # Notification setup guide
└── README.md                     # This file
```

## 📦 Prerequisites

- **Java JDK 11** or higher
- **Maven 3.9+**
- **Chrome Browser** (latest version)
- **Git**
- **Docker** (optional, for containerized execution)

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/Kalisha1234/UI-Test-Automation-Using-Selenide.git
cd UI-Test-Automation-Using-Selenide
```

### 2. Install Dependencies

```bash
mvn clean install -DskipTests
```

### 3. Verify Installation

```bash
mvn --version
java --version
```

## ▶️ Running Tests

### Run All Tests

```bash
mvn clean test
```

### Run Specific Test Suite

```bash
# Smoke Tests
mvn clean test -DsuiteXmlFile=src/test/resources/suites/smoke-suite.xml

# Regression Tests
mvn clean test -DsuiteXmlFile=src/test/resources/suites/regression-suite.xml
```

### Run Specific Test Class

```bash
mvn clean test -Dtest=LoginPageTest
```

### Run in Headless Mode

```bash
mvn clean test -Dheadless=true
```

### Run with Specific Browser

```bash
mvn clean test -Dbrowser=chrome
mvn clean test -Dbrowser=firefox
```

## 🧪 Test Coverage

### Total: 98 Test Cases

| Category | Tests | Description |
|----------|-------|-------------|
| **Login Tests** | 8 | Valid/invalid login, locked users, error messages |
| **Products Tests** | 6 | Product display, add to cart, remove from cart |
| **Cart Tests** | 5 | Cart operations, item count, checkout navigation |
| **Checkout Tests** | 6 | Checkout flow, validation, multiple products |
| **Product Details** | 4 | Product information, navigation, cart operations |
| **Sorting Tests** | 5 | A-Z, Z-A, price sorting, persistence |
| **Navigation Tests** | 10 | Menu, links, logout, social media |
| **Security Tests** | 10 | SQL injection, XSS, direct URL access |
| **UI Validation** | 15 | Images, text, buttons, layout |
| **Edge Cases** | 10 | Boundary conditions, rapid clicks |
| **User Scenarios** | 19 | Standard, problem, performance, visual, error users |

### Test Categories

#### 🔐 Security Tests
- SQL Injection prevention
- XSS attack prevention
- Direct URL access protection
- Input validation (special characters, long strings)
- Case sensitivity checks

#### 🎨 UI/UX Tests
- Element visibility
- Image loading
- Button states
- Cart badge updates
- Footer validation

#### ⚡ Performance Tests
- Performance glitch user scenarios
- Page load times
- Element wait strategies

#### 🧩 Edge Cases
- Add all products
- Remove all products
- Cart persistence
- Rapid clicking
- Navigation flows

## 📊 Reporting

### Allure Reports

#### Generate Report Locally

```bash
# Generate and open report
mvn allure:serve

# Generate report only
mvn allure:report
```

#### View Report

Reports are generated in `target/site/allure-maven-plugin/`

#### CI/CD Reports

Allure reports are automatically deployed to GitHub Pages:
- URL: `https://kalisha1234.github.io/UI-Test-Automation-Using-Selenide/allure-report-{run-number}`

### Report Features

- ✅ Test execution summary
- 📸 Screenshots on failure
- 📝 Step-by-step execution logs
- 📊 Graphs and charts
- 🏷️ Test categorization
- ⏱️ Execution time tracking

## 🔄 CI/CD

### GitHub Actions Workflow

The project includes a comprehensive CI/CD pipeline that:

1. ✅ Runs on every push to `main`
2. ✅ Runs on pull requests
3. ✅ Scheduled daily runs (midnight UTC)
4. ✅ Manual trigger support
5. ✅ Deploys Allure reports to GitHub Pages
6. ✅ Sends Slack notifications
7. ✅ Sends email notifications

### Workflow Features

- **Automated Testing**: Runs all tests in headless mode
- **Allure Reporting**: Generates and deploys reports
- **Notifications**: Slack and email with test results
- **Artifacts**: Stores test results for 30 days
- **Summary**: Beautiful GitHub Actions summary

### Setup Notifications

See [NOTIFICATION_SETUP.md](NOTIFICATION_SETUP.md) for detailed instructions on:
- Setting up Slack webhooks
- Configuring Gmail app passwords
- Adding GitHub secrets

### Required GitHub Secrets

| Secret | Description |
|--------|-------------|
| `SLACK_WEBHOOK_URL` | Slack incoming webhook URL |
| `EMAIL_USERNAME` | Gmail address |
| `EMAIL_PASSWORD` | Gmail app password |
| `EMAIL_RECIPIENTS` | Comma-separated email addresses |

## 🐳 Docker Support

### Build Docker Image

```bash
docker build -t ui-test-automation .
```

### Run Tests in Docker

```bash
docker-compose up
```

### Docker Compose

```bash
# Run tests
docker-compose up --abort-on-container-exit

# Clean up
docker-compose down
```

## ⚙️ Configuration

### Test Data Configuration

Edit `src/test/resources/testdata/testdata.properties`:

```properties
app.url=https://www.saucedemo.com
standard.username=standard_user
standard.password=secret_sauce
checkout.firstname=John
checkout.lastname=Doe
checkout.postalcode=12345
```

### Browser Configuration

Edit `src/test/java/com/saucedemo/config/DriverManager.java`:

```java
Configuration.browser = "chrome";
Configuration.browserSize = "1920x1080";
Configuration.headless = false;
Configuration.timeout = 10000;
```

### TestNG Configuration

Edit `src/test/resources/testng.xml` for:
- Parallel execution
- Thread count
- Test groups
- Listeners

## 📈 Best Practices

This framework follows:

- ✅ **Page Object Model** for maintainability
- ✅ **DRY Principle** (Don't Repeat Yourself)
- ✅ **SOLID Principles** in design
- ✅ **Explicit Waits** over implicit waits
- ✅ **Data-Driven Testing** with properties files
- ✅ **Allure Annotations** for better reporting
- ✅ **TestNG Groups** for test organization
- ✅ **Error Handling** with screenshots
- ✅ **Clean Code** practices

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License.

## 👥 Authors

- **Kalisha1234** - [GitHub Profile](https://github.com/Kalisha1234)

## 🙏 Acknowledgments

- [Selenide](https://selenide.org/) - Elegant Selenium wrapper
- [TestNG](https://testng.org/) - Testing framework
- [Allure](https://docs.qameta.io/allure/) - Reporting framework
- [Sauce Demo](https://www.saucedemo.com/) - Test application

## 📞 Support

For issues and questions:
- 🐛 [Report a Bug](https://github.com/Kalisha1234/UI-Test-Automation-Using-Selenide/issues)
- 💡 [Request a Feature](https://github.com/Kalisha1234/UI-Test-Automation-Using-Selenide/issues)
- 📧 Contact via GitHub

## 🔗 Links

- [GitHub Repository](https://github.com/Kalisha1234/UI-Test-Automation-Using-Selenide)
- [CI/CD Workflow](https://github.com/Kalisha1234/UI-Test-Automation-Using-Selenide/actions)
- [Allure Reports](https://kalisha1234.github.io/UI-Test-Automation-Using-Selenide/)

---

⭐ **Star this repository if you find it helpful!**
