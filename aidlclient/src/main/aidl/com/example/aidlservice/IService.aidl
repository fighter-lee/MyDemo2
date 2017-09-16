// IService.aidl
package com.example.aidlservice;

// Declare any non-default types here with import statements
import com.example.aidlservice.IServiceListener;

interface IService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
   void test();// 测试直接调用aidl方法
   void start(IServiceListener iServiceListener);// 方法里面携带监听回调对象
}
