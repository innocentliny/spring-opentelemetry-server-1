package com.example.spring.opentelemetry.util;

import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.api.baggage.BaggageBuilder;
import io.opentelemetry.api.baggage.BaggageEntryMetadata;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.extension.annotations.SpanAttribute;
import io.opentelemetry.extension.annotations.WithSpan;

import java.util.concurrent.TimeUnit;

public class MyUtils {
    private MyUtils(){}

    @WithSpan
    public static String convert(@SpanAttribute("p") String p) {
        if(p == null)
            return null;
        try {
            Span.current().addEvent("Calculating", Attributes.builder().put("convert_k_1", "convert_v_1").put("convert_k_2", "convert_v_2").build());
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Span.current().addEvent("Calculated", Attributes.of(AttributeKey.stringKey("return"), "converted_p"));
        return "converted_p";
    }
}
