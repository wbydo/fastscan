package fastscan.model;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.Enclosed;

@RunWith(Enclosed.class)
public class LoopCounterTest {

  public static class コンストラクタテスト{
    @Test(expected = IllegalArgumentException.class)
    public void maxが0で生成しようとするとエラーとなる(){
      LoopCounter l = new LoopCounter(0);
    }
  }

  public static class maxを5とした生成直後の挙動テスト{
    LoopCounter l;

    @Before
    public void setUp(){
      l = new LoopCounter(5);
    }

    @Test
    public void 初期値は0である(){
      assertThat(l.getValue(), is(0));
    }

    @Test
    public void カウントして0に戻る(){
      int expected;
      for(expected=0; expected <= 5; expected++){
        assertThat(l.getValue(), is(expected));
        l = l.increase();
      }

      expected = 0;
      assertThat(l.getValue(), is(expected));
    }

    @Test(expected = IllegalStateException.class)
    public void increaseを2回目に実行するとエラー(){
      l.increase();
      l.increase();
    }
  }
}
