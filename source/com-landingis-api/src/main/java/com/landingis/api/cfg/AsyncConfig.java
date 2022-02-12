/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.landingis.api.cfg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

@Configuration
@Slf4j
public class AsyncConfig implements AsyncConfigurer {

    @Value("${thread.pool.size}")
    private Integer threadPoolSize;

    @Value("${thread.pool.queue.size}")
    private Integer threadQueuePoolSize;

    @Bean(name = "threadPoolExecutor")
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadPoolSize);
        executor.setMaxPoolSize(threadPoolSize);
        executor.setQueueCapacity(threadQueuePoolSize);
        executor.setThreadNamePrefix("dvms-springboot-pool-");
        executor.initialize();
        return new DelegatingSecurityContextAsyncTaskExecutor(executor);
    }

    /**
     *
     * ThreadPoolExecutor và ThreadPoolTaskExecutor cũng là Executor nhưng nó có thêm các tham số như sau:
     *
     * corePoolSize: Số lượng Thread mặc định trong Pool
     * maxPoolSize: Số lượng tối đa Thread trong Pool
     * queueCapacity: Số lượng tối da của BlockingQueue
     * # Nguyên tắc vận hành
     *
     * Ví dụ với ThreadPoolExecutor có:
     *
     * corePoolSize: 5
     * maxPoolSize: 15
     * queueCapacity: 100
     *
     * Khi có request, nó sẽ tạo trong Pool tối đa 5 thread (corePoolSize).
     * Khi số lượng thread vượt quá 5 thread. Nó sẽ cho vào hàng đợi.
     * Khi số lượng hàng đợi full 100 (queueCapacity). Lúc này mới bắt đầu tạo thêm Thread mới.
     * Số thread mới được tạo tối đa là 15 (maxPoolSize).
     * Khi Request vượt quá số lượng 15 thread. Request sẽ bị từ chối!
     *
     *
     * */
}
