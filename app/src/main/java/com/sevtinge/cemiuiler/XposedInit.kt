package com.sevtinge.cemiuiler

import com.github.kyuubiran.ezxhelper.init.EzXHelperInit
import com.sevtinge.cemiuiler.module.base.BaseXposedInit
import com.sevtinge.cemiuiler.module.home.title.EnableIconMonetColor
import com.sevtinge.cemiuiler.module.settings.VolumeSeparateControlForSettings
import com.sevtinge.cemiuiler.module.systemframework.CleanShareMenu
import com.sevtinge.cemiuiler.module.systemframework.ScreenRotation
import com.sevtinge.cemiuiler.module.SystemFrameworkForCorepatch
import com.sevtinge.cemiuiler.module.systemframework.AllowUninstall
import com.sevtinge.cemiuiler.module.systemframework.CleanOpenMenu
import com.sevtinge.cemiuiler.module.tsmclient.AutoNfc
import com.sevtinge.cemiuiler.module.wini.hooks.FrameworkHooks
import com.sevtinge.cemiuiler.module.wini.hooks.WiniMainHook
import de.robv.android.xposed.IXposedHookInitPackageResources
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.callbacks.XC_InitPackageResources
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

private const val TAG = "Cemiuiler"

class XposedInit : BaseXposedInit(), IXposedHookInitPackageResources {
    @Throws(Throwable::class)
    override fun initZygote(startupParam: IXposedHookZygoteInit.StartupParam?) {
        super.initZygote(startupParam)
        SystemFrameworkForCorepatch().initZygote(startupParam)
        if (mPrefsMap.getBoolean("system_framework_allow_uninstall")) AllowUninstall().initZygote(startupParam)
        if (mPrefsMap.getBoolean("system_framework_screen_all_rotations")) ScreenRotation.initRes()
        if (mPrefsMap.getBoolean("system_framework_clean_share_menu")) CleanShareMenu.initRes()
        if (mPrefsMap.getBoolean("system_framework_clean_open_menu")) CleanOpenMenu.initRes()
        if (mPrefsMap.getBoolean("system_framework_volume_separate_control")) VolumeSeparateControlForSettings.initRes()
        //if (mPrefsMap.getBoolean("various_theme_crack")) ThemeCrack.initRes()
        if (startupParam != null) {
            FrameworkHooks().initZygote(startupParam)
        }
    }

    @Throws(Throwable::class)
    override fun handleLoadPackage(lpparam: LoadPackageParam) {
        // Init EzXHelper
        EzXHelperInit.initHandleLoadPackage(lpparam)
        EzXHelperInit.setLogTag(TAG)
        EzXHelperInit.setToastTag(TAG)
        init(lpparam)
        SystemFrameworkForCorepatch().handleLoadPackage(lpparam)
        WiniMainHook().handleLoadPackage(lpparam)
    }

    override fun handleInitPackageResources(resparam: XC_InitPackageResources.InitPackageResourcesParam) {
        when (resparam.packageName) {
            "com.miui.tsmclient" -> if (mPrefsMap.getBoolean("tsmclient_auto_nfc")) {
                AutoNfc.initResource(resparam)
            }
            "com.miui.home" -> if (mPrefsMap.getBoolean("home_other_icon_monet_color")) {
                EnableIconMonetColor.initResource(resparam)
            }
        }
    }
}