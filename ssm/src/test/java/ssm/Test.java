package ssm;

import redis.clients.jedis.Jedis;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class Test {

	@Test
	public void test() {
		Jedis js = new Jedis("127.0.0.1", 6379);
		js.set("a", "as");
		js.get("a");
	}

}
