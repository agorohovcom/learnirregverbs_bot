package com.agorohov.learnirregverbs_bot.component.db_agency;

public abstract class DBAgency {
    
    protected DBInteractionStrategy strategy;
    
    public void doWork(){
        strategy.interact();
    }
}
