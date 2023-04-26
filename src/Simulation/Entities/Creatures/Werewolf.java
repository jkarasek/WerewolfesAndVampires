package Simulation.Entities.Creatures;

import Simulation.Entities.Colors;
import Simulation.Entities.Weapon;
import Simulation.Entity;
import Simulation.InteractionResults;
import java.util.concurrent.ThreadLocalRandom;

public class Werewolf implements Entity {

    public int boost=0; //aktualna wartość wzmocnienia wilkołaka, wpływa na szanse zwycięstwa z wampirem
    private int gen=0; //zmienna odpowiadająca za zapobiegnięcie wykonania więcej niż jednego ruchu na cykl
    public static int werewolvesKilled=0;

    @Override
    public void pickUp(Entity e) {

    }
    /**
     * Seria metod zwracających rezultaty obrania obiektu tej klasy na cel przez ruszającą się jednostkę
     * @return - rezultat interakcji typu enum
     *
     */
    @Override
    public InteractionResults onWerewolfMeet(int b)
    {
        return InteractionResults.STAY;
    }

    @Override
    public InteractionResults onHumanMeet(Weapon w)
    {
        if(w!=null) {
            w.damage();
            if (w.canItKillW()) return InteractionResults.KILL_T;
            else return InteractionResults.KILL_A;
        }
        else return InteractionResults.KILL_A;
    }

    @Override
    public InteractionResults onVampireMeet()
    {
        int r= ThreadLocalRandom.current().nextInt(1, 101) - boost;
        if(r<=50) return InteractionResults.KILL_A;
        else return InteractionResults.KILL_T;
    }

    /**
     *
     * @param c - "cel" obiektu wykonującego ruch
     * @return rezultat generowany przez metodę onWerewolfMeet celu
     */

    @Override
    public InteractionResults Interaction(Entity c)
    {
        return c.onWerewolfMeet(boost);
    }

    /**
     * Seria metod zwracających efekt śmierci obiektu tej klasy, zależnie od typu obiektu który go zabił
     * @return null
     */
    @Override
    public Entity onHKill()
    {
        werewolvesKilled++;
        return null;
    }

    @Override
    public Entity onVKill()
    {
        werewolvesKilled++;
        return null;
    }

    @Override
    public Entity onWKill()
    {
        return null;
    }

    /**
     *
     * @param c obiekt który ma zostać "zabity"
     * @return null
     */
    @Override
    public Entity kill(Entity c)
    {
        boost+=c.boostAlt(); //wzrost lub spadek wartosci boost
        return c.onWKill();
    }
    @Override
    public int boostAlt()
    {
        return 0;
    }

    /**
     * move - metoda losująca ruch dla danej postaci
     * age - metoda zwiększająca parametr odpowiadający za kontrole ruchu postaci
     * getAge - metoda zwracająca wspomniany parametr
     * print - metoda przechowująca symbol obiektu
     */

    @Override
    public void age()
    {
        this.gen++;
    }
    @Override
    public int getAge()
    {
        return gen;
    }

    @Override
    public String print()
    {

        return "["+ Colors.ANSI_BLUE+"W"+Colors.ANSI_RESET+"]";
    }
    @Override
    public Moves move()
    {
        return Moves.values()[ThreadLocalRandom.current().nextInt(0, 4)];
    }
}
