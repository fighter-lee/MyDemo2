package com.abupdate.androiddivconfiglibs;

import java.io.File;

/**
 * @author fighter_lee
 * @date 2018/3/2
 */
public interface DivConfigInter {

    DivConfigInter setConfig(ConfigBean configJson);

    File execute() throws Exception;
}
