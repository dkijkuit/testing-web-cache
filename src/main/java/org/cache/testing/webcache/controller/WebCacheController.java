package org.cache.testing.webcache.controller;

import lombok.RequiredArgsConstructor;
import org.cache.testing.webcache.annotation.PrintExecutionTime;
import org.cache.testing.webcache.model.CacheItem;
import org.cache.testing.webcache.model.HazelcastCacheItem;
import org.cache.testing.webcache.model.MemcachedCacheItem;
import org.cache.testing.webcache.model.RedisCacheItem;
import org.cache.testing.webcache.service.CacheService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/webcache/v1")
@RequiredArgsConstructor
public class WebCacheController {
    private final int maxCacheItems = 1_000_000;
    private final CacheService cacheService;

    @PrintExecutionTime
    @GetMapping
    Mono<List<CacheItem>> getAllCacheItems() {
        return cacheService.getAllCacheItems();
    }

    @GetMapping("clear")
    Mono<Void> clearAllCacheItems() {
        return cacheService.clearCache();
    }

    @PostMapping
    Mono<Integer> fillCache() {
        List<CacheItem> cacheItems = new ArrayList<>();
        for(int i=0; i<maxCacheItems; i++) {
            HazelcastCacheItem cacheItem = new HazelcastCacheItem();
            cacheItem.setId(UUID.randomUUID().toString());

            cacheItem.setStringItem0("stringItem0");
            cacheItem.setStringItem1("stringItem1");
            cacheItem.setStringItem2("stringItem2");
            cacheItem.setStringItem3("stringItem3");
            cacheItem.setStringItem4("stringItem4");
            cacheItem.setStringItem5("stringItem5");
            cacheItem.setStringItem6("stringItem6");
            cacheItem.setStringItem7("stringItem7");
            cacheItem.setStringItem8("stringItem8");
            cacheItem.setStringItem9("stringItem9");
            cacheItem.setStringItem10("stringItem10");
            cacheItem.setStringItem11("stringItem11");
            cacheItem.setStringItem12("stringItem12");
            cacheItem.setStringItem13("stringItem13");
            cacheItem.setStringItem14("stringItem14");
            cacheItem.setStringItem15("stringItem15");
            cacheItem.setStringItem16("stringItem16");
            cacheItem.setStringItem17("stringItem17");
            cacheItem.setStringItem18("stringItem18");
            cacheItem.setStringItem19("stringItem19");
            cacheItem.setDoubleItem0(0);
            cacheItem.setDoubleItem1(1);
            cacheItem.setDoubleItem2(2);
            cacheItem.setDoubleItem3(3);
            cacheItem.setDoubleItem4(4);
            cacheItem.setDoubleItem5(5);
            cacheItem.setDoubleItem6(6);
            cacheItem.setDoubleItem7(7);
            cacheItem.setDoubleItem8(8);
            cacheItem.setDoubleItem9(9);
            cacheItem.setDoubleItem10(10);
            cacheItem.setDoubleItem11(11);
            cacheItem.setDoubleItem12(12);
            cacheItem.setDoubleItem13(13);
            cacheItem.setDoubleItem14(14);
            cacheItem.setDoubleItem15(15);
            cacheItem.setDoubleItem16(16);
            cacheItem.setDoubleItem17(17);
            cacheItem.setDoubleItem18(18);
            cacheItem.setDoubleItem19(19);
            cacheItem.setIntItem0(0);
            cacheItem.setIntItem1(1);
            cacheItem.setIntItem2(2);
            cacheItem.setIntItem3(3);
            cacheItem.setIntItem4(4);
            cacheItem.setIntItem5(5);
            cacheItem.setIntItem6(6);
            cacheItem.setIntItem7(7);
            cacheItem.setIntItem8(8);
            cacheItem.setIntItem9(9);
            cacheItem.setIntItem10(10);
            cacheItem.setIntItem11(11);
            cacheItem.setIntItem12(12);
            cacheItem.setIntItem13(13);
            cacheItem.setIntItem14(14);
            cacheItem.setIntItem15(15);
            cacheItem.setIntItem16(16);
            cacheItem.setIntItem17(17);
            cacheItem.setIntItem18(18);
            cacheItem.setIntItem19(19);
            cacheItem.setUuidItem0(UUID.randomUUID());
            cacheItem.setUuidItem1(UUID.randomUUID());
            cacheItem.setUuidItem2(UUID.randomUUID());
            cacheItem.setUuidItem3(UUID.randomUUID());
            cacheItem.setUuidItem4(UUID.randomUUID());
            cacheItem.setUuidItem5(UUID.randomUUID());
            cacheItem.setUuidItem6(UUID.randomUUID());
            cacheItem.setUuidItem7(UUID.randomUUID());
            cacheItem.setUuidItem8(UUID.randomUUID());
            cacheItem.setUuidItem9(UUID.randomUUID());
            cacheItem.setUuidItem10(UUID.randomUUID());
            cacheItem.setUuidItem11(UUID.randomUUID());
            cacheItem.setUuidItem12(UUID.randomUUID());
            cacheItem.setUuidItem13(UUID.randomUUID());
            cacheItem.setUuidItem14(UUID.randomUUID());
            cacheItem.setUuidItem15(UUID.randomUUID());
            cacheItem.setUuidItem16(UUID.randomUUID());
            cacheItem.setUuidItem17(UUID.randomUUID());
            cacheItem.setUuidItem18(UUID.randomUUID());
            cacheItem.setUuidItem19(UUID.randomUUID());

            cacheItems.add(cacheItem);
        }

        return cacheService.insertCacheItems(cacheItems);
    }
}
