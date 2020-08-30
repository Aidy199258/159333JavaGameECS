package com.ECS;

import java.util.ArrayList;
import java.util.List;

public class Entity {




    //Add new player
    public void addEntity(PlayerEntity player, int life){
        player.PlayerEntity(life);


    }

    //Add new Coin
    public void addEntity(CoinEntity coin){

        coin.setCoinNumb(CoinEntity.STARTCOIN);
    }

    //Add new platform
    public void add(PlatformEntity platform){

    }


    public int getEntity(PlayerEntity playerE){

        return playerE.PlayerEntity();
    }

    public void setEntity(PlayerEntity playerE, int value){
        playerE.PlayerEntity(value);
    }


    public static class PlayerEntity{
        public static final int STARTLIFE = 1;
        private int _Life = 0;
        //public int Life;

        //Get Life value
        public int PlayerEntity(){
            return _Life;
        }

        //Set life for player
        public void PlayerEntity(int life){
            _Life = life;
        }
        /*

        public int getLife(PlayerEntity playerE){
            return playerE._Life;
            //Life = _Life;
            //return Life;
        }

        public void setLife(int life){
            _Life = life;
        }

         */


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
