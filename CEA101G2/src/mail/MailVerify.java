package mail;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MailVerify {
		
	private static JedisPool pool = null;

	static {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(8);
		config.setMaxIdle(8);
		config.setMaxWaitMillis(8640000);
		pool = new JedisPool(config, "localhost", 6379);
	}

	public static String getAuthCode() {
		int randomNumbe = 0, count = 0;
		String code="";
	    
		while(count < 8) {
			randomNumbe = (int)(Math.random() * 75) + 48;		
			if( (48 <= randomNumbe && randomNumbe <= 57) || (65 <= randomNumbe && randomNumbe <= 90) || 97 <= randomNumbe && randomNumbe <= 122) {
				code += (char)randomNumbe;
				count++;
			}			
		}
		return code;
	}
		
	public String insertCode(String mem_acct) {
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		String code = getAuthCode();
		jedis.set(mem_acct, code);
		jedis.close();
		return code;
	}
	
	public boolean verifyCode(String mem_acct, String code) {
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		if (jedis.get(mem_acct).equals(code)) {
			jedis.close();
			return true;
		} else {
			jedis.close();
			return false;
		}
	}
	
	
}
