# UI Test Automation Using Selenide

## Project Overview
This project demonstrates automated UI testing using **Selenide**, a powerful framework built on top of Selenium. It validates core functionalities of the Swag Labs web application including user login, product search, product details, cart functionality, and checkout processes. The project emphasizes simplicity, stability, and maintainability using Selenide's fluent API and concise syntax.

## Application Under Test
**Swag Labs**: https://www.saucedemo.com/
- User login and authentication
- Product browsing and search
- Product details page
- Shopping cart functionality
- Checkout process

## Technologies Used
- **Java 17**
- **Selenide 7.0.4** - UI automation framework
- **TestNG 7.8.0** - Testing framework
- **Allure 2.24.0** - Test reporting
- **Maven** - Build and dependency management
- **Docker** - Containerization
- **GitHub Actions** - CI/CD pipeline

## Project Structure
```
UI Test Automation/
├── src/
│   ├── main/java/com/saucedemo/
│   │   ├── pages/              # Page Object Model classes
│   │   │   ├── LoginPage.java
│   │   │   ├── ProductsPage.java
│   │   │   ├── ProductDetailsPage.java
│   │   │   ├── CartPage.java
│   │   │   └── CheckoutPage.java
│   │   └── utils/
│   │       └── TestDataReader.java
│   └── test/java/com/saucedemo/
│       ├── config/
│       │   ├── BaseTest.java          # Base test configuration
│       │   └── DriverManager.java     # Browser configuration
│       └── tests/
│           ├── LoginPageTest.java
│           ├── ProductsPageTest.java
│           ├── ProductDetailsPageTest.java
│           ├── CartPageTest.java
│           ├── CheckoutPageTest.java
│           ├── SortingTest.java
│           ├── NavigationTest.java
│           ├── SecurityTest.java
│           ├── UIValidationTest.java
│           ├── EdgeCaseTest.java
│           └── user/                  # User-specific tests
│               ├── StandardUserTest.java
│               ├── LockedOutUserTest.java
│               ├── ProblemUserTest.java
│               ├── PerformanceGlitchUserTest.java
│               ├── ErrorUserTest.java
│               └── VisualUserTest.java
├── src/test/resources/
│   ├── testng.xml                     # Main test suite
│   ├── suites/
│   │   ├── smoke-suite.xml           # Smoke test suite
│   │   └── regression-suite.xml      # Regression test suite
│   └── config.properties
├── .github/workflows/
│   └── ui-tests.yml                  # GitHub Actions CI/CD
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

## Key Features

### ✅ Page Object Model (POM)
- Clean separation of page elements and test logic
- Reusable page classes for maintainability
- Selenide's fluent API for concise element interactions

### ✅ Selenide Features
- **Automatic waits** - No explicit waits needed
- **Smart assertions** - Built-in conditions and assertions
- **Screenshot on failure** - Automatic screenshot capture
- **Fluent API** - Readable and maintainable test code

### ✅ Test Suites
1. **Smoke Test Suite** (`smoke-suite.xml`)
   - Login functionality
   - Product page display
   - Quick validation tests
   
2. **Regression Test Suite** (`regression-suite.xml`)
   - Full cart functionality
   - Complete checkout process
   - All user scenarios
   - Comprehensive validation

### ✅ Headless Browser Support
- Chrome headless mode
- Firefox headless mode
- Configurable via system properties

### ✅ Docker Support
- Containerized test execution
- Isolated test environment
- Easy deployment and scaling

### ✅ CI/CD Integration
- GitHub Actions workflow
- Automated test execution on every commit
- Test result notifications (Slack & Email)
- Allure report generation and deployment

### ✅ Test Reporting
- **Allure Reports** with detailed test execution data
- Screenshots on test failures
- Error messages and stack traces
- Test execution history

## Setup Instructions

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Chrome or Firefox browser
- Docker (optional)

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/Kalisha1234/UI-Test-Automation-Using-Selenide.git
cd UI-Test-Automation-Using-Selenide
```

2. **Install dependencies**
```bash
mvn clean install -DskipTests
```

3. **Configure test data** (Optional)
Edit `src/test/resources/config.properties` to customize test configuration.

## Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run in Headless Mode
```bash
mvn clean test -Dheadless=true
```

### Run Specific Browser
```bash
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=chrome
```

### Run Smoke Test Suite
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/suites/smoke-suite.xml
```

### Run Regression Test Suite
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/suites/regression-suite.xml
```

### Generate Allure Report
```bash
mvn allure:report
mvn allure:serve
```

## Docker Execution

### Build Docker Image
```bash
docker build -t ui-test-automation .
```

### Run Tests in Docker
```bash
docker run --rm ui-test-automation
```

### Using Docker Compose
```bash
docker-compose up
```

## Test Coverage

### Total Tests: 98

#### Login & Authentication (5 tests)
- Successful login
- Invalid username/password
- Empty credentials
- Locked out user

#### Products & Navigation (16 tests)
- Product listing
- Product details
- Sorting functionality
- Navigation flows

#### Cart & Checkout (11 tests)
- Add/remove items
- Cart validation
- Checkout process
- Form validation

#### Security Tests (10 tests)
- SQL injection prevention
- XSS prevention
- Direct URL access protection
- Input validation

#### UI Validation (15 tests)
- Element visibility
- Layout validation
- Responsive design
- Error messages

#### User-Specific Tests (26 tests)
- Standard user
- Locked out user
- Problem user
- Performance glitch user
- Error user
- Visual user

#### Edge Cases (10 tests)
- Boundary conditions
- Special characters
- Long inputs
- Empty states

#### Additional Tests (5 tests)
- Sorting functionality
- Navigation patterns

## CI/CD Pipeline

### GitHub Actions Workflow
The project includes a comprehensive CI/CD pipeline that:
- ✅ Triggers on every push to main branch
- ✅ Runs tests in headless Chrome
- ✅ Generates Allure reports
- ✅ Deploys reports to GitHub Pages
- ✅ Sends notifications (Slack & Email)
- ✅ Uploads test artifacts

### Workflow Features
- Automated test execution
- Test result parsing
- Pass rate calculation
- Screenshot capture on failures
- Detailed test summaries

## Assertions & Waits

### Selenide Automatic Waits
```java
// No explicit waits needed - Selenide handles it automatically
$("#username").setValue("standard_user");
$("#password").setValue("secret_sauce");
$("#login-button").click();

// Smart assertions with automatic retries
$(".title").shouldHave(text("Products"));
$(".inventory_item").shouldBe(visible);
```

### Hard Assertions (TestNG)
```java
assertTrue(productsPage.isDisplayed(), "Products page should be displayed");
assertEquals(cartPage.getCartItemsCount(), 1, "Cart should contain 1 item");
```

### Soft Assertions
```java
// Selenide conditions act as soft assertions when chained
$(".element").shouldBe(visible).shouldHave(text("Expected"));
```

## Screenshot on Failures

Screenshots are automatically captured on test failures:
- **Selenide Configuration**: `Configuration.screenshots = true`
- **Allure Integration**: Screenshots attached to Allure reports
- **BaseTest**: Captures screenshot, error message, and stack trace

```java
@AfterMethod
public void tearDown(ITestResult result) {
    if (result.getStatus() == ITestResult.FAILURE) {
        byte[] screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Failure Screenshot", "image/png", new ByteArrayInputStream(screenshot), "png");
        Allure.addAttachment("Error Message", "text/plain", result.getThrowable().getMessage());
        Allure.addAttachment("Stack Trace", "text/plain", getStackTrace(result.getThrowable()));
    }
}
```

## Configuration

### Browser Configuration (DriverManager.java)
```java
Configuration.browser = "chrome";           // Browser type
Configuration.headless = false;             // Headless mode
Configuration.browserSize = "1920x1080";    // Window size
Configuration.timeout = 10000;              // Default timeout (10s)
Configuration.screenshots = true;           // Enable screenshots
```

### System Properties
- `-Dbrowser=chrome|firefox` - Browser selection
- `-Dheadless=true|false` - Headless mode
- `-DsuiteXmlFile=path/to/suite.xml` - Test suite selection

## Best Practices Implemented

1. **Page Object Model** - Clean separation of concerns
2. **Selenide Fluent API** - Readable and maintainable code
3. **Automatic Waits** - No explicit waits needed
4. **Smart Assertions** - Built-in retry mechanism
5. **Screenshot on Failure** - Automatic error capture
6. **Allure Reporting** - Comprehensive test reports
7. **Docker Support** - Isolated test environment
8. **CI/CD Integration** - Automated testing pipeline
9. **Test Suites** - Organized smoke and regression tests
10. **Headless Execution** - Fast CI/CD execution

## Troubleshooting

### Common Issues

**Issue**: Tests fail with "Element not found"
**Solution**: Selenide automatically waits, but you can increase timeout:
```java
Configuration.timeout = 15000; // 15 seconds
```

**Issue**: Chrome driver version mismatch
**Solution**: Selenide automatically downloads the correct driver version

**Issue**: Tests fail in headless mode
**Solution**: Some elements may behave differently in headless mode. Add explicit waits if needed:
```java
$(".element").shouldBe(visible, Duration.ofSeconds(10));
```

## Contributing
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## Project Requirements Checklist

✅ **Setup the automation project** - Maven project with all dependencies  
✅ **Create Page Object classes** - 5 page classes (Login, Products, ProductDetails, Cart, Checkout)  
✅ **Selenide based test cases** - 98 comprehensive tests  
✅ **Implement assertions and waits** - Selenide automatic waits + TestNG assertions  
✅ **Headless browser support** - Chrome & Firefox headless mode  
✅ **Smoke test suite** - Login and product page tests  
✅ **Regression test suite** - Cart and checkout functionality  
✅ **Dockerize the project** - Dockerfile and docker-compose.yml  
✅ **GitHub Actions CI/CD** - Automated tests on every commit  
✅ **Screenshot on failures** - Automatic capture and Allure attachment  
✅ **Generate report** - Allure reports with detailed test execution data  

## License
This project is created for educational purposes.

## Contact
For questions or support, please open an issue in the GitHub repository.
