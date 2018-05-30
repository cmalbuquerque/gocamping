package BarServerSide;

import Stubs.LoggerStub;
import EntitiesStates.Chef_states;
import EntitiesStates.Student_states;
import EntitiesStates.Waiter_states;

/**
 * Classe Bar. Este tipo de dados define onde o waiter normalmente estaria a
 * trabalhar.
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class Bar {

    /**
     * Objecto do tipo LoggerStub
     */
    private LoggerStub logger;
    /**
     * boolean sinaliza que um student chegou
     */
    private boolean studentArrived;
    /**
     * boolean sinaliza que o waiter está no bar
     */
    private boolean waiterAtBar;
    /**
     * boolean sinaliza que um student chamou o waiter
     */
    private boolean studentCall;
    /**
     * boolean sinaliza que há uma porção à espera
     */
    private boolean portionWaiting;
    /**
     * boolean sinaliza que o student está pronto para pagar
     */
    private boolean readyToPay;
    /**
     * boolean sinaliza que a conta está pronta
     */
    private boolean billReady;
    /**
     * boolean sinaliza que o waiter apresentou a conta ao student
     */
    private boolean done;
    /**
     * boolean sinaliza que todos os students acabaram de comer
     */
    private boolean allFinished;

    /**
     * Boolean que guarda se o waiter pode servir ou não.
     */
    public boolean waiterCanServe;

    /**
     * Método de acesso. Método de acesso à variável que diz se o waiter pode
     * servir ou não.
     *
     * @return boolean que sinaliza que o waiter pode servir ou não.
     */
    public boolean isWaiterCanServe() {
        return waiterCanServe;
    }

    /**
     * Método de set. Método que dá set à variável que diz se o waiter pode
     * servir ou não.
     *
     * @param waiterCanServe boolean contendo o novo estado da variável.
     */
    public void setWaiterCanServe(boolean waiterCanServe) {
        this.waiterCanServe = waiterCanServe;
    }

    /**
     * Constructor da classe Inicializa as variáveis necessárias
     *
     * @param MaxStudents Número de alunos que vão comer.
     * @param logger Um objecto do tipo LogWriter
     */
    public Bar(int MaxStudents, LoggerStub logger) {
        this.logger = logger;
        this.studentArrived = false;
        this.waiterAtBar = true;
        this.studentCall = false;
        this.portionWaiting = false;
        this.readyToPay = false;
        this.billReady = false;
        this.done = false;
    }

    /**
     * Método de set. Este método serve para avisar se todos os alunos acabaram.
     * Em caso positivo notifica o waiter.
     *
     * @param state boolean cujo valor diz se todos os alunos acabaram ou não.
     */
    public synchronized void allStudentsFinished(boolean state) {
        allFinished = state;
        if (allFinished) {
            notifyAll();
        }
    }

    /*student calls */
    /**
     * Método chamado pelo student. Sinaliza ao waiter que chegou um student.
     */
    public synchronized void enter() {
        while (!waiterAtBar) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        studentArrived = true;
        waiterAtBar = false;
        notifyAll();
    }

    /**
     * Método chamado por o primeiro student a chegar. Sinaliza ao waiter que
     * tem o pedido pronto.
     */
    public synchronized void callTheWaiter() {
        studentCall = true;
        notifyAll();
    }

    /**
     * Método chamado por um aluno. Sinaliza que todos acabaram de comer a sua
     * porção.
     */
    public synchronized void signalTheWaiter() {
        setWaiterCanServe(true);
        notifyAll();

    }

    /**
     * Método chamado pelo ultimo aluno a chegar. Sinalizar que está pronto para
     * pagar a conta.
     *
     * @param studentID ID do student que chamou a função.
     */
    public synchronized void shoudHaveArrivedEarlier(int studentID) {
        while (!allFinished) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        setWaiterCanServe(true);
        logger.UpdateStudentState(Student_states.PAYING_THE_MEAL, studentID);
        readyToPay = true;
        notifyAll();
        honorTheBill();
    }

    /**
     * Método chamado pelo ultimo aluno a chegar. Enquanto este espera que o
     * waiter tenha a conta pronta Bloqueia á espera.
     */
    public synchronized void honorTheBill() {
        while (!done) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }

    }

    /*
    Waiter calls this
     */
    /**
     * Método principal do waiter no bar. Bloqueia a espera enquanto ninguém o
     * chama e consoante quem o chama e o estado de variáveis internas retorna
     * vários valores que levam a que este faça diferentes ações.
     *
     * @return <ul> <li> 0, se for para ir daro menu a um student. </li>
     * <li> 1, se for para ir receber o pedido. </li>
     * <li> 2, se for para ir buscar porções á kitchen. </li>
     * <li> 3, se o student pedir a conta. </li>
     * <li> 4, se for preparar a conta. </li>
     * <li> 5, se for apresentar a conta ao student. </li>
     * <li> 20, default value. Não tem nenhum valor. </li>
     * </ul>
     */
    public synchronized int lookArround() {
        if (!studentArrived && !studentCall && !(portionWaiting && waiterCanServe) && !readyToPay && !billReady && !done) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        if (studentArrived) {
            studentArrived = false;
            waiterAtBar = false;
            return 0;
        } else if (studentCall) {
            studentCall = false;
            return 1;
        } else if ((portionWaiting && waiterCanServe) && !readyToPay) {
            portionWaiting = false;
            return 2;
        } else if (readyToPay) {
            waiterCanServe = false;
            readyToPay = false;
            return 3;
        } else if (billReady) {
            billReady = false;
            return 4;
        } else if (done) {
            return 5;
        }
        return 20;

    }

    /**
     * Métoso chamado pelo waiter. Chamado após ser sinalizado que o student
     * está pronto para pagar.
     */
    public synchronized void prepareTheBill() {
        logger.UpdateWaiterState(Waiter_states.PROCESSING_THE_BILL);
        billReady = true;

    }

    /**
     * Método chamado pelo waiter. Para acabar de preparar a conta e apresentar
     * esta ao student.
     */
    public synchronized void presentTheBill() {
        done = true;
        logger.UpdateWaiterState(Waiter_states.RECEIVING_PAYMENT);
        notifyAll();

    }

    /**
     * Método chamado pelo waiter. Sempre que este retorna ao bar chama este
     * método.
     */
    public synchronized void returnToTheBar() {
        logger.UpdateWaiterState(Waiter_states.APPRAISING_SITUATION);
        waiterAtBar = true;
        notifyAll();
    }

    /*
    Chef calls this
     */
    
    /**
     * Método chamado pelo chef. Sinaliza ao waiter que mais uma porção está
     * pronta.
     */
    public synchronized void alertTheWaiter() {
        while (!waiterCanServe) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        portionWaiting = true;
        logger.UpdateChefState(Chef_states.DELIVERING_THE_PORTIONS);
        notifyAll();
    }

}
