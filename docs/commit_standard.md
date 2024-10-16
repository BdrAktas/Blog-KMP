# Commit Message Standards

This project follows a strict commit message format enforced by Git hooks to maintain consistency and clarity in the version history.

## Format

```
[type][optional scope]: [short summary]
[optional body]
[optional footer]
```

- **Type**: Mandatory, must be one of the allowed types (see below)
- **Scope**: Optional, indicates the section of the codebase
- **Summary**: Mandatory, brief description of the change (50 chars or less)
- **Body**: Optional, detailed explanation of the change (20-72 chars per line)
- **Footer**: Optional, used for referencing issues or noting breaking changes

## Commit Types

- **FEAT**: A new feature
- **FIX**: A bug fix
- **HOTFIX**: A critical bug fix
- **DOCS**: Documentation changes
- **STYLE**: Changes that do not affect the meaning of the code (white-space, formatting, etc)
- **REFACTOR**: A code change that neither fixes a bug nor adds a feature
- **TEST**: Adding missing tests or correcting existing tests
- **BUILD**: Changes that affect the build system or external dependencies
- **CHORE**: Other changes that don't modify src or test files
- **CI**: Changes to the CI configuration files and scripts
- **IMPROVEMENT**: Improvements to existing features

## Scope (Optional)

The scope provides additional contextual information and is placed within parentheses after the type. For example:

```
feat(auth): add two-factor authentication
```

## Examples

### Good Commit Messages

- `feat`: add user registration functionality
- `fix(auth)`: resolve issue with login timeout
- `docs`: update API documentation for new endpoints
- `style`: format code according to style guide
- `refactor(database)`: optimize query performance
- `test`: add unit tests for user service
- `chore`: update dependencies to latest versions

### Bad Commit Messages

- `fixed bug` (lacks type and is too vague)
- `FEAT: Add new feature` (type should be lowercase)
- `feat: implemented lots of new features` (not specific enough and past tense should not be used)
- `fix: corrected various issues in the codebase` (too broad and punctuation should not be used)

## Git Hook

To enforce these standards, I use a `commit-msg` Git hook. This script checks if the commit message adheres to the format:

```bash
#!/bin/sh
COMMIT_MSG_FILE=$1
COMMIT_MSG=$(cat "$COMMIT_MSG_FILE")
FIRST_LINE=$(head -n1 "$COMMIT_MSG_FILE")

if ! echo "$FIRST_LINE" | grep -iqE '^(feat|fix|hotfix|refactor|docs|style|test|build|chore|ci|improvement)(\(.+\))?: .{1,50}$'; then
  echo "Error: Commit message format is incorrect."
  echo "It should match: <type>[optional scope]: <description>"
  echo "Example: feat(auth): add login functionality"
  exit 1
fi

# Check body length if it exists
BODY=$(sed '1d' "$COMMIT_MSG_FILE" | tr -d '\n')
if [ -n "$BODY" ] && [ ${#BODY} -lt 20 ]; then
  echo "Error: Commit message body must be at least 20 characters long."
  exit 1
fi

# Additional checks can be added here

exit 0
```

This hook ensures that:
1. The first line follows the correct format.
2. If a body is present, it's at least 20 characters long.w
3. The commit is rejected with an error message if these conditions aren't met.
## Commit Message Examples

### Valid Commit Messages

1. **Valid Message** (single line):

```
fix: correct calculation error in checkout process
```

2. **Valid Message** (with body and footer):

```
fix: correct calculation error in checkout process

The previous logic was causing incorrect total amounts due to
floating point precision issues. Updated the calculation logic
to round values to two decimal places.

BREAKING CHANGE: user database schema has been updated to add a new field 'age'
JIRA: DEM-1234
```

### Invalid Commit Messages

1. **Invalid Message** (incorrect format):

```
fix added authorization for document access
```

Hook Error message Response:

```
Error: Commit message summary format is incorrect.
The summary should follow this format:
Example: fix: correct calculation error in checkout process
```


By following these standards, I maintain a clean and informative Git history, making it easier for all team members to understand changes and track project progress.
