/**
  * -------------------------------------------------------------------------
  * (C) Copyright Gyyx Tec Corp. 1996-2017 - All Rights Reserved
  * @版权所有：北京光宇在线科技有限责任公司
  * @项目名称：distributelock
  * @作者：niushuai
  * @联系方式：niushuai@gyyx.cn
  * @创建时间：2017年5月9日 上午9:56:48
  * @版本号：0.0.1
  *-------------------------------------------------------------------------
  */
package cn.gyyx.action.utils;

import java.util.concurrent.TimeUnit;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <p>
 * 分布式锁
 * </p>
 * 
 * @author niushuai
 * @since 0.0.1
 */
public class DLock implements AutoCloseable {
    
    
    private String key_prefix = "DLock_";
    private RedisTemplate<String, Object> redisTemplate = null;
    private String lockKey;
    private int tryTimeOut = 10; // 重试间隔 
    private boolean getLock = false;
    public DLock(String key) {
        lockKey = key_prefix + key;
        getValueOperations();
    }
    
    private void getValueOperations() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-redis.xml");
        redisTemplate = context.getBean("redisTemplate",RedisTemplate.class);
    }
    
    /**
      * <p>
      * 启用锁
      * </p>
      *
      * @action
      *    niushuai 2017年5月9日 下午3:04:15 描述
      *
      * @param lockTime 锁定时间(毫秒)
      * @param timeOut 超时时间(毫秒)
     */
    public void lock(long lockTime, long timeOut){
        long i=0;
        do {
            getLock = redisTemplate.opsForValue().setIfAbsent(lockKey,"");
            if (getLock) {
                redisTemplate.expire(lockKey, 5, TimeUnit.SECONDS);
                System.out.println(String.format("线程" + Thread.currentThread().getId() + "，拿锁成功=%b", lockKey, getLock));
                break;
            }
            try {
                Thread.sleep(tryTimeOut);
                i += tryTimeOut;
            } catch (InterruptedException e) {}
            i++;
        } while(i < timeOut);
    }
    
    /* (non-Javadoc)
     * @see java.lang.AutoCloseable#close()
     */
    public void close() throws DLockException {
        if(getLock && redisTemplate.opsForValue().get(lockKey) != null) {
            redisTemplate.delete(lockKey);
            System.out.println("线程" + Thread.currentThread().getId() + "，解锁！key:" + lockKey);
        } else {
            // 没有获取到锁
            throw new DLockException("线程" + Thread.currentThread().getId() + "，关闭锁时发现没有获取到锁");
        }
    }
    
}
