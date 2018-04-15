package fastscan.model;

public enum CursolModeModel {
    SELECT_COLUMN(){
      @Override
      void increase(Index i){
        i.increaseX();
      }

      @Override
      void singleClick(MainModel m){
        if(ScanModel.needConfirm()){
          m.setNeedConfirmAgain(true);
          m.setCurrentMode(CursolModeModel.CONFIRM_COLUMN);
        }
        else{
          m.setCurrentMode(CursolModeModel.SELECT_CHAR);
          ScanModel.startScan();
        }
      }

      @Override
      void doubleClick(MainModel m){
        if(m.hasPrevIndex()){
          m.deleteChar();
          m.setNeedConfirmAgain(false);
          m.setCurrentMode(CursolModeModel.CONFIRM_CHAR);
          m.restore();
          m.increase();
        }
        else{
          ScanModel.startScan();
        }
      }
    },

    CONFIRM_COLUMN(){
      @Override
      void singleClick(MainModel m){
        m.setCurrentMode(CursolModeModel.SELECT_CHAR);
        m.setNeedConfirmAgain(false);
        ScanModel.startScan();
      }

      @Override
      void doubleClick(MainModel m){
        if(m.getNeedConfirmAgain()){
          m.setNeedConfirmAgain(false);
          m.increase();
        }
        else{
          m.setNeedConfirmAgain(false);
          m.resetXY();
          m.setCurrentMode(CursolModeModel.SELECT_COLUMN);
          ScanModel.startScan();
        }
      }

      @Override
      void increase(Index i){
        i.increaseX();
      }
    },

    SELECT_CHAR(){
      @Override
      void increase(Index i){
        i.increaseY();
      }

      @Override
      void singleClick(MainModel m){
        if(ScanModel.needConfirm()){
          m.setNeedConfirmAgain(true);
          m.setCurrentMode(CursolModeModel.CONFIRM_CHAR);
        }
        else{
          m.addChar();
          m.resetXY();
          m.setCurrentMode(CursolModeModel.SELECT_COLUMN);
          ScanModel.startScan();
        }
      }

      @Override
      void doubleClick(MainModel m){
        m.setNeedConfirmAgain(false);
        m.setCurrentMode(CursolModeModel.CONFIRM_COLUMN);
        m.increase();
      }
    },

    CONFIRM_CHAR(){
      @Override
      void increase(Index i){
        i.increaseY();
      }

      @Override
      void singleClick(MainModel m){
        m.addChar();
        m.resetXY();
        m.setCurrentMode(CursolModeModel.SELECT_COLUMN);
        m.setNeedConfirmAgain(false);
        ScanModel.startScan();
      }

      @Override
      void doubleClick(MainModel m){
        if(m.getNeedConfirmAgain()){
          m.setNeedConfirmAgain(false);
          m.increase();
        }
        else{
          m.setNeedConfirmAgain(false);
          m.resetY();
          m.setCurrentMode(CursolModeModel.SELECT_CHAR);
          ScanModel.startScan();
        }
      }
    };

    void increase(Index i){
      throw new IllegalStateException();
    }

    void singleClick(MainModel m){
      throw new IllegalStateException();
    }

    void doubleClick(MainModel m){
      throw new IllegalStateException();
    }
}
