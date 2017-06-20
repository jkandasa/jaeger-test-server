package org.redhat.qe.jaeger.api.model;

import java.util.HashMap;
import java.util.Map;

import lombok.ToString;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TestData {
    private JaegerConfiguration config;
    private String serviceName;
    private Map<String, String> testParameters;

    public Map<String, String> getTestParameters() {
        if (testParameters != null) {
            return testParameters;
        }
        testParameters = new HashMap<String, String>();
        return testParameters;
    }
}
