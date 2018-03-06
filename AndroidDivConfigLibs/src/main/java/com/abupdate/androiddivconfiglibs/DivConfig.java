package com.abupdate.androiddivconfiglibs;

import java.io.File;

/**
 * @author fighter_lee
 * @date 2018/3/2
 */
public class DivConfig {

    public File start(ConfigBean configBean) throws Exception {
        return new DivConfigImpl()
                .setConfig(configBean)
                .execute();
    }

}
