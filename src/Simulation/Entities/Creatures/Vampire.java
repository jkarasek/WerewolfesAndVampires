package Simulation.Entities.Creatures;

import Simulation.Entities.Colors;
import Simulation.Entities.Weapon;
import Simulation.Entity;
import Simulation.InteractionResults;
import java.util.concurrent.ThreadLocalRandom;

public class Vampire implements Entity {
    private int gen=0; //zmienna odpowiadająca za zapobiegnięcie wykonania więcej niż jednego ruchu na cykl
    public static int vampiresKilled=0; //licznik zabitych wampirów

    public Vampire()
    {

    }

    /**
     * konstuktor wykorzystywany przy zamianie człowieka w wampira
     * @param gen numer obecnego cyklu symulacji
     */
     public Vampire(int gen)
    {
        this.gen=gen;
    }

    @Override
    public void pickUp(Entity e) {}

    /**
     * Seria metod zwracających rezultaty obrania obiektu tej klasy na cel przez ruszającą się jednostkę
     * @return - rezultat interakcji typu enum
     */
    @Override
    public InteractionResults onVampireMeet()
    {
        return InteractionResults.STAY;
    }

    @Override
    public InteractionResults onHumanMeet(Weapon w)
    {
        if(w!=null)
        {
            w.damage();
            if (w.canItKillV()) return InteractionResults.KILL_T;
            else return InteractionResults.KILL_A;
        }
        else return InteractionResults.KILL_A;

    }

    @Override
    public InteractionResults onWerewolfMeet(int b)
    {
        int r= ThreadLocalRandom.current().nextInt(1, 101) - b;
        if(r<=50) return InteractionResults.KILL_T;
        else return InteractionResults.KILL_A;
    }

    /**
     *
     * @param c - "cel" obiektu wykonującego ruch
     * @return rezultat generowany przez metodę onVampireMeet celu
     */
    @Override
    public InteractionResults Interaction(Entity c)
    {
        return c.onVampireMeet();
    }

    /**
     * Seria metod zwracających efekt śmierci obiektu tej klasy, zależnie od typu obiektu który go zabił
     * @return null
     */

    @Override
    public Entity onHKill()
    {
    vampiresKilled++;
        return null;
    }

    @Override
    public Entity onVKill()
    {
        return new Vampire(getAge()+1);
    }

    @Override
    public Entity onWKill()
    {
        vampiresKilled++;
        return null;
    }
    /**
     *
     * @param c obiekt który ma zostać "zabity"
     * @return obiekt klasy Vampire w wypadku gdy Entity c jest klasy Human, w innym wypadku null
     */

    @Override
    public Entity kill(Entity c)
    {
        return c.onVKill();
    }

    /**
     *
     * @return wartość zwiększenia/zmniejszenia zmiennej boost obiektu klasy Werewolf
     */

    @Override
    public int boostAlt()
    {
    return -5;
    }

    /**
     * move - metoda losująca ruch dla danej postaci
     * age - metoda zwiększająca parametr odpowiadający za kontrole ruchu postaci
     * getAge - metoda zwracająca wspomniany parametr
     * print - metoda przechowująca symbol obiektu
     */
    @Override
    public int getAge()
    {
        return gen;
    }

    @Override
    public void age()
    {
        this.gen++;
    }
    @Override
    public Moves move() {
        return Moves.values()[ThreadLocalRandom.current().nextInt(0, 4)];
    }
    @Override
    public String print()
    {
        return "["+ Colors.ANSI_RED+"V"+Colors.ANSI_RESET+"]";

    }
}