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
        Thread.sleep(LONG_WAIT);
        clickByText("离线地图");
        swipeToLeft();
        Thread.sleep(LONG_WAIT);
        swipeToRight();
        Thread.sleep(LONG_WAIT);
        mDevice.pressBack();
        clickByResourceId("net.easyconn.carman:id/person_gridview_link");
        Thread.sleep(LONG_WAIT);
        mDevice.pressBack();
        clickByResourceId("net.easyconn.carman:id/person_gridview_wrc");
        Thread.sleep(LONG_WAIT);
        mDevice.pressBack();
        clickByResourceId("net.easyconn.carman:id/person_gridview_footmark");
        Thread.sleep(LONG_WAIT);
        mDevice.pressBack();
        clickByResourceId("net.easyconn.carman:id/person_gridview_tpms");
        Thread.sleep(LONG_WAIT);
        mDevice.pressBack();
        clickByResourceId("net.easyconn.carman:id/person_gridview_findcar");
        Thread.sleep(LONG_WAIT);
        clickByResourceId("net.easyconn.carman:id/person_gridview_bluetooth_recharge");
        Thread.sleep(LONG_WAIT);
        mDevice.pressBack();
    }

    /**
     * 设置
     * */
    @Test
    public void testSetting() throws Exception{
        clickByResourceId("net.easyconn.carman:id/tv_system_settings");
        Thread.sleep(LONG_WAIT);
        assertCheckButton("net.easyconn.carman:id/cb_screen_always_on");
        Thread.sleep(LONG_WAIT);
        assertCheckButton("net.easyconn.carman:id/welcom_xiaoyi_cb");
        Thread.sleep(LONG_WAIT);
        assertCheckButton("net.easyconn.carman:id/cb_auto_playing_music");
        Thread.sleep(LONG_WAIT);
        assertCheckButton("net.easyconn.carman:id/rb_light");
        Thread.sleep(LONG_WAIT);
        swipeToUp();
        Thread.sleep(LONG_WAIT);
        clickByResourceId("net.easyconn.carman:id/tv_navi_setting");
        Thread.sleep(LONG_WAIT);
        mDevice.pressBack();
        Thread.sleep(LONG_WAIT);
        assertCheckButton("net.easyconn.carman:id/rb_night");
        Thread.sleep(LONG_WAIT);
        clickByResourceId("net.easyconn.carman:id/tv_wrc_setting");
        Thread.sleep(LONG_WAIT);
        mDevice.pressBack();
        Thread.sleep(LONG_WAIT);
        clickByResourceId("net.easyconn.carman:id/iv_system_lib");
        Thread.sleep(LONG_WAIT);
        mDevice.pressBack();
        Thread.sleep(LONG_WAIT);
        swipeToDown();
        Thread.sleep(LONG_WAIT);
        assertCheckButton("net.easyconn.carman:id/cb_screen_always_on");
        Thread.sleep(LONG_WAIT);
        assertCheckButton("net.easyconn.carman:id/welcom_xiaoyi_cb");
        Thread.sleep(LONG_WAIT);
        assertCheckButton("net.easyconn.carman:id/cb_auto_playing_music");
        Thread.sleep(LONG_WAIT);
        assertCheckButton("net.easyconn.carman:id/rb_auto");
        Thread.sleep(LONG_WAIT);
        mDevice.pressBack();
    }

    /**
     * 意见反馈
     * */
    @Test
    public void testFeedback() throws Exception{
        clickByResourceId("net.easyconn.carman:id/rl_system_feedback");
        Thread.sleep(SHORT_WAIT);
        setTextByResourceId("net.easyconn.carman:id/et_feed_content","this is text msg!");
        Thread.sleep(LONG_WAIT);
        setTextByResourceId("net.easyconn.carman:id/et_contact","262864900@qq.com");
        Thread.sleep(LONG_WAIT);
        clickByResourceId("net.easyconn.carman:id/btn_submit");
        Thread.sleep(LONG_WAIT);
    }

    /**
     * 关于
     * */
    @Test
    public void testAbout() throws Exception{
        clickByResourceId("net.easyconn.carman:id/rl_system_about");
        Thread.sleep(LONG_WAIT);
        mDevice.pressBack();
        Thread.sleep(LONG_WAIT);
        mDevice.pressBack();
    }

}
