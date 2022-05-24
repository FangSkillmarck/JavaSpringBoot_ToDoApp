package com.example.todo.request;

public class AddBookRequest {

    private String content;

    public AddBookRequest() {
    }

    public AddBookRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
