package com.bloomreach.hotswap;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

@Component
public class Scheduler implements SchedulingConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    private HotswapWorker hotswapWorker;

    @Autowired
    public Scheduler(final HotswapWorker hotswapWorker) {
        this.hotswapWorker = hotswapWorker;
    }

    @SuppressWarnings("ContextJavaBeanUnresolvedMethodsInspection")
    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(100);
    }

    @Override
    public void configureTasks(final ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
        taskRegistrar.addTriggerTask(
                hotswapWorker::onScheduledTrigger,
                triggerContext -> {
                    Calendar nextExecutionTime = new GregorianCalendar();
                    Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
                    nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
                    nextExecutionTime.add(Calendar.MILLISECOND, 5000);
                    return nextExecutionTime.getTime();
                }
        );
    }
}
