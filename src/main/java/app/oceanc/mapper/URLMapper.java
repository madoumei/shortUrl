package app.oceanc.mapper;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author bryan
 * @version 1.0
 * @description: URLMapper
 * @date 2022/5/30 9:47 PM
 */
@Repository
@SuppressWarnings("unused")
public class URLMapper {
    private final String idKey;
    private final String urlKey;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(URLMapper.class);

    public URLMapper() {
        this.idKey = "id";
        this.urlKey = "url:";
    }

    public URLMapper(String idKey, String urlKey) {
        this.idKey = idKey;
        this.urlKey = urlKey;
    }

    public Long incrementID() {

        Long id = stringRedisTemplate.opsForValue().increment(idKey);
        LOGGER.info("Incrementing ID: {}", id - 1);
        return id - 1;
    }

    public void saveUrl(String key, String longUrl) {
        LOGGER.info("Saving: {} at {}", longUrl, key);
        stringRedisTemplate.opsForHash().put(urlKey, key, longUrl);
    }

    public String getUrl(Long id) throws Exception {
        LOGGER.info("Retrieving at {}", id);
        String url = String.valueOf(stringRedisTemplate.opsForHash().get(urlKey, "url:" + id));
        if (url == null) {
            throw new Exception("URL at key" + id + " does not exist");
        }
        return String.valueOf(stringRedisTemplate.opsForHash().get(urlKey, "url:" + id));
    }
}
