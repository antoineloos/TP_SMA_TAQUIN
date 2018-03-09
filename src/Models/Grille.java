/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Views.GridView;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Epulapp
 */
public class Grille extends ArrayList<Case>{
    
    int nbagent;
    
    public ArrayList<Agent> lstAgent;
    public ExecutorService service;
    GridView vw;
    
    public Grille(int nbag, GridView view)
    {
        lstAgent = new ArrayList<Agent>();
        nbagent = nbag;
        service = Executors.newFixedThreadPool(nbag);
        vw = view;
    }
    
    public void Generate(int size , int nbagent)
    {
        Random rand = new Random();
        
        
        for(int i = 0; i< size; i++)
        {
            for(int j = 0; j<size; j++)
            {
                this.add(new Case(i,j));
            }
            
        }
        
        
        for(int i = 0; i < nbagent; i++){
           Agent nwAgent = new Agent(String.valueOf(i), this);
           nwAgent.setCurrentCase(this.get(i));
           
           while(nwAgent.getTarget()==null)
           {
               int pos = rand.nextInt(this.size());
               if(!this.get(pos).getIsAlreadyTargeted()){
                   nwAgent.setTarget(this.get(pos));
                   this.get(pos).SetTargeted(true);
               }
           }
           lstAgent.add(nwAgent);
           nwAgent.addObserver(vw);
        } 
        
        
        
    }
    
    public void startAgent()
    {
        executeRunnables(service, lstAgent);
    }
    
    public static void executeRunnables(final ExecutorService service, ArrayList<Agent> runnables){

               

        for(Runnable r : runnables){


            service.execute(r);

        }

        service.shutdown();

    }
}
