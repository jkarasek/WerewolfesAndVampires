package Simulation;

import Simulation.Entities.Creatures.Human;
import Simulation.Entities.Creatures.Vampire;
import Simulation.Entities.Creatures.Werewolf;
import Simulation.Entities.Items.VampireWeapon;
import Simulation.Entities.Items.WerewolfWeapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Initiator
{

  public static int height,width,wCount,vCount,hCount,wWeaponCount,vWeaponCount,vWepDur,wWepDur, maxGen;
  public static String answer=".";
  private final static List<Entity> list = new ArrayList<>();
   static Scanner scanner = new Scanner(System.in);

    /**
     * pobranie parametrów początkowych symulacji od użytkownika
     */
    public static void askInf()
    {
        try {
            while(!answer.equals("t") && !answer.equals("n"))
            {
                System.out.println("Czy symulacja ma trwac okreslona liczbe cykli? [t/n] ");
                answer = scanner.nextLine();
                if(!answer.equals("t") && !answer.equals("n"))
                {
                    //System.out.println(answer);
                    System.out.println("Bledna odpowiedz, ponow probe");
                }
            }
            System.out.println("Podaj parametry gry:");
            if (answer.equals("t")) {
                System.out.println("Podaj ilosc cykli: ");
                maxGen = scanner.nextInt();
            } else
            {
                maxGen = 0;

            }
            System.out.println("Wielkosc mapy y:");
            height = scanner.nextInt();
            System.out.println("Wielkosc mapy x:");
            width = scanner.nextInt();
            System.out.println("Ilosc wilkolakow");
            wCount = scanner.nextInt();
            System.out.println("Ilosc wampirow");
            vCount = scanner.nextInt();
            System.out.println("Ilosc ludzi");
            hCount = scanner.nextInt();
            System.out.println("Ilosc broni na wilkolaki");
            wWeaponCount = scanner.nextInt();
            if (wWeaponCount > 0) {
                System.out.println("Wytrzymalosc broni na wilkolaki");

                wWepDur = scanner.nextInt();
            } else {
                wWepDur = 0;
            }
            System.out.println("Ilosc broni na wampiry");
            vWeaponCount = scanner.nextInt();
            if (vWeaponCount > 0) {
                System.out.println("Wytrzymalosc broni na wampiry");
                vWepDur = scanner.nextInt();
            } else {
                vWepDur = 0;

            }
            if ((wCount + vCount + hCount + wWeaponCount + vWeaponCount) > (height * width)) {
                System.out.println("Bledne dane wejsciowe, ponow probe");
                askInf();
            }
            if(wCount<0||vCount<0||hCount<0||wWeaponCount<0||vWeaponCount<0||height<=0||width<=0||wWepDur<0||vWepDur<0)
            {
                System.out.println("Bledne dane wejsciowe, ponow probe");
                askInf();
            }
        }
        catch (Exception e)
        {
            System.out.println("Bledne dane wejsciowe");
            System.exit(0);
        }

    }

    /**
     *  stworzenie listy na podstawie parametrów początkowych
     * @return lista wszystkich stworzeń i broni
     */
    public static List<Entity> makeList()
    {

        for(int i=0;i<Initiator.hCount;i++)
        {
            Human human = new Human();
            list.add(human);

        }
        for(int i=0;i<Initiator.wCount;i++)
        {
            Werewolf werewolf = new Werewolf();
            list.add(werewolf);

        }
        for(int i=0;i<Initiator.vCount;i++)
        {
            Vampire vampire = new Vampire();
            list.add(vampire);

        }

        for(int i=0;i<Initiator.vWeaponCount;i++)
        {
            VampireWeapon vW = new VampireWeapon(vWepDur);
            list.add(vW);


        }
        for(int i=0;i<Initiator.wWeaponCount;i++)
        {
            WerewolfWeapon wW = new WerewolfWeapon(wWepDur);
            list.add(wW);


        }
        return list;
    }

}
