package com.polytech4a.robocup.firebot.observers;

import com.polytech4a.robocup.firebot.robots.Firebot;

/**
 * Created by Adrien CHAUSSENDE on 02/06/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          Type of manager that can be notified by observable of the model.
 */
public interface ManagerObserver {

    /**
     * Update firebot informations when he completes its task.
     */
    public void updateActivity(Firebot firebot);
}
