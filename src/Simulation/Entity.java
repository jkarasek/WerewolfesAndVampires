package Simulation;

import Simulation.Entities.Creatures.Moves;
import Simulation.Entities.Weapon;

public interface Entity
{

     String print(); //metoda odpowiadająca za przechowywanie "symbolu" obiektu

    /**
     * onXMeet - metoda określająca co zrobi dany obiekt gdy natrafi na niego obiekt typu X
     * @return rezultat interakcji
     */
    InteractionResults onVampireMeet();
    InteractionResults onWerewolfMeet(int b);
    InteractionResults onHumanMeet(Weapon c);

    /**
     * metoda uruchamiająca odpowiednią metode onXMeet zależnie od rodzaju obiektu wykonującego ruch
     * @param c - "cel" obiektu wykonującego ruch
     * @return przekazuje dalej rezultat generowany przez metode onXMeet
     */
    InteractionResults Interaction(Entity c);

    /**
     * Seria metod wywołujących efekty zabicia jednostki przez obiekt wykonujący ruch, zależnie od jego rodzaju
     * @return nowy wampir dla obiektu klasy Human, w wypadku zabicia przez obiekt klasy Vampire, null dla pozostałych
     */
    Entity onHKill();
    Entity onVKill();
    Entity onWKill();

    /**
     * Wywołanie odpowiedniego onXKill
     * @param c obiekt który ma zostać "zabity"
     * @return ewentualny nowy wampir, lub null
     */
    Entity kill(Entity c);


    /**
     * move - generacja losowego ruchu
     * age - "postarzanie" jednostki, zapobiega wykonaniu więcej niż jednego ruchu na cykl
     * getAge - zwraca "wiek" jednostki
     * @return int gen - wiek jednostki
     * pickUp - uzbrojenie obiektu w broń, tylko dla człowieka
     * boostAlt - zmiana "wzmocnienia" wikołaka
     * @return wartość int, wykorzystywana w metodzie kill() klasy Werewolf
     */

    Moves move();
    void age();
    int getAge();
    void pickUp(Entity e);
    int boostAlt();

}
