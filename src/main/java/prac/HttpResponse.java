package prac;

public class HttpResponse {
    private int status;
    private String responseBody;
    public HttpResponse(int status, String responseBody) {
        this.status = status;
        this.responseBody = responseBody;
    }
}
