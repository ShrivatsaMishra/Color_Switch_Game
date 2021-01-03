package sample;

import javafx.application.Platform;

import java.io.Serializable;

public class SaveState implements Serializable{
    private Player p;
    private int starLocx[], colorChangerx[], obsX[], starLocy[], colorChangery[], obsY[], angle[], gameMode;

    public SaveState(Player p, Star[] s, Obstacle[] obs, ColorChanger[] c, int gm)
    {
        gameMode = gm;
        this.p = p;
        starLocx = new int[s.length];
        starLocy = new int[s.length];
        colorChangerx = new int[s.length];
        colorChangery = new int[s.length];
        obsX = new int[s.length];
        obsY = new int[s.length];
        angle = new int[s.length];
        for(int i=0;i<s.length;i++)
        {
            starLocx[i] = s[i].getxPos();
            starLocy[i] = s[i].getyPos();
        }
        for(int i=0;i<obs.length;i++)
        {
            obsX[i] = obs[i].getxPos();
            obsY[i] = obs[i].getyPos();
            angle[i] = obs[i].getRot();
        }
        for(int i=0;i<s.length;i++)
        {
            colorChangerx[i] = c[i].getxPos();
            colorChangery[i] = c[i].getyPos();
        }
    }

    public void set(Player p, Star[] s, Obstacle[] obs, ColorChanger[] c, int gm) {
        this.p = p;
        gameMode = gm;
        for(int i=0;i<s.length;i++)
        {
            starLocx[i] = s[i].getxPos();
            starLocy[i] = s[i].getyPos();
        }
        for(int i=0;i<obs.length;i++)
        {
            obsX[i] = obs[i].getxPos();
            obsY[i] = obs[i].getyPos();
            angle[i] = obs[i].getRot();
        }
        for(int i=0;i<s.length;i++)
        {
            colorChangerx[i] = c[i].getxPos();
            colorChangery[i] = c[i].getyPos();
        }
    }

    public Player getP() {
        return p;
    }

    public int[] getStarLocx()
    {
        return starLocx;
    }
    public int[] getStarLocy()
    {
        return starLocy;
    }
    public int[] getColorChangerx()
    {
        return colorChangerx;
    }
    public int[] getColorChangery()
    {
        return colorChangery;
    }
    public int[] getObsX()
    {
        return obsX;
    }
    public int[] getObsY()
    {
        return obsY;
    }
    public int[] getAngle()
    {
        return angle;
    }
    public int getGameMode()
    {
        return gameMode;
    }
}
