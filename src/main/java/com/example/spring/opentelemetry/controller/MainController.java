package com.example.spring.opentelemetry.controller;

import com.example.spring.opentelemetry.util.MyUtils;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.extension.annotations.SpanAttribute;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
public class MainController {
    @GetMapping(path = {"hello"}, produces = {MediaType.TEXT_PLAIN_VALUE})
    public String hello() {
        return "Hello, world!";
    }

    @GetMapping(path = {"s1_api"}, produces = {MediaType.TEXT_PLAIN_VALUE})
    public String api(@RequestParam("p") String queryParameter) {
        try {
            final String convertedP = MyUtils.convert(queryParameter);
            Span.current().setAttribute("convert_p", convertedP);
            Response rsp = Request.Post("http://localhost:8082/s2_api").bodyString("{\"k1\":\"v1\", \"k2\":\"v2\"}", ContentType.APPLICATION_JSON)
                                  .setHeader("myheader", "myValue").execute();
            System.out.println("s2_api rsp: " + rsp.returnResponse().getStatusLine());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to call s2_api");
        }
        return "s1_api";
    }

    @GetMapping(path = {"err"}, produces = {MediaType.TEXT_PLAIN_VALUE})
    public String err()
    {
        try {
            throw new IllegalArgumentException("Missing user ID");
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
