package paf.day22.model.exception;

import java.util.Date;

public class ApiError {
    private int status;
    private String message;
    private Date timestamp;
    private String url;

    public ApiError() {}
    public ApiError(int status, String message, Date timestamp, String url) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.url = url;
    }

    @Override
    public String toString() {
        return "ApiError [status=" + status + ", message=" + message + ", timestamp=" + timestamp + ", url=" + url + "]";
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}