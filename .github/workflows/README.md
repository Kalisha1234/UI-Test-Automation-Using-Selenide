# CI/CD Configuration Guide

## Required GitHub Secrets

To enable notifications and reporting, add these secrets in your GitHub repository:

### Settings → Secrets and variables → Actions → New repository secret

1. **SLACK_WEBHOOK_URL**
   - Go to your Slack workspace
   - Create an Incoming Webhook: https://api.slack.com/messaging/webhooks
   - Copy the webhook URL
   - Add as secret

2. **EMAIL_USERNAME**
   - Your Gmail address (e.g., your-email@gmail.com)
   - For Gmail, you need to use App Password, not your regular password

3. **EMAIL_PASSWORD**
   - Gmail App Password (not your regular password)
   - Generate at: https://myaccount.google.com/apppasswords
   - Select "Mail" and "Other (Custom name)"
   - Copy the 16-character password

4. **EMAIL_RECIPIENTS**
   - Comma-separated email addresses
   - Example: `dev1@example.com,dev2@example.com,qa@example.com`

## Features

### 📊 GitHub Actions Summary
- Beautiful test execution summary with emojis
- Pass rate calculation
- Direct links to Allure reports and workflow runs

### 📧 Email Notifications
- HTML formatted emails with test statistics
- Visual pass/fail indicators
- Direct links to reports

### 💬 Slack Notifications
- Rich formatted messages with test results
- Interactive buttons to view reports
- Color-coded status indicators

### 📈 Allure Reports
- Automatically deployed to GitHub Pages
- Accessible via unique URL for each run
- 30-day artifact retention

## GitHub Pages Setup

1. Go to Settings → Pages
2. Source: Deploy from a branch
3. Branch: gh-pages / (root)
4. Save

Reports will be available at:
`https://[username].github.io/[repo-name]/allure-report-[run-number]`

## Scheduled Runs

The workflow runs:
- On push to main branch
- On pull requests to main
- Daily at midnight (UTC) - cron: '0 0 * * *'
- Manual trigger via workflow_dispatch
