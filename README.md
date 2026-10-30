# LinkedIn UI Test Suite (IT3153 Assignment 04)

Automated UI tests for LinkedIn using Java, Selenium WebDriver 4 and TestNG,
written with the Page Object Model and explicit waits.

## What is tested
- TC-01 Valid login
- TC-02 Invalid login (error message shown)
- TC-03 Search for "software testing"
- TC-04 Open the first search result

## Requirements
- Java 17 or later (tested on JDK 24)
- Maven (or IntelliJ's built-in Maven)
- Google Chrome (latest). Selenium Manager downloads ChromeDriver automatically.

## Setup
1. Clone the repository.
2. Copy `config.properties.example` to `config.properties`.
3. Put your TEST account details inside:
   linkedin.username=your_test_email
   linkedin.password=your_test_password
   (Or set the environment variables LINKEDIN_USER and LINKEDIN_PASS.)
4. Never commit `config.properties`. It is listed in `.gitignore`.

## Run
- In IntelliJ: right-click `testng.xml` and choose Run.
- Or from a terminal in the project folder: `mvn clean test`

## Output
- Test report: `target/surefire-reports/index.html`
- Automatic failure screenshots: `screenshots/`

## Notes
- Use a dedicated test account only. Never use anyone else's account.
- If LinkedIn shows a CAPTCHA or verification code page, the test waits up
  to 120 seconds so you can complete it manually. The tests never bypass it.
- LinkedIn changes its pages often, so locators may need updating.