package Simulation.Entities.Items;

import Simulation.Entities.Colors;
import Simulation.Entities.Creatures.Moves;
import Simulation.Entities.Weapon;
import Simulation.Entity;
import Simulation.InteractionResults;

public class VampireWeapon implements Weapon

{
    /**
     * konstruktor tworzący broń z wyrzymałością zależną od parametrów początkowych
     * @param d - zadana przez użytkownika wytrzymałość broni
     */
    public VampireWeapon(int d)
    {
        this.durability=d;
    }
    public int durability; //obecna wytrzymałość broni


    @Override
    public boolean canItKillV() {
        return true;
    }

    @Override
    public boolean canItKillW() {
        return false;
    }

    @Override
    public void pickUp(Entity e) {

    }
    public InteractionResults onVampireMeet()
    {
        return InteractionResults.STAY;
    }
    public InteractionResults onWerewolfMeet(int b)
    {
        return InteractionResults.STAY;
    }

    /**
     *
     * @param c broń w którą wyposażony jest człowiek, null dla braku broni
     * @return rezultat interakcji, typu enum
     */
    public InteractionResults onHumanMeet(Weapon c)
    {
        if(c!=null)  return InteractionResults.STAY;
        else return InteractionResults.PICK_UP;
    }

    public InteractionResults Interaction(Entity c){return null;}
    @Override
    public Entity onHKill()
    {
        return null;
    }

    @Override
    public Entity onVKill()
    {
        return null;
    }

    @Override
    public Entity onWKill()
    {
        return null;
    }

    @Override
    public Entity kill(Entity c)
    {
        return null;
    }
    @Override
    public int boostAlt()
    {
        return 0;
    }

    @Override
    public Moves move() {
        return null;
    }

    @Override
    public void age() {
    }

    @Override
    public int getAge() {
        return 0;
    }


    /**
     * getDur - metoda zwracająca pozostałą wytrzymałość danej broni
     * damage - metoda uszkadzająca daną broń
     * print - metoda przechowująca symbol obiektu
     */


  public void damage()
    {
        this.durability--;
    }

    public int getDur()
    {
        return durability;
    }
    public String print()
    {
        return "["+ Colors.ANSI_PURPLE+"+"+Colors.ANSI_RESET+"]";

    }
}
