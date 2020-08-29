package com.ECS;

public class Entity {
    public static int Life;
    public static int CoinNumb;

    public void addPlayer(PlayerEntity player, int life){
        player.setLife(PlayerEntity.STARTLIFE);


    }
    public void addPlatform(){

    }

    public void addCoin(CoinEntity coin){
        coin.setCoinNumb(CoinEntity.STARTCOIN);
    }

    static class PlayerEntity{
        public static final int STARTLIFE = 1;
        private int _Life = 0;

        public int getLife(){
            Life = _Life;
            return Life;
        }
        public void setLife(int life){
            _Life = life;
        }

    }

    class CoinEntity{
        public static final int STARTCOIN = 1;
        private int _CoinNumb = 0;


        public int getCoinNumb() {
            CoinNumb = _CoinNumb;
            return CoinNumb;
        }

        public void setCoinNumb(int coinNumb) {
            _CoinNumb = coinNumb;
        }
    }

    class PlatformEntity{


    }
}
