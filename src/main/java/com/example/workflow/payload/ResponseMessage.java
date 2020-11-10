package com.example.workflow.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {
    private String message;
    private String code;
    private String traceId;
    private String type;
    private String origin;
    private String details;

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", traceId='" + traceId + '\'' +
                ", type='" + type + '\'' +
                ", origin='" + origin + '\'' +
                ", details='" + details + '\'' +
                ", data=" + data +
                '}';
    }

    private HashMap<String, Object> data;


    @NoArgsConstructor
    public static class ResponseBuilder {
        private String message;
        private String code;
        private String traceId;
        private String type;
        private String origin;
        private String details;

        private String timestamp;
        private HashMap<String, Object> data;


        public ResponseBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public ResponseBuilder setCode(String code) {
            this.code = code;
            return this;
        }

        public ResponseBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public ResponseBuilder setOrigin(String origin) {
            this.origin = origin;
            return this;
        }

        public ResponseBuilder setDetails(String details) {
            this.details = details;
            return this;
        }

        public ResponseBuilder setTraceId(String traceId) {
            this.traceId = traceId;
            return this;
        }

        public ResponseBuilder setTimeStamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ResponseBuilder setData(HashMap<String, Object> data) {
            this.data = data;
            return this;
        }

        public ResponseMessage build() {
            return new ResponseMessage(message, code, traceId, type, origin, details, data);

        }
    }
}
