package com.shopping.server.auth.datasource;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shopping.monitor.MBeanFactory;
import com.shopping.monitor.mbean.ShoppingCommonMonitorMBean;
import com.shopping.monitor.utils.Constant;
import com.shopping.server.auth.redis.clients.jedis.BinaryJedisPubSub;
import com.shopping.server.auth.redis.clients.jedis.Client;
import com.shopping.server.auth.redis.clients.jedis.DebugParams;
import com.shopping.server.auth.redis.clients.jedis.Jedis;
import com.shopping.server.auth.redis.clients.jedis.JedisCommands;
import com.shopping.server.auth.redis.clients.jedis.JedisMonitor;
import com.shopping.server.auth.redis.clients.jedis.JedisPool;
import com.shopping.server.auth.redis.clients.jedis.JedisPubSub;
import com.shopping.server.auth.redis.clients.jedis.Pipeline;
import com.shopping.server.auth.redis.clients.jedis.PipelineBlock;
import com.shopping.server.auth.redis.clients.jedis.SortingParams;
import com.shopping.server.auth.redis.clients.jedis.Transaction;
import com.shopping.server.auth.redis.clients.jedis.TransactionBlock;
import com.shopping.server.auth.redis.clients.jedis.Tuple;
import com.shopping.server.auth.redis.clients.jedis.ZParams;
import com.shopping.server.auth.redis.clients.jedis.BinaryClient.LIST_POSITION;
import com.shopping.server.auth.redis.clients.util.Slowlog;

public class JedisTemplate implements JedisCommands
{

	JedisPool pool;

	private ShoppingCommonMonitorMBean bm = MBeanFactory.getBaikuCommonMonitor();

	private RedisDataSource redisDataSource;

	public RedisDataSource getRedisDataSource()
	{
		return redisDataSource;
	}

	public void setRedisDataSource(RedisDataSource redisDataSource)
	{
		this.redisDataSource = redisDataSource;
	}

	public JedisTemplate()
	{
	}

	public void init()
	{
		RedisDataSource r = getRedisDataSource();
		pool = new JedisPool(r.getConfig(), r.getIp(), r.getPort(),
				r.getConOuttime(), r.getPassword(), r.getDatabase());
	}

	public String ping() throws Exception
	{
		Jedis jedis = pool.getResource();
		try
		{
			return jedis.ping();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			throw e;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public String set(String key, String value)
	{
		Jedis jedis = pool.getResource();
		try
		{
			return jedis.set(key, value);
		}
		catch (Exception e)
		{
			warning(Constant.FATAL, e);
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public String get(String key)
	{
		Jedis jedis = pool.getResource();
		try
		{
			return jedis.get(key);
		}
		catch (Exception e)
		{
			warning(Constant.FATAL, e);
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String quit()
	{
		Jedis jedis = pool.getResource();
		try
		{
			return jedis.quit();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Boolean exists(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.exists(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long del(String... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.del(keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public String type(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.type(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String flushDB()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.flushDB();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<String> keys(String pattern)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.keys(pattern);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String randomKey()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.randomKey();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String rename(String oldkey, String newkey)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.rename(oldkey, newkey);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long renamenx(String oldkey, String newkey)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.renamenx(oldkey, newkey);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long expire(String key, int seconds)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.expire(key, seconds);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long expireAt(String key, long unixTime)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.expireAt(key, unixTime);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long ttl(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.ttl(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String select(int index)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.select(index);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long move(String key, int dbIndex)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.move(key, dbIndex);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String flushAll()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.flushAll();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public String getSet(String key, String value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.getSet(key, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<String> mget(String... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.mget(keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long setnx(String key, String value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.setnx(key, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public String setex(String key, int seconds, String value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.setex(key, seconds, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String mset(String... keysvalues)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.mset(keysvalues);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long msetnx(String... keysvalues)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.msetnx(keysvalues);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long decrBy(String key, long integer)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.decrBy(key, integer);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long decr(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.decr(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long incrBy(String key, long integer)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.incrBy(key, integer);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long incr(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.incr(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long append(String key, String value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.append(key, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public String substr(String key, int start, int end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.substr(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long hset(String key, String field, String value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hset(key, field, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public String hget(String key, String field)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hget(key, field);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long hsetnx(String key, String field, String value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hsetnx(key, field, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public String hmset(String key, Map<String, String> hash)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hmset(key, hash);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public List<String> hmget(String key, String... fields)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hmget(key, fields);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long hincrBy(String key, String field, long value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hincrBy(key, field, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Boolean hexists(String key, String field)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hexists(key, field);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long hdel(String key, String... fields)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hdel(key, fields);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long hlen(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hlen(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<String> hkeys(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hkeys(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public List<String> hvals(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hvals(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Map<String, String> hgetAll(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hgetAll(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long rpush(String key, String... strings)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.rpush(key, strings);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long lpush(String key, String... strings)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.lpush(key, strings);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long llen(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.llen(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public List<String> lrange(String key, long start, long end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.lrange(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public String ltrim(String key, long start, long end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.ltrim(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public String lindex(String key, long index)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.lindex(key, index);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public String lset(String key, long index, String value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.lset(key, index, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long lrem(String key, long count, String value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.lrem(key, count, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public String lpop(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.lpop(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public String rpop(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.rpop(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String rpoplpush(String srckey, String dstkey)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.rpoplpush(srckey, dstkey);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long sadd(String key, String... members)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sadd(key, members);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<String> smembers(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.smembers(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long srem(String key, String... members)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.srem(key, members);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public String spop(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.spop(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long smove(String srckey, String dstkey, String member)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.smove(srckey, dstkey, member);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long scard(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.scard(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Boolean sismember(String key, String member)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sismember(key, member);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<String> sinter(String... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sinter(keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long sinterstore(String dstkey, String... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sinterstore(dstkey, keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<String> sunion(String... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sunion(keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long sunionstore(String dstkey, String... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sunionstore(dstkey, keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<String> sdiff(String... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sdiff(keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long sdiffstore(String dstkey, String... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sdiffstore(dstkey, keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public String srandmember(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.srandmember(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long zadd(String key, double score, String member)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zadd(key, score, member);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long zadd(String key, Map<Double, String> scoreMembers)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zadd(key, scoreMembers);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<String> zrange(String key, long start, long end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrange(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long zrem(String key, String... members)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrem(key, members);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Double zincrby(String key, double score, String member)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zincrby(key, score, member);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long zrank(String key, String member)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrank(key, member);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long zrevrank(String key, String member)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrank(key, member);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<String> zrevrange(String key, long start, long end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrange(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<Tuple> zrangeWithScores(String key, long start, long end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrangeWithScores(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<Tuple> zrevrangeWithScores(String key, long start, long end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrangeWithScores(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long zcard(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zcard(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Double zscore(String key, String member)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zscore(key, member);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String watch(String... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.watch(keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public List<String> sort(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sort(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public List<String> sort(String key, SortingParams sortingParameters)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sort(key, sortingParameters);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<String> blpop(int timeout, String... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.blpop(timeout, keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long sort(String key, SortingParams sortingParameters, String dstkey)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sort(key, sortingParameters, dstkey);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long sort(String key, String dstkey)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sort(key, dstkey);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<String> brpop(int timeout, String... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.brpop(timeout, keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String auth(String password)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.auth(password);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public void subscribe(JedisPubSub jedisPubSub, String... channels)
	{
		Jedis jedis = pool.getResource();
		try
		{
			jedis.subscribe(jedisPubSub, channels);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long publish(String channel, String message)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.publish(channel, message);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public void psubscribe(JedisPubSub jedisPubSub, String... patterns)
	{
		Jedis jedis = pool.getResource();
		try
		{
			jedis.psubscribe(jedisPubSub, patterns);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long zcount(String key, double min, double max)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zcount(key, min, max);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long zcount(String key, String min, String max)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zcount(key, min, max);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrangeByScore(key, min, max);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<String> zrangeByScore(String key, String min, String max)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrangeByScore(key, min, max);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max,
			int offset, int count)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrangeByScore(key, min, max, offset, count);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<String> zrangeByScore(String key, String min, String max,
			int offset, int count)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrangeByScore(key, min, max, offset, count);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrangeByScoreWithScores(key, min, max);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrangeByScoreWithScores(key, min, max);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min,
			double max, int offset, int count)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min,
			String max, int offset, int count)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrangeByScore(key, max, min);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrangeByScore(key, max, min);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min,
			int offset, int count)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrangeByScore(key, max, min, offset, count);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max,
			double min)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrangeByScoreWithScores(key, max, min);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max,
			double min, int offset, int count)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrangeByScoreWithScores(key, max, min, offset,
					count);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max,
			String min, int offset, int count)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrangeByScoreWithScores(key, max, min, offset,
					count);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min,
			int offset, int count)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrangeByScore(key, max, min, offset, count);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max,
			String min)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrangeByScoreWithScores(key, max, min);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long zremrangeByRank(String key, long start, long end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zremrangeByRank(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long zremrangeByScore(String key, double start, double end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zremrangeByScore(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long zremrangeByScore(String key, String start, String end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zremrangeByScore(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long zunionstore(String dstkey, String... sets)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zunionstore(dstkey, sets);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long zunionstore(String dstkey, ZParams params, String... sets)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zunionstore(dstkey, params, sets);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long zinterstore(String dstkey, String... sets)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zinterstore(dstkey, sets);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long zinterstore(String dstkey, ZParams params, String... sets)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zinterstore(dstkey, params, sets);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long strlen(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.strlen(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long lpushx(String key, String string)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.lpushx(key, string);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long persist(String key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.persist(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long rpushx(String key, String string)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.rpushx(key, string);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String echo(String string)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.echo(string);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long linsert(String key, LIST_POSITION where, String pivot,
			String value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.linsert(key, where, pivot, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String brpoplpush(String source, String destination, int timeout)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.brpoplpush(source, destination, timeout);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Boolean setbit(String key, long offset, boolean value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.setbit(key, offset, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Boolean getbit(String key, long offset)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.getbit(key, offset);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public Long setrange(String key, long offset, String value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.setrange(key, offset, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public String getrange(String key, long startOffset, long endOffset)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.getrange(key, startOffset, endOffset);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<String> configGet(String pattern)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.configGet(pattern);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String configSet(String parameter, String value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.configSet(parameter, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Object eval(String script, int keyCount, String... params)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.eval(script, keyCount, params);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Object eval(String script, List<String> keys, List<String> args)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.eval(script, keys, args);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Object eval(String script)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.eval(script);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Object evalsha(String script)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.evalsha(script);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Object evalsha(String sha1, List<String> keys, List<String> args)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.evalsha(sha1, keys, args);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Object evalsha(String sha1, int keyCount, String... params)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.evalsha(sha1, keyCount, params);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Boolean scriptExists(String sha1)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.scriptExists(sha1);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<Boolean> scriptExists(String... sha1)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.scriptExists(sha1);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String scriptLoad(String script)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.scriptLoad(script);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<Slowlog> slowlogGet()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.slowlogGet();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<Slowlog> slowlogGet(long entries)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.slowlogGet(entries);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long objectRefcount(String string)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.objectRefcount(string);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String objectEncoding(String string)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.objectEncoding(string);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long objectIdletime(String string)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.objectIdletime(string);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String set(byte[] key, byte[] value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.set(key, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public byte[] get(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.get(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Boolean exists(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.exists(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long del(byte[]... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.del(keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String type(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.type(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<byte[]> keys(byte[] pattern)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.keys(pattern);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public byte[] randomBinaryKey()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.randomBinaryKey();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String rename(byte[] oldkey, byte[] newkey)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.rename(oldkey, newkey);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long renamenx(byte[] oldkey, byte[] newkey)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.renamenx(oldkey, newkey);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long dbSize()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.dbSize();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long expire(byte[] key, int seconds)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.expire(key, seconds);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long expireAt(byte[] key, long unixTime)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.expireAt(key, unixTime);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long ttl(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.ttl(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long move(byte[] key, int dbIndex)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.move(key, dbIndex);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public byte[] getSet(byte[] key, byte[] value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.getSet(key, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<byte[]> mget(byte[]... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.mget(keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long setnx(byte[] key, byte[] value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.setnx(key, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String setex(byte[] key, int seconds, byte[] value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.setex(key, seconds, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String mset(byte[]... keysvalues)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.mset(keysvalues);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long msetnx(byte[]... keysvalues)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.msetnx(keysvalues);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long decrBy(byte[] key, long integer)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.decrBy(key, integer);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long decr(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.decr(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long incrBy(byte[] key, long integer)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.incrBy(key, integer);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long incr(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.incr(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long append(byte[] key, byte[] value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.append(key, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public byte[] substr(byte[] key, int start, int end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.substr(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long hset(byte[] key, byte[] field, byte[] value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hset(key, field, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public byte[] hget(byte[] key, byte[] field)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hget(key, field);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long hsetnx(byte[] key, byte[] field, byte[] value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hsetnx(key, field, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String hmset(byte[] key, Map<byte[], byte[]> hash)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hmset(key, hash);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<byte[]> hmget(byte[] key, byte[]... fields)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hmget(key, fields);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long hincrBy(byte[] key, byte[] field, long value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hincrBy(key, field, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Boolean hexists(byte[] key, byte[] field)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hexists(key, field);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long hdel(byte[] key, byte[]... fields)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hdel(key, fields);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long hlen(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hlen(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<byte[]> hkeys(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hkeys(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<byte[]> hvals(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hvals(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Map<byte[], byte[]> hgetAll(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.hgetAll(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long rpush(byte[] key, byte[]... strings)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.rpush(key, strings);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long lpush(byte[] key, byte[]... strings)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.lpush(key, strings);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long llen(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.llen(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<byte[]> lrange(byte[] key, int start, int end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.lrange(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String ltrim(byte[] key, int start, int end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.ltrim(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public byte[] lindex(byte[] key, int index)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.lindex(key, index);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String lset(byte[] key, int index, byte[] value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.lset(key, index, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long lrem(byte[] key, int count, byte[] value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.lrem(key, count, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public byte[] lpop(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.lpop(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public byte[] rpop(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.rpop(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public byte[] rpoplpush(byte[] srckey, byte[] dstkey)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.rpoplpush(srckey, dstkey);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long sadd(byte[] key, byte[]... members)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sadd(key, members);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<byte[]> smembers(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.smembers(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long srem(byte[] key, byte[]... member)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.srem(key, member);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public byte[] spop(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.spop(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long smove(byte[] srckey, byte[] dstkey, byte[] member)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.smove(srckey, dstkey, member);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long scard(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.scard(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Boolean sismember(byte[] key, byte[] member)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sismember(key, member);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<byte[]> sinter(byte[]... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sinter(keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long sinterstore(byte[] dstkey, byte[]... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sinterstore(dstkey, keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<byte[]> sunion(byte[]... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sunion(keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long sunionstore(byte[] dstkey, byte[]... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sunionstore(dstkey, keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<byte[]> sdiff(byte[]... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sdiff(keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long sdiffstore(byte[] dstkey, byte[]... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sdiffstore(dstkey, keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public byte[] srandmember(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.srandmember(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long zadd(byte[] key, double score, byte[] member)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zadd(key, score, member);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long zadd(byte[] key, Map<Double, byte[]> scoreMembers)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zadd(key, scoreMembers);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<byte[]> zrange(byte[] key, int start, int end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrange(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long zrem(byte[] key, byte[]... members)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrem(key, members);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Double zincrby(byte[] key, double score, byte[] member)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zincrby(key, score, member);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long zrank(byte[] key, byte[] member)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrank(key, member);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long zrevrank(byte[] key, byte[] member)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrank(key, member);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<byte[]> zrevrange(byte[] key, int start, int end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrange(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<Tuple> zrangeWithScores(byte[] key, int start, int end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrangeWithScores(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<Tuple> zrevrangeWithScores(byte[] key, int start, int end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrangeWithScores(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long zcard(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zcard(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Double zscore(byte[] key, byte[] member)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zscore(key, member);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Transaction multi()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.multi();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<Object> multi(TransactionBlock jedisTransaction)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.multi(jedisTransaction);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public void connect()
	{
		Jedis jedis = pool.getResource();
		try
		{
			jedis.connect();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public void disconnect()
	{
		Jedis jedis = pool.getResource();
		try
		{
			jedis.disconnect();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String watch(byte[]... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.watch(keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String unwatch()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.unwatch();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<byte[]> sort(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sort(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<byte[]> sort(byte[] key, SortingParams sortingParameters)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sort(key, sortingParameters);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<byte[]> blpop(int timeout, byte[]... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.blpop(timeout, keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long sort(byte[] key, SortingParams sortingParameters, byte[] dstkey)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sort(key, sortingParameters, dstkey);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long sort(byte[] key, byte[] dstkey)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.sort(key, dstkey);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<byte[]> brpop(int timeout, byte[]... keys)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.brpop(timeout, keys);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<Object> pipelined(PipelineBlock jedisPipeline)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.pipelined(jedisPipeline);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Pipeline pipelined()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.pipelined();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long zcount(byte[] key, double min, double max)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zcount(key, min, max);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long zcount(byte[] key, byte[] min, byte[] max)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zcount(key, min, max);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<byte[]> zrangeByScore(byte[] key, double min, double max)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrangeByScore(key, min, max);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrangeByScore(key, min, max);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<byte[]> zrangeByScore(byte[] key, double min, double max,
			int offset, int count)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrangeByScore(key, min, max, offset, count);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max,
			int offset, int count)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrangeByScore(key, min, max, offset, count);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrangeByScoreWithScores(key, min, max);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrangeByScoreWithScores(key, min, max);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min,
			double max, int offset, int count)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min,
			byte[] max, int offset, int count)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrangeByScore(key, max, min);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrangeByScore(key, max, min);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min,
			int offset, int count)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrangeByScore(key, max, min, offset, count);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min,
			int offset, int count)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrangeByScore(key, max, min, offset, count);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max,
			double min)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrangeByScoreWithScores(key, max, min);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max,
			double min, int offset, int count)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrangeByScoreWithScores(key, max, min, offset,
					count);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max,
			byte[] min)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrangeByScoreWithScores(key, max, min);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max,
			byte[] min, int offset, int count)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zrevrangeByScoreWithScores(key, max, min, offset,
					count);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long zremrangeByRank(byte[] key, int start, int end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zremrangeByRank(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long zremrangeByScore(byte[] key, double start, double end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zremrangeByScore(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long zremrangeByScore(byte[] key, byte[] start, byte[] end)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zremrangeByScore(key, start, end);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long zunionstore(byte[] dstkey, byte[]... sets)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zunionstore(dstkey, sets);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long zunionstore(byte[] dstkey, ZParams params, byte[]... sets)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zunionstore(dstkey, params, sets);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long zinterstore(byte[] dstkey, byte[]... sets)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zinterstore(dstkey, sets);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long zinterstore(byte[] dstkey, ZParams params, byte[]... sets)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.zinterstore(dstkey, params, sets);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String save()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.save();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String bgsave()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.bgsave();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String bgrewriteaof()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.bgrewriteaof();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long lastsave()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.lastsave();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String shutdown()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.shutdown();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String info()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.info();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public void monitor(JedisMonitor jedisMonitor)
	{
		Jedis jedis = pool.getResource();
		try
		{
			jedis.monitor(jedisMonitor);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String slaveof(String host, int port)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.slaveof(host, port);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String slaveofNoOne()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.slaveofNoOne();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<byte[]> configGet(byte[] pattern)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.configGet(pattern);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String configResetStat()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.configResetStat();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public byte[] configSet(byte[] parameter, byte[] value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.configSet(parameter, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public boolean isConnected()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.isConnected();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return false;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long strlen(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.strlen(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public void sync()
	{
		Jedis jedis = pool.getResource();
		try
		{
			jedis.sync();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long lpushx(byte[] key, byte[] string)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.lpushx(key, string);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long persist(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.persist(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long rpushx(byte[] key, byte[] string)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.rpushx(key, string);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public byte[] echo(byte[] string)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.echo(string);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long linsert(byte[] key, LIST_POSITION where, byte[] pivot,
			byte[] value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.linsert(key, where, pivot, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String debug(DebugParams params)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.debug(params);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Client getClient()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.getClient();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public byte[] brpoplpush(byte[] source, byte[] destination, int timeout)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.brpoplpush(source, destination, timeout);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Boolean setbit(byte[] key, long offset, byte[] value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.setbit(key, offset, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Boolean getbit(byte[] key, long offset)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.getbit(key, offset);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long setrange(byte[] key, long offset, byte[] value)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.setrange(key, offset, value);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public String getrange(byte[] key, long startOffset, long endOffset)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.getrange(key, startOffset, endOffset);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long publish(byte[] channel, byte[] message)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.publish(channel, message);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public void subscribe(BinaryJedisPubSub jedisPubSub, byte[]... channels)
	{
		Jedis jedis = pool.getResource();
		try
		{
			jedis.subscribe(jedisPubSub, channels);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public void psubscribe(BinaryJedisPubSub jedisPubSub, byte[]... patterns)
	{
		Jedis jedis = pool.getResource();
		try
		{
			jedis.psubscribe(jedisPubSub, patterns);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long getDB()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.getDB();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Object eval(byte[] script, List<byte[]> keys, List<byte[]> args)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.eval(script, keys, args);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Object eval(byte[] script, byte[] keyCount, byte[][] params)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.eval(script, keyCount, params);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public byte[] scriptFlush()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.scriptFlush();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<Long> scriptExists(byte[]... sha1)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.scriptExists(sha1);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public byte[] scriptLoad(byte[] script)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.scriptLoad(script);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public byte[] scriptKill()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.scriptKill();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public byte[] slowlogReset()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.slowlogReset();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public long slowlogLen()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.slowlogLen();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return 0l;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<byte[]> slowlogGetBinary()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.slowlogGetBinary();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public List<byte[]> slowlogGetBinary(long entries)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.slowlogGetBinary(entries);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long objectRefcount(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.objectRefcount(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public byte[] objectEncoding(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.objectEncoding(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	public Long objectIdletime(byte[] key)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.objectIdletime(key);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public int hashCode()
	{
		try
		{
			return this.getRedisDataSource().hashCode();
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj)
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.equals(obj);
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return false;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	@Override
	public String toString()
	{

		Jedis jedis = pool.getResource();
		try
		{
			return jedis.toString();
		}
		catch (Exception e)
		{
			pool.returnResource(jedis);
			return null;
		}
		finally
		{
			pool.returnResource(jedis);
		}
	}

	/**
	 * 异常告警
	 * 
	 * @param type
	 * @param e
	 */
	private void warning(int type, Exception e)
	{
		bm.onWarning("JedisTemplate", type, e.getMessage());
	}
}
