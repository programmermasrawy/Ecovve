package com.q8intouch.ecovve.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;

public class ApiError {
    public String error = "An error occurred";

    public ApiError(Throwable error) {
        if (error instanceof HttpException) {
            String errorJsonString = null;
            try {
                errorJsonString = ((HttpException)
                        error).response().errorBody().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JsonElement parsedString = new
                    JsonParser().parse(errorJsonString);
            this.error = parsedString.getAsJsonObject()
                    .get("error")
                    .getAsString();
        } else {
            this.error = error.getMessage() != null ? error.getMessage() : this.error;
        }
    }
}