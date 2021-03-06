package com.code.monitor.cronjob.controller;

import com.code.monitor.cronjob.job.TimedThread;
import com.code.monitor.cronjob.worker.CornJobWorker;
import com.code.monitor.mq.rabbit.config.RabbitConfig;
import com.code.monitor.properties.PropertiesConfig;
import com.code.monitor.properties.constant.ThreadPoolConstant;

import java.util.HashMap;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/1/27 17:32
 */
public class ThreadPoolController {

    static {
        init();
    }

    private static void init() {
        HashMap<String, String> configMap = PropertiesConfig.configMap;
        boolean aTrue = Boolean.parseBoolean(configMap.getOrDefault(ThreadPoolConstant.STATUS, "true"));
        TimedThread timedThread = new TimedThread();
        timedThread.init();
        CornJobWorker.setRunnable(timedThread);
        CornJobWorker.start(aTrue);
    }

    public ThreadPoolController() {
        // HashMap<String, String> configMap = PropertiesConfig.configMap;
        // boolean aTrue = Boolean.parseBoolean(configMap.getOrDefault(ThreadPoolConstant.STATUS, "true"));
        // TimedThread timedThread = new TimedThread();
        // timedThread.init();
        // CornJobWorker.setRunnable(timedThread);
        // CornJobWorker.start(aTrue);
    }

    public void shutdown() {
        RabbitConfig.rabbitConfig.shutdown();
        CornJobWorker.shutdown();
    }
}
