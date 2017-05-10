/**
  * -------------------------------------------------------------------------
  * (C) Copyright Gyyx Tec Corp. 1996-2017 - All Rights Reserved
  * @版权所有：北京光宇在线科技有限责任公司
  * @项目名称：distributelock
  * @作者：niushuai
  * @联系方式：niushuai@gyyx.cn
  * @创建时间：2017年5月9日 下午1:41:10
  * @版本号：0.0.1
  *-------------------------------------------------------------------------
  */
package cn.gyyx.action.utils;

/**
  * <p>
  *   TestThread描述
  * </p>
  *  
  * @author niushuai
  * @since 0.0.1
  */
public class TestThread extends Thread {
    // 测试锁
    @Override
    public void run() {
        boolean commit = false;
        try {
            try (DLock lock = new DLock("test1")) {
                lock.lock(10000,5000);
                // do something..
                try {
                    Thread.sleep(100);
                } catch(Exception e) {
                    System.out.println("线程休眠失败！");
                };
            }
            commit = true; // 事务提交
        } catch(DLockException e) {
            commit = false; // 事务回滚
            e.printStackTrace();
        }
        System.out.println("线程" + Thread.currentThread().getId() + "，事务处理结果：" + commit);
    }
}