package ssm;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.tools.javac.util.Assert;

import redis.clients.jedis.Jedis;

public class JedisTest {

	@Test
	public void test() {
		Jedis js = new Jedis("127.0.0.1", 6379);
		//js.set("a", "as");
		String str =js.get("a");
		System.err.println(str);
	}

}
