package com.Spring.QuizService.model;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class Response {
    @NotBlank
    private int id;
    @NotBlank
    private String response;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Response(int id, String response) {
        this.id = id;
        this.response = response;
    }

    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", response='" + response + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response1 = (Response) o;
        return id == response1.id && Objects.equals(response, response1.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, response);
    }
}
