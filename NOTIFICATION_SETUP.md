# Email and Slack Configuration Guide

## 📧 Email Configuration (Gmail)

### Step 1: Enable 2-Factor Authentication
1. Go to https://myaccount.google.com/security
2. Click on "2-Step Verification"
3. Follow the prompts to enable 2FA (required for App Passwords)

### Step 2: Generate App Password
1. Go to https://myaccount.google.com/apppasswords
2. If you don't see this option, make sure 2FA is enabled
3. Select app: **Mail**
4. Select device: **Other (Custom name)**
5. Enter name: `GitHub Actions UI Tests`
6. Click **Generate**
7. Copy the 16-character password (format: xxxx xxxx xxxx xxxx)
8. **Important:** Save this password - you won't see it again!

### Step 3: Add GitHub Secrets
1. Go to your GitHub repository
2. Navigate to: **Settings** → **Secrets and variables** → **Actions**
3. Click **New repository secret**

#### Add EMAIL_USERNAME:
- Name: `EMAIL_USERNAME`
- Value: Your Gmail address (e.g., `yourname@gmail.com`)
- Click **Add secret**

#### Add EMAIL_PASSWORD:
- Name: `EMAIL_PASSWORD`
- Value: The 16-character app password from Step 2 (remove spaces)
- Click **Add secret**

#### Add EMAIL_RECIPIENTS:
- Name: `EMAIL_RECIPIENTS`
- Value: Comma-separated email addresses
- Example: `dev1@example.com,qa@example.com,manager@example.com`
- Click **Add secret**

### Troubleshooting Email
- ❌ "Invalid credentials" → Check app password is correct (no spaces)
- ❌ "Less secure app" error → Use App Password, not regular password
- ❌ Not receiving emails → Check spam folder
- ❌ 2FA not available → Contact your Google Workspace admin

---

## 💬 Slack Configuration

### Step 1: Create Slack App
1. Go to https://api.slack.com/apps
2. Click **Create New App**
3. Select **From scratch**
4. App Name: `UI Test Automation Bot`
5. Pick your workspace
6. Click **Create App**

### Step 2: Enable Incoming Webhooks
1. In your app settings, click **Incoming Webhooks** (left sidebar)
2. Toggle **Activate Incoming Webhooks** to **On**
3. Scroll down and click **Add New Webhook to Workspace**
4. Select the channel where you want notifications (e.g., `#test-results`, `#qa`, `#ci-cd`)
5. Click **Allow**

### Step 3: Copy Webhook URL
1. You'll see your webhook URL in the format:
   ```
   https://hooks.slack.com/services/T00000000/B00000000/XXXXXXXXXXXXXXXXXXXX
   ```
2. Click **Copy** button
3. **Important:** Keep this URL secret!

### Step 4: Add GitHub Secret
1. Go to your GitHub repository
2. Navigate to: **Settings** → **Secrets and variables** → **Actions**
3. Click **New repository secret**
4. Name: `SLACK_WEBHOOK_URL`
5. Value: Paste the webhook URL from Step 3
6. Click **Add secret**

### Step 5: Test Slack Integration (Optional)
You can test the webhook using curl:
```bash
curl -X POST -H 'Content-type: application/json' \
--data '{"text":"Test message from UI Test Automation"}' \
YOUR_WEBHOOK_URL
```

### Customizing Slack Channel
To send notifications to different channels:
1. Go back to https://api.slack.com/apps
2. Select your app
3. Click **Incoming Webhooks**
4. Add new webhook for different channel
5. Update the `SLACK_WEBHOOK_URL` secret

### Troubleshooting Slack
- ❌ "Invalid webhook" → Check URL is complete and correct
- ❌ Not receiving messages → Check channel permissions
- ❌ "Channel not found" → Ensure bot is added to private channels
- ❌ Formatting issues → Webhook URL must be for Incoming Webhooks (not other types)

---

## 🔐 GitHub Secrets Summary

After completing both configurations, you should have these 4 secrets:

| Secret Name | Example Value | Description |
|-------------|---------------|-------------|
| `EMAIL_USERNAME` | `yourname@gmail.com` | Gmail address |
| `EMAIL_PASSWORD` | `abcdabcdabcdabcd` | 16-char app password |
| `EMAIL_RECIPIENTS` | `dev@example.com,qa@example.com` | Comma-separated emails |
| `SLACK_WEBHOOK_URL` | `https://hooks.slack.com/services/...` | Slack webhook URL |

### Verify Secrets
1. Go to **Settings** → **Secrets and variables** → **Actions**
2. You should see all 4 secrets listed
3. You cannot view secret values after creation (security feature)
4. You can update them by clicking **Update** next to each secret

---

## 🚀 Testing the Configuration

### Option 1: Manual Trigger
1. Go to **Actions** tab in your repository
2. Select **UI Test Automation** workflow
3. Click **Run workflow** dropdown
4. Select branch (usually `main`)
5. Click **Run workflow**
6. Wait for completion and check:
   - Email inbox for notification
   - Slack channel for message

### Option 2: Push to Main
1. Make any commit to main branch
2. Workflow will trigger automatically
3. Check notifications after completion

### What to Expect

#### Email Notification:
- Subject: ✅ or ❌ UI Test Results - [repo-name]
- Beautiful HTML format with:
  - Test statistics
  - Pass/Fail counts
  - Links to Allure report and workflow

#### Slack Notification:
- Message in configured channel
- Test summary with metrics
- Interactive buttons:
  - "View Allure Report"
  - "View Workflow"

---

## 📝 Alternative Email Providers

### Using Outlook/Office 365
```yaml
server_address: smtp.office365.com
server_port: 587
username: ${{ secrets.EMAIL_USERNAME }}
password: ${{ secrets.EMAIL_PASSWORD }}
```

### Using SendGrid
```yaml
server_address: smtp.sendgrid.net
server_port: 587
username: apikey
password: ${{ secrets.SENDGRID_API_KEY }}
```

### Using AWS SES
```yaml
server_address: email-smtp.us-east-1.amazonaws.com
server_port: 587
username: ${{ secrets.AWS_SES_USERNAME }}
password: ${{ secrets.AWS_SES_PASSWORD }}
```

---

## 🔒 Security Best Practices

1. ✅ **Never commit secrets** to your repository
2. ✅ **Use App Passwords** instead of regular passwords
3. ✅ **Rotate secrets** periodically (every 90 days)
4. ✅ **Limit email recipients** to necessary team members
5. ✅ **Use dedicated Slack channels** for CI/CD notifications
6. ✅ **Review webhook permissions** regularly
7. ✅ **Delete unused webhooks** and app passwords

---

## 📞 Support

If you encounter issues:
1. Check the workflow logs in GitHub Actions
2. Verify all secrets are correctly set
3. Test email/Slack independently
4. Review the troubleshooting sections above

For Gmail issues: https://support.google.com/accounts/answer/185833
For Slack issues: https://api.slack.com/messaging/webhooks
