package com.liujiajun.config;

import com.liujiajun.controller.converter.CustomDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.Ordered;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashSet;
import java.util.Set;

@Configuration // 标记这是一个配置类，Spring会将其作为配置文件加载到容器中
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 配置全局日期转换器。
     * Spring MVC 默认的 Converter 机制可以用来处理请求参数和返回值的类型转换。
     * 这里我们通过自定义的 CustomDateConverter 来实现日期格式的转换。
     *
     * @param customDateConverter 自定义的日期转换器（CustomDateConverter）
     * @return ConversionService 实例，用于管理所有的类型转换器
     */
    @Bean // 将该方法返回的对象注册为Spring容器中的一个Bean
    @Autowired // 自动注入 CustomDateConverter 实例
    public ConversionService getConversionService(CustomDateConverter customDateConverter) {
        // 创建 ConversionServiceFactoryBean 实例，用于生成 ConversionService
        ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();

        // 创建一个存储 Converter 的集合
        Set<Converter> converters = new HashSet<>();

        // 将自定义的日期转换器添加到集合中
        converters.add(customDateConverter);

        // 将转换器集合设置到工厂Bean中
        factoryBean.setConverters(converters);

        // 返回生成的 ConversionService 实例
        return factoryBean.getObject();
    }

    /**
     * 设置访问默认首页。
     * 通过重写 addViewControllers 方法，我们可以配置默认的视图映射规则。
     * 例如，当用户访问根路径 "/" 时，自动跳转到 index.jsp 页面。
     *
     * @param registry ViewControllerRegistry 实例，用于注册视图控制器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 添加一个视图控制器，将根路径 "/" 映射到 "index.jsp" 页面
        registry.addViewController("/").setViewName("forward:index.jsp");

        // 设置视图控制器的优先级为最高（Ordered.HIGHEST_PRECEDENCE）
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        // 调用父类的方法（可选，确保其他默认配置生效）
        super.addViewControllers(registry);
    }
}