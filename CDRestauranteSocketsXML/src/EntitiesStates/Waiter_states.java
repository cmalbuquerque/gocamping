package EntitiesStates;

/**
 * Enum com os estados do waiter.
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public enum Waiter_states {

    /**
     * Waiter no bar á espera de ser chamado
     */
    APPRAISING_SITUATION,
    /**
     * Waiter a apresentar o menu aos students
     */
    PRESENTING_THE_MENU,
    /**
     * Waiter a receber o pedido
     */
    TAKING_THE_ORDER,
    /**
     * Waiter a dar o pedido ao chef
     */
    PLACING_THE_ORDER,
    /**
     * Waiter á espera de uma porção do chef e a entregar aos alunos
     */
    WAITING_FOR_PORTION,
    /**
     * Waiter tratar da conta
     */
    PROCESSING_THE_BILL,
    /**
     * Waiter a receber o pagamento
     */
    RECEIVING_PAYMENT

}
