package Simulation;
import Simulation.Entities.Creatures.Moves;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Simulation
{

    /**
     * map - plansza na której przeprowadzana będzie symulacja
     * tracker - lista wszystkich stworzeń i broni, edytowana wraz z przebiegiem symulacji
     * height - wysokość planszy
     * width - szerokość planszy
     * generation - numer obecnego cyklu symulacji
     *
     */
    private final Entity[][] map;




    public final List<Entity> tracker;

    public static int weaponsPickedUp=0;


    int height;
    int width;
    int generation=0;

    /**
     * stworzenie obiektu symulacji oraz rozstawienie obiektów w losowy sposób na planszy
     * @param y - wyskość planszy
     * @param x - szerokość planszy
     * @param list - lista wszystkich stworzeń i broni
     */
    public Simulation(int y, int x, List<Entity> list) {

        this.height= y;
        this.width= x;
        this.tracker = list;
        map= new Entity[y][x];
        for (Entity c : list) {
            int ry = ThreadLocalRandom.current().nextInt(0, (this.height ));
            int rx = ThreadLocalRandom.current().nextInt(0, (this.width ));
            while (map[ry][rx] != null) {
                ry = ThreadLocalRandom.current().nextInt(0, (this.height ));
                rx = ThreadLocalRandom.current().nextInt(0, (this.width ));
            }

            map[ry][rx] = c;
        }

    }

    /**
     * wyświetlanie planszy
     */
    public void show()
    {
        for (int i = 0; i < this.height; i++)
        {
            for (int j = 0; j < this.width; j++)
            {
                if (map[i][j] != null)
                {
                    System.out.print(map[i][j].print());
                } else
                {
                    System.out.print("[ ]");
                }

            }
            System.out.println();

        }
    }

    /**
     * wykonanie jednego cyklu symulacji
     */

    public void simulate()

    {

        for (int i = 0; i < this.height; i++)
        {
            for (int j = 0; j < this.width; j++)
            {
                Entity entity = map[i][j];
                if(entity==null) continue;
                else
                {


                        Moves move = entity.move(); // wylosowanie ruchu postaci
                        int targety=i;
                        int targetx=j;

                        /**
                         * wybór pola na cel na podstawie wylosowanego wcześniej ruchu
                         */
                        try {
                            switch (move) {
                                case UP:
                                    if (targety > 0) {
                                        targety--;
                                        break;
                                    }

                                case DOWN:
                                    if (targety < this.height - 1) {
                                        targety++;
                                        break;
                                    }

                                case LEFT:
                                    if (targetx > 0) {
                                        targetx--;
                                        break;
                                    }

                                case RIGHT:
                                    if (targetx < this.width - 1) {
                                        targetx++;
                                        break;
                                    }


                            }
                        }
                        catch (NullPointerException e)
                        {}

                    InteractionResults result;


                    if(targetx==j&&targety==i) result=InteractionResults.STAY;

                    //zapobiega wykonaniu ruchu przez tą samą postać dwa razy
                    if(entity.getAge()>this.generation) continue;

                    Entity target = map[targety][targetx];


                    //wybór efektu interakcji
                    if(target!=null)
                    {
                        result = entity.Interaction(target);
                    }
                    else result=InteractionResults.MOVE;

                    //wykonanie efektu interakcji
                    try {
                        switch (result) {
                            case STAY: {
                                entity.age();

                                continue;

                            }
                            case MOVE: {
                                map[targety][targetx] = entity;
                                map[i][j] = null;
                                entity.age();

                                break;
                            }
                            case KILL_A: {

                                Entity t = target.kill(entity);
                                map[i][j] = t;
                                if (t != null) tracker.add(t);
                                tracker.remove(entity);

                                break;

                            }
                            case KILL_T: {
                                Entity t = entity.kill(target);
                                map[targety][targetx] = t;
                                if (t != null) tracker.add(t);
                                tracker.remove(target);
                                entity.age();
                                break;

                            }
                            case PICK_UP: {
                                weaponsPickedUp++;
                                entity.pickUp(target);
                                map[targety][targetx] = null;
                                map[targety][targetx] = entity;
                                map[i][j] = null;
                                entity.age();
                                break;
                            }

                        }
                    }
                    catch (NullPointerException e)
                    {}


                }
                }

            }

    this.generation++;
    }




}
