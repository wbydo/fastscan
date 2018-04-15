package fastscan.model;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.Enclosed;

@RunWith(Enclosed.class)
public class IndexTest {
  public static class コンストラクタのテスト{
    LoopCounter x;
    LoopCounter y;
    Index i;

    @Before
    public void setUp(){
      x = new LoopCounter(8);
      y = new LoopCounter(6);
      i = new Index(x, y);
    }

    @Test
    public void xとyが0を出す(){
      assertThat(i.getX(), is(0));
      assertThat(i.getY(), is(0));
    }
  }

  public static class increaseのテスト{
    LoopCounter x;
    LoopCounter y;
    Index i;

    @Before
    public void setUp(){
      x = new LoopCounter(8);
      y = new LoopCounter(6);
      i = new Index(x, y);
    }

    @Test
    public void SELECT_COLUMNのときx1y0になる(){
      CursolModeModel m = CursolModeModel.SELECT_COLUMN;
      Index next = i.increase(m);
      assertThat(next.getX(), is(1));
      assertThat(next.getY(), is(0));
    }

    @Test
    public void SELECT_COLUMNでincrease9回やるとxが0(){
      CursolModeModel m = CursolModeModel.SELECT_COLUMN;
      int j;
      for(j=1; j<=8; j++){
        i = i.increase(m);
        assertThat(i.getX(), is(j));
        assertThat(i.getY(), is(0));
      }

      i = i.increase(m);
      assertThat(i.getX(), is(0));
      assertThat(i.getY(), is(0));
    }

    @Test
    public void SELECT_CHARのときx0y1になる(){
      CursolModeModel m = CursolModeModel.SELECT_CHAR;
      Index next = i.increase(m);
      assertThat(next.getX(), is(0));
      assertThat(next.getY(), is(1));
    }

    @Test
    public void SELECT_CHARでincrease6回やるとxが0(){
      CursolModeModel m = CursolModeModel.SELECT_CHAR;
      int j;
      for(j=1; j<=6; j++){
        i = i.increase(m);
        assertThat(i.getX(), is(0));
        assertThat(i.getY(), is(j));
      }

      i = i.increase(m);
      assertThat(i.getX(), is(0));
      assertThat(i.getY(), is(0));
    }

    @Test
    public void x5y6になる(){
      int j;
      CursolModeModel cx = CursolModeModel.SELECT_COLUMN;
      CursolModeModel cy = CursolModeModel.SELECT_CHAR;

      for(j=1; j<=5; j++){
        i = i.increase(cx);
      }
      for(j=1; j<=6; j++){
        i = i.increase(cy);
      }
      assertThat(i.getX(), is(5));
      assertThat(i.getY(), is(6));
    }
  }
}
