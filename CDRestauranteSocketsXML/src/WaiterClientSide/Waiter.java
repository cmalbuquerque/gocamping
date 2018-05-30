package WaiterClientSide;

import EntitiesStates.Waiter_states;
import Stubs.*;

/**
 * Define um objecto do tipo Waiter.
 *
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class Waiter extends Thread implements Runnable {

    /**
     * state representa o estado do waiter
     */
    private Waiter_states state;
    /**
     * kitchen Objecto do tipo KitchenStub
     */
    private KitchenStub kitchen;
    /**
     * bar Objecto do tipo BarStub
     */
    private BarStub bar;
    /**
     * table Objecto do tipo TableStub
     */
    private TableStub table;


    /**
     * Constructor da classe. Inicializa as variáveis necessárias
     *
     * @param kitchen Objecto do tipo KitchenStub.
     * @param bar Objecto do tipo BarStub
     * @param table Objecto do tipo TableStub.
     */
    public Waiter(KitchenStub kitchen, BarStub bar, TableStub table) {
        this.state = Waiter_states.APPRAISING_SITUATION;
        this.kitchen = kitchen;
        this.bar = bar;
        this.table = table;
    }

    @Override
    public void run() {
        int alter;
        while ((alter = bar.lookArround()) != 5) {
            switch (alter) {
                case 0:
                    table.saluteTheClient();
                    break;
                case 1:
                    table.getThePad();
                    kitchen.handTheNoteToTheChef();
                    break;
                case 2:
                    do {
                        kitchen.collectPortion();
                        table.deliverPortion();
                    } while (!table.haveAllClientsBeenServed());
                    break;
                case 3:
                    bar.prepareTheBill();
                    break;
                case 4:
                    bar.presentTheBill();
            }
            if (alter != 5 && alter != 20) {
                bar.returnToTheBar();
            }
        }
    }

    /**
     * Método que retorna o estado do waiter.
     *
     * @return O estado actual do waiter.
     */
    public Waiter_states getWaiterState() {
        return state;
    }

    /**
     * Método que permite dar um novo estado ao waiter.
     *
     * @param state Estado para o qual o waiter deve mudar.
     */
    public void setWaiterState(Waiter_states state) {
        this.state = state;
    }

}
