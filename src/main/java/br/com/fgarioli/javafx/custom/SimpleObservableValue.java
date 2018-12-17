package br.com.fgarioli.javafx.custom;

import javafx.beans.value.ObservableValueBase;

/**
 *
 * @author fernando Classe auxiliar para setar propriedades de objetos em
 * tableview's
 */
public class SimpleObservableValue<T> extends ObservableValueBase<T> {

    private T value;

    public SimpleObservableValue(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }
}
