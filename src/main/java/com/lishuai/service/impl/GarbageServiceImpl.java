package com.lishuai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.lishuai.entity.Classification;
import com.lishuai.entity.Garbage;
import com.lishuai.entity.vo.ClassificationVO;
import com.lishuai.mapper.ClassificationMapper;
import com.lishuai.mapper.GarbageMapper;
import com.lishuai.service.GarbageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Service
public class GarbageServiceImpl extends ServiceImpl<GarbageMapper, Garbage> implements GarbageService {

    @Autowired
    GarbageMapper garbageMapper;

    @Autowired
    ClassificationMapper classificationMapper;

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Override
    public List<ClassificationVO> garbageClassify(String garbageName) throws IOException {

        // 基本的条件搜索
        SearchRequest searchRequest = new SearchRequest("garbage");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        // 模糊匹配
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = QueryBuilders.matchPhraseQuery("garbageName", garbageName);
        sourceBuilder.query(matchPhraseQueryBuilder).timeout(new TimeValue(60, TimeUnit.SECONDS));

        // 搜索
        searchRequest.source(sourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : response.getHits()) {
            Map<String, Object> source = hit.getSourceAsMap();
            list.add(source);
        }

        List<ClassificationVO> result = new ArrayList<>();
        list.forEach(v->{
            ClassificationVO classificationVO =  new ClassificationVO();
            classificationVO.setGarbageName(v.get("garbageName").toString());
            Classification classification = classificationMapper.selectById(v.get("categoryId").toString());
            BeanUtil.copyProperties(classification, classificationVO);
            result.add(classificationVO);
        });
        return result;

//        List<ClassificationVO> result = new ArrayList<>();
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.like("garbage_name",garbageName);
//        List<Garbage> list = garbageMapper.selectList(queryWrapper);
//        list.forEach(v->{
//            ClassificationVO classificationVO =  new ClassificationVO();
//            classificationVO.setGarbageName(v.getGarbageName());
//            Classification classification = classificationMapper.selectById(v.getCategoryId());
//            BeanUtil.copyProperties(classification, classificationVO);
//            result.add(classificationVO);
//        });
//        return result;
    }

    @Override
    public List<Classification> classifyInfo() {
        return classificationMapper.selectList(null);
    }
}
