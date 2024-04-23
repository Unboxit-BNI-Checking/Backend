# BNI Checking API Contract

## Accounts

### /login/me
> 200 Success

    

---
## Twitter Reports
### GET /twitter-report/:id
Get one twitter report using the id

> 200 Success


    {
        success = boolean(true),
        data = {
            "id": int,
            "post_date": timestamp,
            "twitter_username": string,
            "reported_account_number": string,
            created_at: timestamp,
            updated_at: timestamp,
            deleted_at: timestamp
        },
        error = null
    }

**Error Cases:**
- Twitter report id not found
    > 404 Not Found

        {
            success = boolean(false),
            data = null,
            error = "twitter report id is invalid"
        }

---
### GET /twitter-report
Get all twitter reports with assosiated queries

Query:
1. from_date: timestamp 
    - description: take all twitter reports which has created_at higher than from_date
    - default: none
2. include_deleted: boolean
    - description: if true, include all deleted twitter reports
    - default: false
3. twitter_username: string
    - description: take all twitter reports with associated username
    default: none

> 200 Success

    {
        success = boolean(true),
        data = [
            {
                "id": int,
                "post_date": timestamp,
                "twitter_username": string,
                "reported_account_number": string,
                created_at: timestamp,
                updated_at: timestamp,
                deleted_at: timestamp
            },{
                "id": int,
                "post_date": timestamp,
                "twitter_username": string,
                "reported_account_number": string,
                created_at: timestamp,
                updated_at: timestamp,
                deleted_at: timestamp
            },
        ]
        error = null
    }


**Error Cases:**
- No twitter reports found
    > 404 Not Found

        {
            success = boolean(false),
            data = null,
            error = "no twitter reports found"
        }

- No twitter username found
    > 404 Not Found

        {
            success = boolean(false),
            data = null,
            error = "twitter username is not found"
        }

- Wrong date format for from_date
    > 400 Bad Request

        {
            success = boolean(false),
            data = null,
            error = "from_date format is invalid"
        }
