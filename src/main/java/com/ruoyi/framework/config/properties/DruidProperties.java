package com.ruoyi.framework.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.pool.DruidDataSource;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * druid 配置属性
 *
 * @author ruoyi
 */
@Configuration
public class DruidProperties {
    @Value("${spring.datasource.druid.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.druid.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.druid.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.druid.maxWait}")
    private int maxWait;

//    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
//    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.druid.maxEvictableIdleTimeMillis}")
    private int maxEvictableIdleTimeMillis;

    @Value("${spring.datasource.druid.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.druid.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.druid.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.druid.testOnReturn}")
    private boolean testOnReturn;

    public DruidDataSource dataSource(DruidDataSource datasource) {
        /** 配置初始化大小、最小、最大 */
        datasource.setInitialSize(initialSize);
        datasource.setMaxActive(maxActive);
        datasource.setMinIdle(minIdle);

        /** 配置获取连接等待超时的时间 */
        datasource.setMaxWait(maxWait);

        /** 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 */
//        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);˙

        /** 配置一个连接在池中最小、最大生存的时间，单位是毫秒 */
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);

        /**
         * 用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。
         */
        datasource.setValidationQuery(validationQuery);
        /** 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。 */
        datasource.setTestWhileIdle(testWhileIdle);
        /** 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 */
        datasource.setTestOnBorrow(testOnBorrow);
        /** 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 */
        datasource.setTestOnReturn(testOnReturn);
        return datasource;
    }

    static double y = 10; //最终值
    static double avgErrVl = 0.8;//误差范围


    public static void main(String[] args) {
        BigDecimal x = new BigDecimal(-0.001);

        BigDecimal v = execVal(x);
        System.out.println("final:"+v);
        System.out.println("final2:"+finalVal(v));



    }

    static BigDecimal execVal(BigDecimal x) {
        try {
            BigDecimal val1 = new BigDecimal(0.0003).multiply(x).multiply(x).multiply(x).multiply(x).setScale(0,BigDecimal.ROUND_UP);
            BigDecimal val2 = new BigDecimal(0.0062).multiply(x).multiply(x).multiply(x).setScale(0,BigDecimal.ROUND_UP);
            BigDecimal val3 = new BigDecimal(0.0484).multiply(x).multiply(x).setScale(0,BigDecimal.ROUND_UP);
            BigDecimal val4 = new BigDecimal(0.1614).multiply(x).setScale(0,BigDecimal.ROUND_UP);
            BigDecimal val5 = new BigDecimal(0.2006).setScale(0,BigDecimal.ROUND_UP);
            BigDecimal finalVal = val1.subtract(val2).add(val3).subtract(val4).add(val5).setScale(0,BigDecimal.ROUND_UP);
            BigDecimal multiply1 = finalVal.subtract(new BigDecimal(y)).setScale(0,BigDecimal.ROUND_UP);
            int i = multiply1.setScale(0,BigDecimal.ROUND_UP).abs().compareTo(new BigDecimal(avgErrVl));
            while (i>0){
               return execVal(x .add(new BigDecimal(1))).setScale(0,BigDecimal.ROUND_UP);
            }
//            double execVal = 0.0003 * Math.pow(x, 4) - 0.0062 * Math.pow(x, 3) + 0.0484 * Math.pow(x, 2) - 0.1614 * x + 0.2006;
            return x;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static BigDecimal finalVal(BigDecimal x){
        BigDecimal val1 = new BigDecimal(0.0003).multiply(x).multiply(x).multiply(x).multiply(x).setScale(0,BigDecimal.ROUND_UP);
        BigDecimal val2 = new BigDecimal(0.0062).multiply(x).multiply(x).multiply(x).setScale(0,BigDecimal.ROUND_UP);
        BigDecimal val3 = new BigDecimal(0.0484).multiply(x).multiply(x).setScale(0,BigDecimal.ROUND_UP);
        BigDecimal val4 = new BigDecimal(0.1614).multiply(x).setScale(0,BigDecimal.ROUND_UP);
        BigDecimal val5 = new BigDecimal(0.2006).setScale(0,BigDecimal.ROUND_UP);
        BigDecimal finalVal = val1.subtract(val2).add(val3).subtract(val4).add(val5).setScale(0,BigDecimal.ROUND_UP);
        return finalVal;
    }
}
