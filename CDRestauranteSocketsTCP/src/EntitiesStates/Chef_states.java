package EntitiesStates;

/**
 * Enum com os estados do chef.
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public enum Chef_states {

    /**
     * Chef á espera de um pedido
     */
    WAITING_FOR_AN_ORDER,
    /**
     * Chef a preparar a comida
     */
    PREPARING_THE_COURSE,
    /**
     * Chef acabar as porções
     */
    DISHING_THE_PORTIONS,
    /**
     * Chef entregar as porções
     */
    DELIVERING_THE_PORTIONS,
    /**
     * Chef ir embora
     */
    CLOSING_SERVICE

}
