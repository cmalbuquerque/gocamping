package KitchenServerSide;

import Stubs.LoggerStub;
import EntitiesStates.Chef_states;
import EntitiesStates.Waiter_states;

/**
 * Classe kitchen. Este tipo de dados define onde o chef normalmente estaria a
 * trabalhar.
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class Kitchen {
    /**
     * LoggerStub Um objecto do tipo LoggerStub.
     */
    private LoggerStub logger;
    /**
     * boolean Sinaliza se existe uma order.
     */
    private boolean gotAnOrder;
    /**
     * int sinaliza o número de portion que o chef já deu dish num course.
     */
    private int dishedPortions;
    /**
     * boolean Sinaliza se há alguma porção pronta.
     */
    private boolean portionReady;
    /**
     * boolean Sinaliza se o waiter deu colect à ultima porção.
     */
    private boolean waiterColectedPortion;
    /**
     * int Sinaliza o course actual.
     */
    private int currentCourse;
    /**
     * Guarda o número de students que vão comer.
     */
    private final int maxStudents;
    /**
     * Guarda o número de courses que vão ser consumidos.
     */
    private final int courses;

    /**
     * Constructor da classe. Inicializa as variáveis internas necessárias.
     *
     * @param MaxStudents Número de students que vão comer
     * @param courses Número de pratos da refeição
     * @param logger Um objecto do tipo LogWriter
     */
    public Kitchen(int MaxStudents, int courses, LoggerStub logger) {
        this.logger = logger;
        this.maxStudents = MaxStudents;
        this.gotAnOrder = false;
        this.dishedPortions = 0;
        this.portionReady = false;
        this.waiterColectedPortion = false;
        this.currentCourse = 1;
        this.courses = courses;
    }

    /**
     *
     * Método chamado pelo waiter. Sinaliza ao chef que este já tem um pedido.
     * SAltera o seu estado para refletir a mudança
     *
     *
     */
    public synchronized void handTheNoteToTheChef() {
        logger.UpdateWaiterState(Waiter_states.PLACING_THE_ORDER);
        gotAnOrder = true;
        notifyAll();

    }

    /**
     * Método chamado pelo waiter. Onde este vai buscar uma porção para servir
     * aos students- Bloqueia enquando o chef não tem alguma porção pronta-
     * Altera os eu estado para refletir a mudança
     *
     */
    public synchronized void collectPortion() {
        while (!portionReady) {
            try {
                wait();

            } catch (InterruptedException ex) {
            }
        }
        logger.UpdateWaiterState(Waiter_states.WAITING_FOR_PORTION);
        waiterColectedPortion = true;
        portionReady = false;
        notifyAll();
    }

    /**
     * Método inicial do chef. Bloqueia enquanto espera que o waiter lhe traga
     * algum pedigo
     *
     */
    public synchronized void watchTheNews() {
        while (!gotAnOrder) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
    }

    /**
     * Método chamado pelo chef. Altera o seu estado para refletir que está a
     * preparar os pratos
     *
     */
    public void startPreparation() {
        logger.UpdateChefState(Chef_states.PREPARING_THE_COURSE);
        try {
            Thread.sleep((long) (150 * Math.random() + 1));
        } catch (InterruptedException ex) {
        }
    }

    /**
     * Método chamado pelo chef. Após ter praparado os pratos e estar pronto
     * para os servir. Altera o seu estado para refletir a mudança
     *
     */
    public synchronized void procedToPresentation() {
        logger.UpdateChefState(Chef_states.DISHING_THE_PORTIONS);
        dishedPortions = 0;
        dishedPortions++;
        portionReady = true;
        waiterColectedPortion = false;
        logger.UpdateCourse(currentCourse);
        notifyAll();
    }

    /**
     * Método que verifica se todas as porções já foram agarradas pelo waiter.
     *
     * Retorna um boleano conforme o resultado
     *
     * @return <ul> <li> true, se já todas as porções tiverem sido servidas. </li>
     * <li> false, em caso contrário </li> </ul>
     */
    public synchronized boolean haveAllPortionsBeenDelivered() {
        while (!waiterColectedPortion && portionReady) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        boolean val = (dishedPortions == maxStudents);
        return val;
    }

    /**
     * Método chamado pelo chef. Informa o waiter que tem mais uma porção
     * pronta. Altera o seu estado para refletir que está a "Dishing The
     * Portions".
     *
     */
    public synchronized void haveNextPortionReady() {
        logger.UpdateChefState(Chef_states.DISHING_THE_PORTIONS);
        portionReady = true;
        waiterColectedPortion = false;
        dishedPortions++;
        notifyAll();
    }

    /**
     * Método que verifica se todos os pratos foram servidos.
     *
     * retorna um boleano conforme o resultado
     *
     * @return <ul> <li> true, se já tiver servido 3 pratos. </li>
     * <li> false, em caso contrário. </li> </ul>
     */
    public boolean hasTheOrderBeenCompleted() {
        boolean val = (currentCourse == courses);
        if (!val) {
            currentCourse++;
        }
        return val;
    }

    /**
     * Método chamado pelo chef. Caso seja chamado ainda faltam preparar pratos,
     * então, altera o seu estado para refletir isso.
     *
     */
    public synchronized void continuePreparation() {
        logger.UpdateChefState(Chef_states.PREPARING_THE_COURSE);
    }

    /**
     * Método chamado pelo chef. No final, após todos os pratos terem sido
     * feitos e entregues ao waiter altera o sue estado para reflectir que se
     * está a ir embora.
     *
     */
    public synchronized void cleanUp() {
        try {
            Thread.sleep((long) (100 * Math.random() + 1));
        } catch (InterruptedException ex) {
        }
        logger.UpdateChefState(Chef_states.CLOSING_SERVICE);

    }
}
