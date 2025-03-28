package org.polaris2023.wild_wind.util.interfaces;

import net.minecraft.data.DataProvider;

public interface ILanguage<T extends ILanguage<T> & DataProvider> extends IData<T> {
    T add(Object r, String value);
    default void init() {}
}
