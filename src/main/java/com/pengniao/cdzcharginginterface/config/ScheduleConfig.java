

package com.pengniao.cdzcharginginterface.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/* 功能描述: 定时任务配置
 * @param null
* @return:
* @Author: home
* @Date: 2020/8/27 9:02
*/
@Configuration
public class ScheduleConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {

        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(dataSource);

        //quartz参数
        Properties prop = new Properties();
        prop.put("org.quartz.scheduler.instanceName", "CdzChargingScheduler");
        //配置的名字，如果设置为AUTO,quartz会根据物理机名和当前时间产生一个名字
        prop.put("org.quartz.scheduler.instanceId", "AUTO");


        //线程池配置
        //线程池的实现类（一般使用SimpleThreadPool即可满足几乎所有用户的需求）
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        //指定线程数，至少为1（无默认值）(一般设置为1-100直接的整数合适)
        prop.put("org.quartz.threadPool.threadCount", "20");
        //设置线程的优先级（最大为java.lang.Thread.MAX_PRIORITY 10，最小为Thread.MIN_PRIORITY 1，默认为5）
        prop.put("org.quartz.threadPool.threadPriority", "5");



        //JobStore配置
        //保存job和Trigger的状态信息到数据库的类
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");


        //集群配置
        // 加入集群
//        prop.put("org.quartz.jobStore.isClustered", "true");
//        //调度实例失效的检查时间间隔
//        prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
//        //jobStore处理未按时触发的Job的数量
//        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
//        //容许的最大作业延长时间
//        prop.put("org.quartz.jobStore.misfireThreshold", "12000");
//        //表的前缀
//        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
//        //加锁的SQL语句，默认为SELECT * FROM {0}LOCKS WHERE LOCK_NAME = ? FOR UPDATE
//        //{0}=$@org.quartz.jobStore.tablePrefix
//        prop.put("org.quartz.jobStore.selectWithLockSQL", "SELECT * FROM {0}LOCKS UPDLOCK WHERE LOCK_NAME = ?");

        //PostgreSQL数据库，需要打开此注释
        //prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate");

        factory.setQuartzProperties(prop);

        factory.setSchedulerName("CdzChargingScheduler");
        //延时启动
        factory.setStartupDelay(30);
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        //可选，QuartzScheduler 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);
        //设置自动启动，默认为true
        factory.setAutoStartup(true);

        return factory;
    }
}
