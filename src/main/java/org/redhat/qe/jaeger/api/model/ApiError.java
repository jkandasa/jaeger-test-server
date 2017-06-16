package org.redhat.qe.jaeger.api.model;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
@Getter
@Builder
public class ApiError {
    private String code;
    private String error;
}
