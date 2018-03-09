/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author Epulapp
 */
public class Agent extends Observable implements Runnable {

    private Case target;
    private Case currentCase;
    private String id;
    private static ArrayList<Message> messageList = new ArrayList<Message>();
    private int nbpas;
    private Grille mainGrd;

    public Agent(String nom, Grille grd) {
        nbpas = 0;
        id = nom;
        mainGrd = grd;

    }

    @Override
    public void run() {

        while (mainGrd.lstAgent.stream().anyMatch(a -> a.GetIsIenb() == false)) {
            Message msg = GetMessageForMe();
            if (msg != null) {
                try {
                    move(msg.getEmetteur().currentCase);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (!currentCase.equals(target)) {

                try {
                    move(msg.getEmetteur().currentCase);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Boolean GetIsIenb() {
        return currentCase.equals(target);
    }

    public synchronized Message GetMessageForMe() {
        if (Agent.messageList.size() > 0) {
            System.out.println("Wesh il y a des messages bro");
            Iterator<Message> it = Agent.messageList.iterator();
            Message tmp = null;
            while (it.hasNext()) {
                tmp = it.next();

                if (tmp != null && tmp.getDestinataire() == this) {

                    it.remove();
                    System.err.println("Tu dois tu bouger frère");
                    return tmp;

                }
            }

        }
        return null;
    }

    public void move(Case forbid) throws InterruptedException {

        if (target.getPosX() > this.getCurrentCase().getPosX()) {

            moveproc(1, 0, forbid);
        } else if (target.getPosX() < this.getCurrentCase().getPosX()) {

            moveproc(-1, 0, forbid);
        } else if (target.getPosY() > this.getCurrentCase().getPosY()) {

            moveproc(0, 1, forbid);
        } else if (target.getPosY() < this.getCurrentCase().getPosY()) {

            moveproc(0, -1, forbid);
        }

    }

    synchronized public void moveproc(int plusx, int plusy, Case forbid) throws InterruptedException {

        if (forbid == null) {
            if (mainGrd.stream().anyMatch(c -> c.getPosX() == this.getCurrentCase().getPosX() + plusx && c.getPosY() == this.getCurrentCase().getPosY() + plusy)) {
                Case cplus1 = mainGrd.stream().filter(c -> c.getPosX() == this.getCurrentCase().getPosX() + plusx && c.getPosY() == this.getCurrentCase().getPosY() + plusy).findFirst().get();
                if (cplus1.getIsOccuped()) {
                    System.out.println("produce Message");

                    Agent agentToCall = FindAgentByCase(cplus1);
                    if (agentToCall != null) {
                        Agent.messageList.add(new Message(this, agentToCall, 1, cplus1));
                    }
                } else {
                    this.getCurrentCase().SetState(false);
                    this.setCurrentCase(cplus1);
                    this.getCurrentCase().SetState(true);
                }

            }
        }
        else
        {
            
        }

    }

    public Agent FindAgentByCase(Case cs) {
        Iterator<Agent> it = mainGrd.lstAgent.iterator();
        while (it.hasNext()) {
            Agent tmp = it.next();
            if (tmp.currentCase.equals(cs)) {
                return tmp;
            }

        }
        return null;
    }

    /**
     * @return the target
     */
    public Case getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(Case target) {
        this.target = target;
    }

    /**
     * @return the currentCase
     */
    public Case getCurrentCase() {
        return currentCase;
    }

    /**
     * @param currentCase the currentCase to set
     */
    public void setCurrentCase(Case currentCase) {

        this.currentCase = currentCase;
        setChanged();
        notifyObservers();

    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the messageList
     */
    public static ArrayList<Message> getMessageList() {
        return messageList;
    }

    /**
     * @param aMessageList the messageList to set
     */
    public static void setMessageList(ArrayList<Message> aMessageList) {
        messageList = aMessageList;
    }

    /**
     * @return the nbpas
     */
    public int getNbpas() {
        return nbpas;
    }

    /**
     * @param nbpas the nbpas to set
     */
    public void setNbpas(int nbpas) {
        this.nbpas = nbpas;
    }

}
