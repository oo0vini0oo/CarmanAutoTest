package autotest.carman.easyconn.net.carmanautotest;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by wwl on 2017/5/26.
 */
@RunWith(AndroidJUnit4.class)
public class TestMap extends TestBase {
    /**
     * 测试准备工作
     * 连接设备、启动应用
     * 调用goHomePage方法
     * */
    @Before
    public void initEnv() throws Exception
    {
        initDevice();
        startMainActivityFromHomeScreen();
        goHomePage();
    }

    @Test
    public void testMap() throws Exception{
        clickByResourceId("net.easyconn.carman:id/ll_normal");
        Thread.sleep(LONG_WAIT);
        clickByResourceId("net.easyconn.carman:id/tv_search");
        Thread.sleep(LONG_WAIT);
        clickByResourceId("net.easyconn.carman:id/tv_input");
        Thread.sleep(LONG_WAIT);
        setTextByResourceId("net.easyconn.carman:id/et_search","汉街");
        Thread.sleep(LONG_WAIT);
        //clickByResourceId("net.easyconn.carman:id/ll_left_action");
        ClickByResourceNameAndIndex("net.easyconn.carman:id/search_list_view",2);
        Thread.sleep(LONG_WAIT);
        ClickByResourceNameAndIndex("net.easyconn.carman:id/result_view",1, "net.easyconn.carman:id/iv_favorite");
        Thread.sleep(LONG_WAIT);
    }

}
