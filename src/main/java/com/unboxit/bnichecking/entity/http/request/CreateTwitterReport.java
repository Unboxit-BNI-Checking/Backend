package com.unboxit.bnichecking.entity.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class CreateTwitterReport {
    @JsonProperty("post_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime postDate;

    @JsonProperty("twitter_username")
    private String twitterUsername;

    @JsonProperty("tweet_link")
    private String tweetLink;

    @JsonProperty("reported_account_number")
    private String reportedAccountNumber;

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public void setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
    }

    public String getTweetLink() {
        return tweetLink;
    }

    public void setTweetLink(String tweetLink) {
        this.tweetLink = tweetLink;
    }

    public String getReportedAccountNumber() {
        return reportedAccountNumber;
    }

    public void setReportedAccountNumber(String reportedAccountNumber) {
        this.reportedAccountNumber = reportedAccountNumber;
    }
}
