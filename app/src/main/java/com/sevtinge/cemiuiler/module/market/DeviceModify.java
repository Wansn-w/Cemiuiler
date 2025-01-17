package com.sevtinge.cemiuiler.module.market;

import android.os.Build;

import com.sevtinge.cemiuiler.module.base.BaseHook;

import de.robv.android.xposed.XposedHelpers;

public class DeviceModify extends BaseHook {

    String mDevice;
    String mModel;
    String mManufacturer;

    @Override
    public void init() {
        if (mPrefsMap.getStringAsInt("market_device_modify_new", 0) == 1) {//13u
            mDevice = "ishtar";
            mModel = "2304FPN6DC";
            mManufacturer = "Xiaomi";
        } else if (mPrefsMap.getStringAsInt("market_device_modify_new", 0) == 2) {//13p
            mDevice = "nuwa";
            mModel = "2210132C";
            mManufacturer = "Xiaomi";
        } else if (mPrefsMap.getStringAsInt("market_device_modify_new", 0) == 3) {//f2
            mDevice = "zizhan";
            mModel = "22061218C";
            mManufacturer = "Xiaomi";
        } else if (mPrefsMap.getStringAsInt("market_device_modify_new", 0) == 4) {//alpha
            mDevice = "avenger";
            mModel = "MIX Alpha";
            mManufacturer = "Xiaomi";
        } else if (mPrefsMap.getStringAsInt("market_device_modify_new", 0) == 5) {//alpha
            mDevice = "draco";
            mModel = "MIX Alpha";
            mManufacturer = "Xiaomi";
        } else if (mPrefsMap.getStringAsInt("market_device_modify_new", 0) == 6) {//pad6p
            mDevice = "liuqin";
            mModel = "23046RP50C";
            mManufacturer = "Xiaomi";
        } else if (mPrefsMap.getStringAsInt("market_device_modify_new", 0) == 7) {//k60p
            mDevice = "socrates";
            mModel = "22127RK46C";
            mManufacturer = "Redmi";
        } else if (mPrefsMap.getStringAsInt("market_device_modify_new", 0) == 8) {//n12t
            mDevice = "marble";
            mModel = "23049RAD8C";
            mManufacturer = "Redmi";
        }
        findAndHookConstructor("com.xiaomi.market.MarketApp", new MethodHook() {
            @Override
            protected void before(MethodHookParam param) throws Throwable {
                XposedHelpers.setStaticObjectField(Build.class, "DEVICE", mDevice);
                XposedHelpers.setStaticObjectField(Build.class, "MODEL", mModel);
                XposedHelpers.setStaticObjectField(Build.class, "MANUFACTURER", mManufacturer);
            }
        });
    }
}
