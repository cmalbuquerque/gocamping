package TableServerSide;

import CommonInfra.Definitions;
import Stubs.LoggerStub;
import Stubs.BarStub;
import EntitiesStates.Student_states;
import EntitiesStates.Waiter_states;
import CommonInfra.MemFIFO;
import java.util.HashMap;

/**
 * Classe Table. Este tipo de dados define onde os students passariam a maior
 * parte do tempo.
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class Table {

    private int StuOrder;
    private LoggerStub logger;
    private MemFIFO waitingForMenu;
    private HashMap<Integer, Boolean> studentGotMenu;
    private HashMap<Integer, Boolean> canEatNextPortion;
    private int numChoices;
    private boolean waiterReadyToRecieveOrder;
    private boolean orderDescribed;
    private int portionOnTable;
    private int numStuFinishedPortion;
    private int readyToGo;
    private final int MaxStudents;
    private BarStub bar;

    /**
     * Define se todos os students acabaram de comer ou não. Pode tomar 2
     * valores: true caso tenham acabado, false caso contrário.
     */
    public boolean allFinished;

    /**
     * Constructor da classe Inicializa as variáveis necessárias
     *
     * @param MaxStudents Número de students que vai comer ao restaurante.
     * @param courses Número de pratos que serão comidos.
     * @param bar Um objecto do tipo Bar.
     * @param logger Um objecto do tipo LogWriter.
     */
    public Table(int MaxStudents, int courses, BarStub bar, LoggerStub logger) {
        this.StuOrder = 0;
        this.logger = logger;
        this.studentGotMenu = new HashMap<Integer, Boolean>();
        this.canEatNextPortion = new HashMap<Integer, Boolean>();
        this.waitingForMenu = new MemFIFO(MaxStudents);
        this.numChoices = 0;
        this.waiterReadyToRecieveOrder = false;
        this.orderDescribed = false;
        this.portionOnTable = 0;
        this.numStuFinishedPortion = 0;
        this.allFinished = false;
        this.readyToGo = 0;
        this.MaxStudents = MaxStudents;
        this.bar = bar;
    }

    /**
     * Método chamado pelo student quando este chega. Altera o seu estado para
     * refletir que chegou retorna a ordem de chegada de cada aluno
     *
     * @param studentID ID do student que chamou o método.
     * @return Um inteiro que sinaliza a ordem de chegada do student.
     */
    public synchronized int enter(int studentID) {
        logger.UpdateStudentState(Student_states.TAKING_A_SEAT_AT_THE_TABLE, studentID);
        studentGotMenu.put(studentID, false);
        canEatNextPortion.put(studentID, false);
        waitingForMenu.write(studentID);
        StuOrder++;
        return StuOrder;
    }

    /**
     * Método chamado pelo student que espera que o waiter lhe traga um menu.
     * Após isso altera o seu estado para sinalizar que está a escolher oq ue
     * comer.
     *
     *
     * @param studentID ID do student que chamou o método.
     */
    public synchronized void readTheMenu(int studentID) {
        while (!studentGotMenu.get(studentID)) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        logger.UpdateStudentState(Student_states.SELECTING_THE_COURSES, studentID);

    }

    /**
     * Método privado que verifica se já todos os students escolheram o prato.
     *
     * @return <ul> <li> true, em caso positivo. </li>
     * <li> false, em caso contrário. </li> </ul>
     */
    private boolean hasEverybodyChosen() {
        return numChoices == MaxStudents;
    }

    /**
     * Método chamado pelo primeiro aluno a chegar. Este começa então a preparar
     * o pedido. Espera que todos escolham e sinalizem que já o fizeram. Altera
     * o seu estado para refletir isso.
     *
     * @param studentID ID do student que chamou o método.
     */
    public synchronized void prepareTheOrder(int studentID) {
        logger.UpdateStudentState(Student_states.ORGANIZING_THE_ORDER, studentID);
        numChoices++;
        while (!hasEverybodyChosen()) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
    }

    /**
     * Método chamado pelo primeiro aluno a chegar. Avisa o waiter que todos
     * escolheram e o pedido está pronto.
     */
    public synchronized void describeTheOrder() {
        while (!waiterReadyToRecieveOrder) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        orderDescribed = true;
        notifyAll();

    }

    /**
     * Método chamado pelo primeiro student a chegar. Após descrever a order ao
     * waiter chama este método. Altera o seu estado para refletir que já fez o
     * pedido e está, tal como os outros à espera e à conversa.
     *
     * @param studentID ID do student que chamou o método.
     */
    public void joinTheTalk(int studentID) {
        logger.UpdateStudentState(Student_states.CHATTING_WITH_COMPANIONS, studentID);
        bar.setWaiterCanServe(true);

    }

    /**
     * Método chamado por todos os students menos o primeiro. Informam o
     * primeiro a chegar que escolheram alteram o seu estado para refletir que
     * estão à espera da comida.
     *
     * @param studentID ID do student que chamou o método.
     */
    public synchronized void informCompanion(int studentID) {
        numChoices++;
        logger.UpdateStudentState(Student_states.CHATTING_WITH_COMPANIONS, studentID);
        notifyAll();

    }

    /**
     * Método chamado pelos sudents para começarem a comer. Se não houver comida
     * ainda, bloqueiam à espera que esta chege. Após esta chegar altera o seu
     * estado para refletir que estão a comer
     *
     * @param studentID ID do student que chamou o método.
     */
    public synchronized void startEating(int studentID) {
        while (!(canEatNextPortion.get(studentID))) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        logger.UpdateStudentState(Student_states.ENJOYING_THE_MEAL, studentID);

    }

    /**
     * Método chamado pelos students. Simula que acabaram de comer uma porção.
     * Altera o seu estado para refletir que estão à conversa.
     *
     * @param studentID ID do student que chamou o método.
     */
    public synchronized void endEating(int studentID) {
        try {
            Thread.sleep((long) (200 * Math.random() + 1));
        } catch (InterruptedException ex) {
        }
        logger.UpdateStudentState(Student_states.CHATTING_WITH_COMPANIONS, studentID);
        numStuFinishedPortion++;
        if (numStuFinishedPortion == Definitions.MAXSTUDENTS) {
            allFinished = true;
            bar.setAllStudentsFinished(allFinished);
        }
        canEatNextPortion.put(studentID, false);
        notifyAll();

    }

    /**
     * Método que verifica se já todos os students acabaram a sua porção.
     * Retorna um boleano conforme o resultado.
     *
     * @return <ul> <li> true, se já todos tiverem acabado a sua porção. </li>
     * <li> false, em caso contrário. </li> </ul>
     */
    public synchronized boolean hasEverybodyFinished() {
        boolean val = allFinished;
        if (allFinished) {
            bar.setAllStudentsFinished(allFinished);
            allFinished = false;
            portionOnTable = 0;
            numStuFinishedPortion = 0;
        }
        return val;
    }

    /**
     * Método chamado pelos students. Após comerem e antes de irem embora
     * bloqueiam á espera que todos acabem e que o ultimo a chegar pague a
     * sonta, indo embora apenas após isso.
     *
     *
     * @param studentID ID do student que chamou o método.
     */
    public synchronized void exit(int studentID) {
        readyToGo++;
        notifyAll();
        while (readyToGo != 7) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        logger.UpdateStudentState(Student_states.GOING_HOME, studentID);

    }

    /* Métodos do waiter */
    /**
     * Método chamado pelo waiter. Se há pelo menos um aluno à espera de receber
     * o menu altera o seu estado para sinalizar que está a dar menus.
     *
     */
    public synchronized void saluteTheClient() {
        int sID = waitingForMenu.read();
        if (sID != -1) {
            studentGotMenu.put(sID, true);
        }
        logger.UpdateWaiterState(Waiter_states.PRESENTING_THE_MENU);
        notifyAll();

    }

    /**
     * Método chamado pelo waiter. Quando este é sinalizado pelo primeiro aluno
     * a chegar que o pedido está pronto altera o seu estado para sinalizar que
     * está a receber o pedido.
     *
     */
    public synchronized void getThePad() {
        waiterReadyToRecieveOrder = true;
        logger.UpdateWaiterState(Waiter_states.TAKING_THE_ORDER);
        notifyAll();
    }

    /**
     * Método chamado pelo waiter. Entrega uma porção a um student.
     *
     */
    public synchronized void deliverPortion() {
        while (!bar.isWaiterCanServe()) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        portionOnTable++;
    }

    /**
     * Método que verifica se todos os alunos já foram servidos. retorna um
     * boleano conforme o resultado.
     *
     * @return  <ul> <li> true, se já todos tiverem sido servidos. </li>
     * <li> false, em caso contrário. </li> </ul>
     */
    public synchronized boolean haveAllClientsBeenServed() {
        boolean val = portionOnTable == MaxStudents;
        if (val) {
            bar.setWaiterCanServe(false);
            for (Integer key : canEatNextPortion.keySet()) {
                canEatNextPortion.put(key, true);
            }
            notifyAll();
        }
        return val;
    }

}
