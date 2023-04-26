package Simulation.Entities.Creatures;
import Simulation.Entities.Colors;
import Simulation.Entities.Items.VampireWeapon;
import Simulation.Entities.Items.WerewolfWeapon;
import Simulation.Entities.Weapon;
import Simulation.Entity;
import Simulation.InteractionResults;


import java.util.concurrent.ThreadLocalRandom;

public class Human implements Entity {

    public Weapon weapon = null; //informacja na temat tego czy i jaką broń posiada człowiek

    private int gen=0; //zmienna odpowiadająca za zapobiegnięcie wykonania więcej niż jednego ruchu na cykl
    public static int humansTransformed=0; //licznik ludzi zabitych przez wampiry
    public static int humansEaten=0; //licznik ludzi zabitych przez wilkołaki

    public void destroyWeapon()
  {
      if(weapon.getDur()<1) weapon=null;
  } //metoda niszcząca broń
    @Override
    public void pickUp(Entity e)
    {
    weapon=(Weapon)e;
    } //metoda uzbrajająca człowieka w broń

    /**
     * Seria metod zwracających rezultaty obrania obiektu tej klasy na cel przez ruszającą się jednostkę
     * @param w - broń w którą jest uzbrojony człowiek, może być null
     * @return - rezultat interakcji typu enum
     *
     */
    @Override
    public InteractionResults onHumanMeet(Weapon w)
    {
        return InteractionResults.STAY;
    }
    @Override
    public InteractionResults onVampireMeet()
    {
        if(weapon!=null)
        {
            if(weapon.canItKillV()) return InteractionResults.KILL_A;
            else return InteractionResults.KILL_T;
        }
        else return InteractionResults.KILL_T;
    }

    /**
     *
     * @param b - wartość zmiennej boost
     * @return - rezultat interakcji typu enum
     */
    @Override
    public InteractionResults onWerewolfMeet(int b) {
        if(weapon!=null)
        {
            if(weapon.canItKillW()) return InteractionResults.KILL_A;
            else return InteractionResults.KILL_T;
        }
        else return InteractionResults.KILL_T;
    }

    /**
     *
     * @param c - "cel" obiektu wykonującego ruch
     * @return rezultat generowany przez metodę onHumanMeet celu
     */
    @Override
    public InteractionResults Interaction(Entity c)
    {
        InteractionResults r=c.onHumanMeet(weapon);
        if(weapon!=null)  {destroyWeapon();}
        return r;
    }

    /**
     * Seria metod zwracających efekt śmierci obiektu tej klasy, zależnie od typu obiektu który go zabił
     * @return null lub nowy obiekt klasy Vampire
     */

    @Override
    public Entity onHKill()
    {
      return null;
    }

    @Override
    public Entity onVKill()
    {
        humansTransformed++;
        return new Vampire(getAge()+1);
    }

    @Override
    public Entity onWKill()
    {
        humansEaten++;
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
        return c.onHKill();
    }

    /**
     *
     * @return wartość zwiększenia/zmniejszenia zmiennej boost obiektu klasy Werewolf
     */

    @Override
    public int boostAlt()
    {
        return 10;
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
    public Moves move()
    {

        return Moves.values()[ThreadLocalRandom.current().nextInt(0, 4)];
    }
    @Override
    public String print()
    {
        if(weapon instanceof VampireWeapon) return "["+ Colors.ANSI_PURPLE+"H"+Colors.ANSI_RESET+"]";
        if(weapon instanceof WerewolfWeapon) return "["+Colors.ANSI_CYAN+"H"+Colors.ANSI_RESET+"]";
        return "["+Colors.ANSI_YELLOW+"H"+Colors.ANSI_RESET+"]";
    }
}
