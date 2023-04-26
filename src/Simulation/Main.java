package Simulation;
import Simulation.Entities.Creatures.Human;
import Simulation.Entities.Creatures.Vampire;
import Simulation.Entities.Creatures.Werewolf;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class Main
    {

        //zamiennik dla czyszczenia ekranu, nie działającego w terminalu środowiska
    public static void moveScreen()
    {
        for (int i=0;i<5000;i++)
        {
            System.out.println();
        }
    }


        //metoda czyszcząca ekran, działa tylko w cmd
    public static void clearScreen()
    {
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system

            if(operatingSystem.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        }catch(Exception e){
            System.out.println();
        }
    }
    public static void main(String[] args) {

        //lista do której będą zapisywane informacje na temat każdego cyklu
        List<String> logs = new ArrayList<>();

    logs.add("Epoka; Ludzie; Wilkołaki; Wampiry;"+"Przemienieni ludzie;"+"Pożarci ludzie;"+"Zabite wilkołaki;"+"Zabite wampiry;"+"\r\n");

        Initiator.askInf(); //wczytanie parametrów startowych symulacji
        /**
         * y - wysokość planszy
         * x - szerokość planszy
         * winToken - przechowuje informacje z jakiego powodu zakończyła się symulacja
         */
        int y = Initiator.height;
        int x = Initiator.width;
        String winToken = null;

        /**
         * list - lista wszystkich stworzeń i broni
         * checker - informacja czy symulacja powinna dalej trwać
         */
        List<Entity> list = new ArrayList<>(Initiator.makeList());
        Simulation simulation = new Simulation(y, x, list);
        //clearScreen(); //przy uruchamianiu za pomocą IDE usunąć
        boolean checker = true;
        while (checker) {

            simulation.show(); //wyświetlanie planszy
            try {
                TimeUnit.MILLISECONDS.sleep(650);
            } catch (InterruptedException e) {

            }
            simulation.simulate(); //przeprowadzenie jednego cyklu symulacji
            //clearScreen(); //usunąć przy uruchamianiu w IDE
            moveScreen(); //dodać przy uruchamianiu w IDE
            int w = 0, v = 0, h = 0; //wewnetrzne liczniki populacji

            /**
             * zliczanie populacji poszczególnych ras
             */
            for (Entity c : simulation.tracker) {
                if (c instanceof Werewolf) w++;
                if (c instanceof Vampire) v++;
                if (c instanceof Human) h++;
            }
            System.out.println("Ilosc wilkolakow: " + w);
            System.out.println("Ilosc wampirow: " + v);
            System.out.println("Ilosc ludzi: " + h);

            /**
             * zapisywanie obecnego stanu symulacji do listy
             */
            logs.add(simulation.generation+";"+h+";"+w+";"+v+";"+Human.humansTransformed+";"+Human.humansEaten+";"+Werewolf.werewolvesKilled+";"+Vampire.vampiresKilled+"\r\n");
            /**
             * zakończenie rozgrywki gdy pozostanie jedna rasa
             * lub odbędzie się odpowiednia ilość cyklów (jeśli została wybrana taka opcja)
             */

            if ((w + v) == 0) {
                checker = false;
                winToken = "Ludzie wygrali!";
            }
            if ((h + v) == 0) {
                checker = false;
                winToken = "Wilkolaki wygraly!";
            }
            if ((w + h) == 0) {
                checker = false;
                winToken = "Wampiry wygraly!";
            }
            if(Initiator.answer.equals("t") &&simulation.generation>=Initiator.maxGen)
            {
                checker=false;
                winToken= "Symulacja osiagnela zadana liczbe cykli";
            }
            Scribe.shouter(simulation.generation); //wyświetlanie bierzącego stanu symulacji
        }


       moveScreen(); //dodać przy uruchamianiu w IDE

        /**
         * wyświetlanie wyników symulacji i zapisywanie ich do plików tekstowych
         * results.txt dla podsumowania
         * logs.txt dla danych z każdego cyklu symulacji
         */
        clearScreen();
        System.out.println(winToken);
        Scribe.shouter(simulation.generation);
        Scribe.writter(winToken, simulation.generation);
        String results = logs.stream().map(String::valueOf).collect(Collectors.joining("", "", ""));
        Scribe.save(results);
        simulation.show();

    }

}
