package com.murdock.examples.dropwizard;

import com.murdock.examples.dropwizard.resources.CoinCalculatorResource;
import com.murdock.examples.dropwizard.resources.HolaRestResourceV1;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class HolaDropwizardApplication extends Application<HolaDropwizardConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HolaDropwizardApplication().run(args);
    }

    @Override
    public String getName() {
        return "HolaDropwizard";
    }

    @Override
    public void initialize(final Bootstrap<HolaDropwizardConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final HolaDropwizardConfiguration configuration,
                    final Environment environment) {
        // 注册 CORS 过滤器
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // 配置 CORS 允许的参数
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");

        // 设置过滤器生效范围
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        environment.jersey().register(new HolaRestResourceV1());
        environment.jersey().register(new CoinCalculatorResource());
    }

}
