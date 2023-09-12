package com.deyaco.gateway.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 动态路由
 */

@Component
public class NacosDynamicRouteService implements ApplicationEventPublisherAware {
 
    private static final Logger LOGGER = LoggerFactory.getLogger(NacosDynamicRouteService.class);
 
    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;
 
    private ApplicationEventPublisher applicationEventPublisher;
 
    /** 路由id */
    private static List<String> routeIds = Lists.newArrayList();
 
    /**
     * @NacosConfigListener注解监听nacos路由配置 -> 动态改变路由
     * @param configInfo
     */
    @NacosConfigListener(dataId = "taixing-gateway", groupId = "taixing")
    public void routeConfigListener(String configInfo) {
        // 清空路由
        clearRoute();
        try {
            List<RouteDefinition> gatewayRouteDefinitions = JSON.parseArray(configInfo, RouteDefinition.class);
            for (RouteDefinition routeDefinition : gatewayRouteDefinitions) {
                addRoute(routeDefinition);
            }
            publish();
            LOGGER.info("Dynamic Routing Publish Success");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } 
    }
    /**
     * 清空路由
     */
    private void clearRoute() {
        for (String id : routeIds) {
            routeDefinitionWriter.delete(Mono.just(id)).subscribe();
        }
        routeIds.clear();
    }
 
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    /**
     * 添加路由
     * 
     * @param definition
     */
    private void addRoute(RouteDefinition definition) {
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            routeIds.add(definition.getId());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    /**
     * 发布路由、使路由生效
     */
    private void publish() {
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this.routeDefinitionWriter));
    }
}
