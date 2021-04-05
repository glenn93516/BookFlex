package com.ssafy.backend.service;

import java.util.List;
import java.util.Map;

public interface CommunityService {
    List<Map> getMaxReadingBook() throws Exception;
    List<Map> getMaxGoodBook() throws Exception;
    List<Map> getMaxHighlightBook() throws Exception;
    List<Map> getMaxWishlistBook() throws Exception;
}
