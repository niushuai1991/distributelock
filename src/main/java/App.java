import cn.gyyx.action.utils.TestThread;

/**
  * -------------------------------------------------------------------------
  * (C) Copyright Gyyx Tec Corp. 1996-2017 - All Rights Reserved
  * @版权所有：北京光宇在线科技有限责任公司
  * @项目名称：distributelock
  * @作者：niushuai
  * @联系方式：niushuai@gyyx.cn
  * @创建时间：2017年5月9日 上午10:36:20
  * @版本号：0.0.1
  *-------------------------------------------------------------------------
  */


/**
  * <p>
  *   TestLoader描述
  * </p>
  *  
  * @author niushuai
  * @since 0.0.1
  */
public class App {
    
    public static void main(String[] args) {
        System.out.println("开始锁测试");
        for (int i = 0 ;i<10; i++) {
            new TestThread().start();
        }
    }
}
