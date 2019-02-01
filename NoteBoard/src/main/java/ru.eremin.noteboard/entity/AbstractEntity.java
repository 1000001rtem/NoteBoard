package ru.eremin.noteboard.entity;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

public abstract class AbstractEntity {

    public abstract boolean equals(Object o);

    public abstract int hashCode();

    public abstract String toString();

}
