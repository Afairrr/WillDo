package com.afair.common.utils;

import com.afair.pojo.entity.Location;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;

/**
 * @author WangBing
 * @date 2021/1/7 11:22
 */
@Slf4j
@Component
public class IPUtils {
    private static DbSearcher ipSearcher;

    @Value("${ip.db.path}")
    private String ipFilePath;

    @PostConstruct
    public void init() {
        try {
            DbConfig conf = new DbConfig();
            ipSearcher = new DbSearcher(conf, ipFilePath);
        } catch (FileNotFoundException | DbMakerConfigException e) {
            log.error("ip2region init fail", e);
        }
    }

    public Location ipLocation(String ip) {
        Location location = null;
        if (!Util.isIpAddress(ip)) {
            return null;
        }
        try {
            DataBlock block = ipSearcher.memorySearch(ip);
            String[] detail = block.getRegion().split("\\|");
            location = new Location();
            location.setCountry(detail[0]);
            location.setRegion(detail[1]);
            location.setProvince(detail[2]);
            location.setCity(detail[3]);
            location.setAddress(detail[4]);
        } catch (Exception e) {
            location = new Location();
            log.error("ip2region error", e);
        }
        return location;
    }
}
