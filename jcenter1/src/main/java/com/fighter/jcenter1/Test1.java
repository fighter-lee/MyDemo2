package com.fighter.jcenter1;

import com.adups.mqtt_libs.ota.OtaAgentPolicy;
import com.adups.mqtt_libs.ota.info.VersionInfo;
import com.adups.mqtt_libs.ota.inter.ICheckVersionCallback;

/**
 * Created by fighter_lee on 2017/6/28.
 */

public class Test1 {

    public void test1() {
        OtaAgentPolicy.checkVersion(new ICheckVersionCallback() {
            @Override
            public void onCheckSuccess(VersionInfo versionInfo) {

            }

            @Override
            public void onCheckFail(int status) {

            }
        });
    }

}
