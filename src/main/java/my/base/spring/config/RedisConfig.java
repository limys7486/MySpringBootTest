package my.base.spring.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
@PropertySource("application.properties")
public class RedisConfig {
  @Value("${spring.redis.host}")
  private String redisHost;

  @Value("${spring.redis.port}")
  private int redisPort;

  @Value("${spring.redis.password}")
  private String redisPassword;

  @Bean("jedisConnectionFactory")
  public JedisConnectionFactory jedisConnectionFactory()
  {
    JedisConnectionFactory factory = new JedisConnectionFactory(); factory.setHostName(redisHost);
    factory.setPort(redisPort); factory.setPassword(redisPassword);
    factory.setUsePool(true);

    return factory;
  }

  @Bean("stringRedisTemplate")
  public StringRedisTemplate stringRedisTemplate(@Qualifier("jedisConnectionFactory") JedisConnectionFactory jedisConnectionFactory)
  {
    StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();

    stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
    stringRedisTemplate.setValueSerializer(new StringRedisSerializer());

    stringRedisTemplate.setConnectionFactory(jedisConnectionFactory());

    return stringRedisTemplate;
  }

  @Bean
  public RedisTemplate<String , Object> redisTemplate()
  {
    RedisTemplate<String, Object> template = new RedisTemplate<>();

    template.setConnectionFactory(jedisConnectionFactory());

    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new StringRedisSerializer());

    template.setHashKeySerializer(new StringRedisSerializer());
    template.setHashValueSerializer(new StringRedisSerializer());

    template.setEnableDefaultSerializer(false);
    template.setEnableTransactionSupport(true);

    return template;
  }

    /*
  @Bean
  public RedisConnectionFactory redisConnectionFactory()
  {
    return new LettuceConnectionFactory(redisHost, redisPort);
  }

  @Bean
  public RedisTemplate<?, ?> redisTemplate()
  {
    RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());

    return redisTemplate;
  }
  */


 /*
    @Bean("messagePackRedisTemplate")
    public RedisTemplate<String, byte[]> messagePackRedisTemplate(@Qualifier("jedisConnectionFactory") JedisConnectionFactory jedisConnectionFactory)
    {
      RedisTemplate<String, byte[]> template = new RedisTemplate<>();
      template.setConnectionFactory(jedisConnectionFactory);
      template.setKeySerializer(new StringRedisSerializer());
      template.setEnableDefaultSerializer(false);

      return template;
    }

    @Bean("messagePackObjectMapper")
    public ObjectMapper messagePackObjectMapper()
    {
      return new ObjectMapper(new MessagePackFactory()).registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
*/
}