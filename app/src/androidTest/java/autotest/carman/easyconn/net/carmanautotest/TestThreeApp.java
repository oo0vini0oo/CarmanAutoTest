package autotest.carman.easyconn.net.carmanautotest;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
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

    public void testThirdApp() throws Exception{
        swipeToLeft();
        Thread.sleep(LONG_WAIT);

    }
}

