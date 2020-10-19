package club.sdll.ptc.juc.threadlocal;

import lombok.Data;

/**
 * description
 *
 * @author shandianlala@gmail.com
 * @version 1.0
 * @date 2020-10-19 20:20
 */
public class TraceContext {

    private String traceId;

    private String method;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
