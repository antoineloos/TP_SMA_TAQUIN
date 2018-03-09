/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Epulapp
 */
public class Case {
    private int posX ;
    private int posY;
    private Boolean IsOccuped;
    private Boolean IsAlreadyTargeted;        
    public Case(int x, int y)
    {
        IsAlreadyTargeted = false;
        IsOccuped = false;
        posX = x;
        posY = y;
    }
    
    public void SetState(Boolean state)
    {
        this.IsOccuped = state;
    }
    
    public void SetTargeted(Boolean state)
    {
        this.IsAlreadyTargeted = state;
    }

    /**
     * @return the posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @param posX the posX to set
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * @return the posY
     */
    public int getPosY() {
        return posY;
    }

    /**
     * @param posY the posY to set
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * @return the IsOccuped
     */
    public Boolean getIsOccuped() {
        return IsOccuped;
    }

    /**
     * @return the IsAlreadyTargeted
     */
    public Boolean getIsAlreadyTargeted() {
        return IsAlreadyTargeted;
    }
    
}
