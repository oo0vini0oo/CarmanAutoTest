package autotest.carman.easyconn.net.carmanautotest;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by wwl on 2017/5/26.
 */
@RunWith(AndroidJUnit4.class)
public class TestThreeApp extends TestBase {

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
    public void testThirdApp() throws Exception{
        Thread.sleep(LONG_WAIT);
        swipeToLeft();
        Thread.sleep(LONG_WAIT);
        clickByResourceIdAndIndex("net.easyconn.carman:id/gridview",0);
        mDevice.pressBack();
        longClickByText("微信");
        Thread.sleep(LONG_WAIT);
        assertCheckButtonByResourceId("net.easyconn.carman:id/gridview",0);
        Thread.sleep(LONG_WAIT);
        assertCheckButtonByResourceId("net.easyconn.carman:id/gridview",0);
        Thread.sleep(LONG_WAIT);
        clickByResourceIdAndIndex("net.easyconn.carman:id/gridview",1,0);
        Thread.sleep(LONG_WAIT);
        clickByTextIfExists("添加应用");
        Thread.sleep(LONG_WAIT);
        clickByResourceIdAndIndex("net.easyconn.carman:id/gv_recommend_list",2);
        Thread.sleep(LONG_WAIT);

    }
}

