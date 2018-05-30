package EntitiesStates;

/**
 * Enum com os estados do student.
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public enum Student_states {

    /**
     * Student a ir para o restaurante
     */
    GOING_TO_THE_RESTAURANT,
    /**
     * Student a sentar-se á mesa
     */
    TAKING_A_SEAT_AT_THE_TABLE,
    /**
     * Student a escolher o que comer
     */
    SELECTING_THE_COURSES,
    /**
     * primeiro Student a chegar a preparar o pedido
     */
    ORGANIZING_THE_ORDER,
    /**
     * Student á espera de comida enquanto conversa
     */
    CHATTING_WITH_COMPANIONS,
    /**
     * Student a comer
     */
    ENJOYING_THE_MEAL,
    /**
     * ultimo Student a chegar a pagar a refeição de todos
     */
    PAYING_THE_MEAL,
    /**
     * Student a ir embora
     */
    GOING_HOME

}
