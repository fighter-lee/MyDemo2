// IServiceListener.aidl
package com.example.aidlservice;

// Declare any non-default types here with import statements

interface IServiceListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void download(int progress);
    void cancel();
}