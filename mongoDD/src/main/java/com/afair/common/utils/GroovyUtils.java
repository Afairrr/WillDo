package com.afair.common.utils;

import com.afair.groovy.GroovyModuleRule;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WangBing
 * @date 2021/1/4 16:57
 */
@Slf4j
@Component
public class GroovyUtils {
    public static Map<String, GroovyObject> passedClassMap = new HashMap<>();

    public static GroovyClassLoader groovyClassLoader;

    public static String groovyTemplate;

    public static String PATH = "classpath*:groovy_template.groovy_template";

    static {
        //把类加载器绑定到spring启动上
        ClassLoader parent = AutowiredAnnotationBeanPostProcessor.class.getClassLoader();
        groovyClassLoader = new GroovyClassLoader(parent);
        //模板的提取
        initTemplate();
    }

    @SneakyThrows
    private static void initTemplate() {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Arrays.stream(resolver.getResources(PATH))
                .parallel()
                .forEach(resource -> {
                    try {
                        InputStream input = resource.getInputStream();
                        InputStreamReader reader = new InputStreamReader(input);
                        BufferedReader br = new BufferedReader(reader);
                        StringBuilder template = new StringBuilder();
                        for (String line; (line = br.readLine()) != null; ) {
                            template.append(line).append("\n");
                        }
                        groovyTemplate = template.toString();
                    } catch (Exception e) {
                        log.error("resolve file failed", e);
                    }
                });
    }

    /**
     * 包装script成一个类
     *
     * @param script 条件语句
     * @return
     */
    public static String packageGroovyScript(String script) {
        return String.format(groovyTemplate, script);
    }

    /**
     * 加载groovy脚本语言
     *
     * @param script
     * @return
     */
    public static GroovyModuleRule loadRuleScript(String script) {
        GroovyModuleRule obj = null;
        Class clazz = groovyClassLoader.parseClass(script);
        try {
            obj = (GroovyModuleRule) clazz.newInstance();
        } catch (IllegalAccessException e) {
            log.error("groovy实例化无法访问指定类异常", e);
        } catch (InstantiationException e) {
            log.error("groovy实例化异常", e);
        }
        return obj;
    }

    /**
     * 运行脚本
     *
     * @param object
     * @param arg
     * @return
     */
    public static Object invokeMethod(GroovyModuleRule object, Object arg) {
        return object.run(arg);
    }

    /**
     * 运行脚本
     *
     * @param script
     * @param arg
     * @return
     */
    public static Object invokeMethod(String script, Object arg) {
        GroovyModuleRule groovyModuleRule = loadRuleScript(script);
        if (groovyModuleRule != null) {
            return invokeMethod(groovyModuleRule, arg);
        } else {
            return null;
        }
    }
}