package autotest.carman.easyconn.net.carmanautotest;

import android.support.test.runner.AndroidJUnit4;



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
        clickByText("硬件商城");
        Thread.sleep(3000);
        mDevice.pressBack();
        if(!clickByTextIfExists("离线地图").exists()){
            mDevice.pressBack();
            mDevice.pressBack();
        }
        Thread.sleep(2000);
        clickByText("离线地图");
        swipeToLeft();
        Thread.sleep(2000);
        swipeToRight();
        Thread.sleep(2000);
        mDevice.pressBack();
        clickByResourceId("net.easyconn.carman:id/person_gridview_link");
        Thread.sleep(2000);
        mDevice.pressBack();
        clickByResourceId("net.easyconn.carman:id/person_gridview_wrc");
        Thread.sleep(2000);
        mDevice.pressBack();
        clickByResourceId("net.easyconn.carman:id/person_gridview_footmark");
        Thread.sleep(2000);
        mDevice.pressBack();
        clickByResourceId("net.easyconn.carman:id/person_gridview_tpms");
        Thread.sleep(2000);
        mDevice.pressBack();
        clickByResourceId("net.easyconn.carman:id/person_gridview_findcar");
        Thread.sleep(2000);
        clickByResourceId("net.easyconn.carman:id/person_gridview_bluetooth_recharge");
        Thread.sleep(2000);
        mDevice.pressBack();
    }

    @Test
    public void testSetting() throws Exception{
        clickByResourceId("net.easyconn.carman:id/tv_system_settings");
        Thread.sleep(2000);
        clickByResourceId("net.easyconn.carman:id/cb_screen_always_on");
        Thread.sleep(2000);
        clickByResourceId("net.easyconn.carman:id/welcom_xiaoyi_cb");
        Thread.sleep(2000);
        clickByResourceId("net.easyconn.carman:id/cb_auto_playing_music");
        Thread.sleep(2000);
        clickByResourceId("net.easyconn.carman:id/rb_light");
        Thread.sleep(2000);
        swipeToUp();
        Thread.sleep(2000);
        clickByResourceId("net.easyconn.carman:id/tv_navi_setting");
        Thread.sleep(2000);
        clickByResourceId("net.easyconn.carman:id/iv_system_back");
        Thread.sleep(2000);
        clickByResourceId("net.easyconn.carman:id/rb_night");
        Thread.sleep(2000);
        clickByResourceId("net.easyconn.carman:id/tv_wrc_setting");
        Thread.sleep(2000);
        clickByResourceId("net.easyconn.carman:id/iv_system_back");
        Thread.sleep(2000);
        clickByResourceId("net.easyconn.carman:id/iv_system_lib");
        Thread.sleep(2000);
        clickByResourceId("net.easyconn.carman:id/iv_system_back");
        Thread.sleep(2000);
        swipeToDown();
        Thread.sleep(2000);
        clickByResourceId("net.easyconn.carman:id/cb_screen_always_on");
        Thread.sleep(2000);
        clickByResourceId("net.easyconn.carman:id/welcom_xiaoyi_cb");
        Thread.sleep(2000);
        clickByResourceId("net.easyconn.carman:id/cb_auto_playing_music");
        Thread.sleep(2000);
        clickByResourceId("net.easyconn.carman:id/rb_auto");
        Thread.sleep(2000);
        mDevice.pressBack();
    }

}
