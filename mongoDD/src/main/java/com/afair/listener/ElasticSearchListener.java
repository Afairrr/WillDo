package com.afair.listener;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.springframework.stereotype.Component;

/**
 * @author WangBing
 * @date 2020/12/26 14:13
 */
@Slf4j
@Component
public class ElasticSearchListener implements ActionListener<IndexResponse> {
    @Override
    public void onResponse(IndexResponse indexResponse) {
        String index = indexResponse.getIndex();
        String id = indexResponse.getId();
        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
            log.info("创建成功{},id为{}", index, id);
        } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            log.info("更新成功{},id为{}", index, id);
        }
        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            log.info("有失败操作,总操作为{},成功操作为{}", shardInfo.getTotal(), shardInfo.getSuccessful());
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                String reason = failure.reason();
                log.info("错误原因{}", reason);
            }
        }
    }

    @Override
    public void onFailure(Exception e) {
        log.info("异常抛出{}", e);
    }
}
