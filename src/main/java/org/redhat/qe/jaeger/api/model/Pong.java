package org.redhat.qe.jaeger.api.model;

import lombok.ToString;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
@Getter
@Builder
@ToString
public class Pong {
    private String hostname;
    private String ip;
    private Long timestamp;
}
