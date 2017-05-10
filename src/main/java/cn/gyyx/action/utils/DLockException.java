/**
  * -------------------------------------------------------------------------
  * (C) Copyright Gyyx Tec Corp. 1996-2017 - All Rights Reserved
  * @版权所有：北京光宇在线科技有限责任公司
  * @项目名称：distributelock
  * @作者：niushuai
  * @联系方式：niushuai@gyyx.cn
  * @创建时间：2017年5月9日 下午1:23:56
  * @版本号：0.0.1
  *-------------------------------------------------------------------------
  */
package cn.gyyx.action.utils;

/**
  * <p>
  *   DLockException描述
  * </p>
  *  
  * @author niushuai
  * @since 0.0.1
  */
public class DLockException extends Exception {
    
    private static final long serialVersionUID = -3424822269785250068L;

    public DLockException(String message) {
        super(message);
    }
}
