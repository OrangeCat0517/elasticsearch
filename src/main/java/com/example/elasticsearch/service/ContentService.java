package com.example.elasticsearch.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ContentService {
    boolean parseContent(String keyword) throws IOException;
    //这个方法根据keyword去爬虫，将结果放入ES中保存

    List<Map<String, Object>> searchPage(String keyword, int pageNo, int pageSize)
            throws IOException ;
    //按照关键字keyword分页去ES中进行查找

    List<Map<String, Object>> searchPageHighlightBuilder(String keyword, int pageNo, int pageSize)
            throws IOException;
    //将keyword关键字高亮显示
}
