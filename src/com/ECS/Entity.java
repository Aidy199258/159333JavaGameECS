package com.ECS;

import java.util.ArrayList;
import java.util.List;

public class Entity {




    //Add new player
    public void add(PlayerEntity player){
        player.setLife(PlayerEntity.STARTLIFE);


    }

    //Add new Coin
    public void add(CoinEntity coin){

        coin.setCoinNumb(CoinEntity.STARTCOIN);
    }

    //Add new platform
    public void add(PlatformEntity platform){

    }


    public static class PlayerEntity{
        public static final int STARTLIFE = 1;
        private int _Life = 0;
        public int Life;

        public int getLife(){
            Life = _Life;
            return Life;
        }
        public void setLife(int life){
            _Life = life;
        }
        PlayerEntity(int life){
            this.setLife(life);
        }

    }

    public static class CoinEntity{
        public static final int STARTCOIN = 1;
        private int _CoinNumb = 0;
        public int CoinNumb;


        public int getCoinNumb() {
            CoinNumb = _CoinNumb;
            return CoinNumb;
        }

        public void setCoinNumb(int coinNumb) {
            _CoinNumb = coinNumb;

        }

        public void getACoin(){
            int CoinNumb = getCoinNumb();
            CoinNumb++;
            setCoinNumb(CoinNumb);
        }

        CoinEntity(int coinNumb){
            this.setCoinNumb(coinNumb);
        }

    }

    class PlatformEntity{


    }
}
