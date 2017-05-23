package autotest.carman.easyconn.net.carmanautotest;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiSelector;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by wwl on 2017/5/19.
 */
@RunWith(AndroidJUnit4.class)
public class MusicTest extends TestBase {

    @Before
    public void initEnv() throws Exception
    {
        initDevice();
        startMainActivityFromHomeScreen();
        goHomePage();

    }
    @Test
    public void testMusicList() throws Exception {
        mDevice.waitForIdle(5000);
        clickByResIdIfExists("net.easyconn.carman:id/rl_cover_default");

    }
}
