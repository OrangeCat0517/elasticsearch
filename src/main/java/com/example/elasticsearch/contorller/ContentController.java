package com.example.elasticsearch.contorller;

import com.example.elasticsearch.service.ContentService;
import com.example.elasticsearch.service.impl.ContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class ContentController {
    @Autowired
    private ContentService contentService;

    //http://localhost:8080/search/java/1/30
    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
    @CrossOrigin
    public List<Map<String, Object>> search(
            @PathVariable("keyword") String keyword,
            @PathVariable("pageNo") int pageNo,
            @PathVariable("pageSize") int pageSize) throws IOException {
        return contentService.searchPageHighlightBuilder(keyword, pageNo, pageSize);
    }

}
