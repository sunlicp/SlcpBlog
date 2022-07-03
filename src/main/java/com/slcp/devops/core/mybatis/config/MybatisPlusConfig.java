package com.slcp.devops.core.mybatis.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.slcp.devops.core.mybatis.factory.YamlPropertySourceFactory;
import com.slcp.devops.core.mybatis.interceptor.SqlLogInterceptor;
import org.apache.ibatis.type.EnumTypeHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Slcp
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableTransactionManagement
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:/application-dev.yml")
@MapperScan("com.slcp.**.mapper.**")
public class MybatisPlusConfig {

    /**
     * 单页分页条数限制(默认无限制,参见 插件#handlerLimit 方法)
     */
    private static final Long MAX_LIMIT = 1000L;

    /**
     * sql 日志
     */
    @Bean
    @Profile({"local", "dev", "test"})
    @ConditionalOnProperty(value = "mybatis-plus.sql-log.enable", matchIfMissing = true)
    public SqlLogInterceptor sqlLogInterceptor() {
        return new SqlLogInterceptor();
    }


    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,
     * 需要设置 MybatisConfiguration#useDeprecatedExecutor = false
     * 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //分页插件: PaginationInnerInterceptor
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setMaxLimit(MAX_LIMIT);
        //防止全表更新与删除插件: BlockAttackInnerInterceptor
        BlockAttackInnerInterceptor blockAttackInnerInterceptor = new BlockAttackInnerInterceptor();
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        interceptor.addInnerInterceptor(blockAttackInnerInterceptor);
        return interceptor;
    }

    /**
     * IEnum 枚举配置
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
            configuration.setDefaultEnumTypeHandler(EnumTypeHandler.class);
            // 关闭 mybatis 默认的日志
            configuration.setLogPrefix("log.mybatis");
        };
    }

    /**
     * mybatis-plus 乐观锁拦截器
     */
    @Bean
    public OptimisticLockerInnerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInnerInterceptor();
    }
}
