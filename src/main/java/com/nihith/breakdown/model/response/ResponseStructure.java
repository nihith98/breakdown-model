package com.nihith.breakdown.model.response;


public class ResponseStructure {

    private ResponseStatus responseStatus;
    private ResponseMessages messages;
    private Object payload;

    public ResponseStatus getStatus() {
        return responseStatus;
    }

    public void setStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public ResponseMessages getMessages() {
        return messages;
    }

    public void setMessages(ResponseMessages messages) {
        this.messages = messages;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "ResponseStructure{" +
                "status=" + responseStatus +
                ", messages=" + messages +
                ", payload=" + payload +
                '}';
    }
}
