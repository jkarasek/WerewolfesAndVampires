package Simulation;

import Simulation.Entities.Creatures.Human;
import Simulation.Entities.Creatures.Vampire;
import Simulation.Entities.Creatures.Werewolf;

import java.io.File;
import java.io.FileWriter;
import java.util.Formatter;


public class Scribe {

    /**
     * wyświetlenie podsumowania symulacji
     * @param token - informacja na temat tego jak zakończyła się symulacja (winToken)
     * @param counter - numer obecnego cyklu symulacji
     */


    /**
     * wyświetlanie obecnego stanu symulacji
     * @param counter - numer obecnego cyklu symulacji
     */

    public static void shouter(int counter)
    {
        System.out.println("Symulacja trwa: " + counter + " tur");
        System.out.println(Human.humansTransformed + " Ludzi zostalo przemienionych w wampiry");
        System.out.println(Human.humansEaten+ " Ludzi zostalo pozartych przez wilkolaki");
        System.out.println(Werewolf.werewolvesKilled + " Wilkolakow zostalo zabitych");
        System.out.println(Vampire.vampiresKilled + " Wampirow zostalo zabitych");
        System.out.println(Simulation.weaponsPickedUp + " Sztuk(i) broni zostalo podniesionych");

    }


    /**
     * zapisywanie podsumowania symulacji do pliku tekstowego
     *@param token - informacja na temat tego jak zakończyła się symulacja (winToken)
     *@param counter - numer obecnego cyklu symulacji
     */
    public static void writter(String token, int counter) {

        File results = new File("results.txt");
        if (!results.exists()) {
            try {
                results.createNewFile();
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        if (results.canWrite()) {
            try {

                FileWriter resultsW = new FileWriter(results, false);
                Formatter resultsFM = new Formatter(resultsW);
                resultsFM.format("%s \r\n", token);
                resultsFM.format("%s \r\n", "Symulacja trwala: " + counter + " tur");
                resultsFM.format("%s \r\n", "Poczatkowa liczba ludzi wynosila: " + Initiator.hCount);
                resultsFM.format("%s \r\n", "Poczatkowa liczba wilkolakow wynosila: " + Initiator.wCount);
                resultsFM.format("%s \r\n", "Poczatkowa liczba wampirow wynosila: " + Initiator.vCount);
                resultsFM.format("%s \r\n", "Poczatkowa liczba broni na wilkolaki wynosila: " + Initiator.wWeaponCount);
                resultsFM.format("%s \r\n", "Poczatkowa liczba broni na wampiry wynosila: " + Initiator.vWeaponCount);
                resultsFM.format("%s \r\n", Human.humansTransformed + " Ludzi zostalo przemienionych w wampiry");
                resultsFM.format("%s \r\n", Human.humansEaten+ " Ludzi zostalo pozartych przez wilkolaki");
                resultsFM.format("%s \r\n", Werewolf.werewolvesKilled + " Wilkolakow zostalo zabitych");
                resultsFM.format("%s \r\n", Vampire.vampiresKilled + " Wampirow zostalo zabitych");
                resultsFM.format("%s \r\n", Simulation.weaponsPickedUp + " Sztuk(i) broni zostalo podniesionych");
                resultsW.close();
                resultsFM.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * zapisanie stworzonej wcześniej listy do pliku tekstowego
     * @param data - lista przechowująca wyniki z każdego cyklu symulacji
     */
    public static void save(String data) {
        File logs = new File("logs.txt");
    //    System.out.println(logs.getAbsolutePath());
        if (!logs.exists()) {
            try {
                logs.createNewFile();
            } catch (Exception e) {
                System.out.println("nie można stworzyć pliku");
            }
        }
        if (logs.canWrite()) {
            try {
                FileWriter logsW = new FileWriter(logs, false);
                logsW.write(data);
                logsW.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    }




