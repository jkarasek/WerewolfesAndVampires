package Simulation.Entities;

import Simulation.Entity;

public interface Weapon extends Entity
{
    /**
     * getDur - metoda zwracająca pozostałą wytrzymałość danej broni
     * damage - metoda uszkadzająca daną broń
     * canItKill - metoda zwracająca możliwości broni
     */

    int getDur();

    void damage();

    boolean canItKillV();
    boolean canItKillW();


}
