package org.redhat.qe.jaeger.api.model;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
@Getter
@Builder
public class Pong {
    private String hostname;
    private Long timestamp;
}
