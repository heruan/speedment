package com.speedment.core.db.crud.impl;

import com.speedment.core.config.model.Column;
import com.speedment.core.db.crud.Selector;
import com.speedment.core.field.*;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 * The default implementation of the {@link Selector} interface.
 *
 * @author Emil
 */
public final class SelectorImpl implements Selector {

    private final Column column;
    private final Operator operator;
    private final Object operand;

    /**
     * SelectorImpl should be constructed using the appropriate static class.
     *
     * @param column    the column to compare
     * @param operator  the operator to use when comparing
     * @param operand   the operand to compare to, or {@code null} if the operator is unary
     */
    private SelectorImpl(Column column, Operator operator, Object operand) {
        this.column   = requireNonNull(column);
        this.operator = requireNonNull(operator);
        this.operand  = operand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Column getColumn() {
        return column;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Operator getOperator() {
        return operator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Object> getOperand() {
        return Optional.ofNullable(operand);
    }

    /**
     * Constructs a new {@link Selector} based on a binary predicate and a value to test it against.
     *
     * @param predicate  the predicate
     * @param value      the value to test against
     * @param <ENTITY>   the type of the entity
     * @param <V>        the type of the value
     * @return           the constructed {@link Selector}
     * @see              BinaryPredicateBuilder
     */
    public static <ENTITY, V extends Comparable<V>> Selector fromBinaryPredicate(BinaryPredicateBuilder<ENTITY, V> predicate, V value) {
        return new SelectorImpl(
            predicate.getField().getColumn(),
            predicate.getOperator(),
            value
        );
    }

    /**
     * Constructs a new {@link Selector} based on a unary predicate.
     *
     * @param predicate  the predicate
     * @param <ENTITY>   the type of the entity
     * @return           the constructed {@link Selector}
     * @see              UnaryPredicateBuilder
     */
    public static <ENTITY> Selector fromUnaryPredicate(UnaryPredicateBuilder<ENTITY> predicate) {
        return new SelectorImpl(
            predicate.getField().getColumn(),
            predicate.getOperator(),
            null
        );
    }

    /**
     * Constructs a standard key-value selector.
     * @param key    the key
     * @param value  the expected value
     * @return       a selector for that match
     */
    public static Selector standard(Column key, Object value) {
        return new SelectorImpl(key, StandardBinaryOperator.EQUAL, value);
    }
}