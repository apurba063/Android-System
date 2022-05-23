package edu.ewubd.assignment3;

public class ImageModelClass {
    String URL;
    String Detail;

    public ImageModelClass(String URL, String detail) {
        this.URL = URL;
        Detail = detail;
    }

    public ImageModelClass() {
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }
}
