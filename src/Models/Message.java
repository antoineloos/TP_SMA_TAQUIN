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
public class Message {
    private Agent emetteur;
    private Agent destinataire;
    private int action;
    private Case caseCourante;
    private boolean etat;
    
    public Message(Agent emetteur, Agent destinataire, int action, Case caseCourante) {
        this.emetteur = emetteur;
        this.destinataire = destinataire;
        this.action = action;
        this.caseCourante = caseCourante;
        this.etat = true;
    }

    /**
     * @return the emetteur
     */
    public Agent getEmetteur() {
        return emetteur;
    }

    /**
     * @param emetteur the emetteur to set
     */
    public void setEmetteur(Agent emetteur) {
        this.emetteur = emetteur;
    }

    /**
     * @return the destinataire
     */
    public Agent getDestinataire() {
        return destinataire;
    }

    /**
     * @param destinataire the destinataire to set
     */
    public void setDestinataire(Agent destinataire) {
        this.destinataire = destinataire;
    }

    /**
     * @return the action
     */
    public int getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(int action) {
        this.action = action;
    }

    /**
     * @return the caseCourante
     */
    public Case getCaseCourante() {
        return caseCourante;
    }

    /**
     * @param caseCourante the caseCourante to set
     */
    public void setCaseCourante(Case caseCourante) {
        this.caseCourante = caseCourante;
    }

    /**
     * @return the etat
     */
    public boolean isEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(boolean etat) {
        this.etat = etat;
    }
}
