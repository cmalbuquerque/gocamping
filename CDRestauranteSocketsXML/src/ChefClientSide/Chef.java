package ChefClientSide;

import EntitiesStates.Chef_states;
import Stubs.BarStub;
import Stubs.KitchenStub;

/**
 * Define um objecto do tipo Chef.
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class Chef extends Thread implements Runnable {
    /**
     * state Guarda o estado do Chef.
     */
    private Chef_states state;
    /**
     * kithen guarda um objecto do tipo KitchenStub.
     */
    private KitchenStub kitchen;
    /**
     *  bar Guarda um objecto do tipo Bar.
     */
    private BarStub bar;
    /**
     * Guarda o número de course actual.
     */
    private int numPratoActual;
    /**
     * Número total de courses a serem consumidos.
     */
    private final int courses;
    /**
     * Número de students que vão comer.
     */
    private final int maxStudents;

    /**
     * Constructor da classe. Inicializa as variáveis necessárias
     *
     * @param kitchen Objecto do tipo Kitchen.
     * @param bar Objecto do tipo Bar.
     * @param courses Número de pratos a serem consumidos.
     * @param maxStudents Número de students que vão comer.
     */
    public Chef(KitchenStub kitchen, BarStub bar, int courses, int maxStudents) {
        this.state = Chef_states.WAITING_FOR_AN_ORDER;
        this.kitchen = kitchen;
        this.bar = bar;
        this.numPratoActual = 0;
        this.courses = courses;
        this.maxStudents = maxStudents;
    }

    @Override
    public void run() {
        kitchen.watchTheNews();
        kitchen.startPreparation();

        for (numPratoActual = 0; numPratoActual < courses; numPratoActual++) {
            kitchen.procedToPresentation();
            for (int currentStudent = 0; currentStudent < maxStudents; currentStudent++) {
                bar.alertTheWaiter();
                if (!kitchen.haveAllPortionsBeenDelivered()) {
                    kitchen.haveNextPortionReady();
                }
            }
            if (!kitchen.hasTheOrderBeenCompleted()) {
                kitchen.continuePreparation();
            }
        }
        kitchen.cleanUp();
    }

    /**
     * Método que retorna o estado do chef.
     *
     * @return O estado actual do chef.
     */
    public Chef_states getChefState() {
        return state;
    }

    /**
     * Método que permite da rum novo estado ao chef.
     *
     * @param state Estado para o qual o chef deve mudar.
     */
    public void setChefState(Chef_states state) {
        this.state = state;
    }

}
