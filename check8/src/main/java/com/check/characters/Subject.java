package com.check.characters;

public interface Subject {

    public void attach(HealthObserver healthObserver);

    public void detach(HealthObserver healthObserver);

    public void updateAll();

}
