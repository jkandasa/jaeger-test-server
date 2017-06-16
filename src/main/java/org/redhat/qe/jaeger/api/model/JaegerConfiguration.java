package org.redhat.qe.jaeger.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JaegerConfiguration {
    private String serverHost;
    private Integer serverPort;
    private JAEGER_AGENT agentType;
    private String agentHost;
    private Integer agentPort;
    private Integer flushInterval;

    public JAEGER_AGENT getAgentType() {
        return agentType != null ? agentType : JAEGER_AGENT.JAEGER_BINARY;
    }

    public Integer getAgentPort() {
        return agentPort != null ? agentPort : 5775;
    }

    public Integer getServerPort() {
        return serverPort != null ? serverPort : 16686;
    }

    public String getServerHost() {
        return serverHost != null ? serverHost : "localhost";
    }

    public String getAgentHost() {
        return agentHost != null ? agentHost : "localhost";
    }

    public Integer getFlushInterval() {
        return flushInterval != null ? flushInterval : 100;
    }
}