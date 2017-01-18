package com.gw.viewfinder;

import com.gw.viewfinder.provider.Provider;

public interface Finder<T> {
    void inject(T host, Object source, Provider provider);
}