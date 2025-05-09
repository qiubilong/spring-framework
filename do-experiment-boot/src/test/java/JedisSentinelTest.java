import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class JedisSentinelTest {
	
    public static void main(String[] args) throws IOException {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        config.setMinIdle(5);

        String masterName = "mymaster";
        Set<String> sentinels = new HashSet<String>();
        sentinels.add(new HostAndPort("192.168.229.130",26379).toString());
        sentinels.add(new HostAndPort("192.168.229.132",26379).toString());
        sentinels.add(new HostAndPort("192.168.229.133",26379).toString());
        //JedisSentinelPool其实本质跟JedisPool类似，都是与redis主节点建立的连接池
        //JedisSentinelPool并不是说与sentinel建立的连接池，而是通过sentinel发现redis主节点并与其建立连接
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName, sentinels, config, 3000, null);
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            System.out.println(jedis.set("sentinel", "zhuge"));
            System.out.println(jedis.get("sentinel"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //注意这里不是关闭连接，在JedisPool模式下，Jedis会被归还给资源池。
            if (jedis != null)
                jedis.close();
        }

		testMasterDown(jedisSentinelPool);

    }

	public static void testMasterDown(JedisSentinelPool jedisSentinelPool){
		int i = 0;
		Jedis jedis = null;
		while (true){
			try {
				jedis = jedisSentinelPool.getResource();
				jedis.set("zhuge"+i, i+"");
				System.out.println("设置key："+ "zhuge" + i);
				i++;
				Thread.sleep(1000);
			}catch (Exception e){
				log.error("错误：", e);
			} finally {
				//注意这里不是关闭连接，在JedisPool模式下，Jedis会被归还给资源池。
				if (jedis != null)
					try {
						jedis.close();
					}catch (Exception ee){}
			}
		}
	}
}