package org.apache.dubbo.demo.provider.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Activate(group = "provider")
public class TraceFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TraceFilter.class);
    private static String TRACE_KEY = "traceId";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String key = String.format("%s-%s", invoker.getInterface().getName(), invocation.getMethodName());
        LOGGER.info("monitor key", key);
        Result result = new AppResponse();
        try {
            if (isConsumer()) {
                addTraceInfoToProvider();
            } else {
                addTraceInfoFromConsumer();
            }
        } catch (Exception e) {
            LOGGER.error("dubbo invoker failed: {}", e.getMessage());
        }
        try {
            result = invoker.invoke(invocation);
        } catch (RpcException e) {
            if (e.isTimeout()) {
                //超时异常只记录监控
                LOGGER.error("dubbo出现了超时异常", e);
            } else {
                throw e;
            }
        } finally {
            if (result != null
                    && result.getException() instanceof RuntimeException
                    && result.getException().getCause() != null
                    && result.getException().getCause() instanceof InterruptedException) {
                //dubbo中断异常
                LOGGER.error("dubbo出现中断异常了", result.getException().getCause());
                result = new AppResponse();
            }
        }
        return result;
    }

    /**
     * 是否consumer
     *
     * @return
     */
    private boolean isConsumer() {
        return RpcContext.getContext().isConsumerSide();
    }

    /**
     * consumer调用此方法
     * 把TraceUtils中的信息添加到dubbo的header中，供provider使用
     */
    private void addTraceInfoToProvider() {
        RpcContext.getContext().setAttachment(TRACE_KEY, "testKey");
    }

    /**
     * provider调用此方法
     * 把header中的traceUtils放入
     */
    private void addTraceInfoFromConsumer() {
        Map<String, String> attachments = RpcContext.getContext().getAttachments();
        if (attachments == null || !attachments.containsKey(TRACE_KEY)) {
            return;
        }
        String traceInfo = attachments.get(TRACE_KEY);
        System.out.println("provider 获得 traceId" + traceInfo);
    }
    @SuppressWarnings("uncheck")
    public static Set union(Set s1, Set s2) {
        Set result = new HashSet(s1);
        result.addAll(s2);
        return result;
    }
}
