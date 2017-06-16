package org.redhat.qe.jaeger.api.model.test;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Test {
    private String method;
    @JsonProperty("class")
    private String clazz;
    private Long startMillis;
    private Long endMillis;
    private Boolean success;
    private Object[] parameters;
    private Throwable throwable;
    private Integer status;
}
