package com.example.projetappel.util;

import com.example.projetappel.enumtype.ResponseType;

public class ResponseJSON {

    private ResponseType responseType;
    private Object body;

    public ResponseJSON() {}

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

}
