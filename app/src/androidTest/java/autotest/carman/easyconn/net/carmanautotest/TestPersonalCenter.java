package autotest.carman.easyconn.net.carmanautotest;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by wwl on 2017/5/23.
 * 个人中心
 */
@RunWith(AndroidJUnit4.class)
public class TestPersonalCenter extends TestBase {

    /**
     * 测试准备工作
     * 连接设备、启动应用
     * 调用goHomePage方法
     * 登录方法
     * */
    @Before
    public void initEnv() throws Exception
    {
        initDevice();
        startMainActivityFromHomeScreen();
        goHomePage();
        login();
    }

    /**
     * 检查个人中心页面跳转是否正常
     * */
    @Test
    public void testCheckYemian() throws Exception{
        clickByTextIfExists("硬件商城");
        Thread.sleep(3000);
        mDevice.pressBack();
        mDevice.waitForIdle(2000);
        clickByTextIfExists("离线地图");
        swipeToLeft();
        Thread.sleep(2000);
        swipeToRight();
        Thread.sleep(2000);
        mDevice.pressBack();
        clickByResIdIfExists("net.easyconn.carman:id/person_gridview_link");
        Thread.sleep(2000);
        mDevice.pressBack();
        clickByResIdIfExists("net.easyconn.carman:id/person_gridview_wrc");
        Thread.sleep(2000);
        mDevice.pressBack();
        clickByResIdIfExists("net.easyconn.carman:id/person_gridview_footmark");
        Thread.sleep(2000);
        mDevice.pressBack();
        clickByResIdIfExists("net.easyconn.carman:id/person_gridview_tpms");
        Thread.sleep(2000);
        mDevice.pressBack();
        clickByResIdIfExists("net.easyconn.carman:id/person_gridview_findcar");
        Thread.sleep(2000);
        clickByResIdIfExists("net.easyconn.carman:id/person_gridview_bluetooth_recharge");
        Thread.sleep(2000);
        mDevice.pressBack();
    }

}
