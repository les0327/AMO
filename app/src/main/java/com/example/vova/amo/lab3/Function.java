package com.example.vova.amo.lab3;

import java.io.Serializable;

/**
 * Created on 16.03.2017.
 *
 * @author Les.
 * @version 1.0.
 */
public interface Function extends Serializable {
    double valueAt(double value);
}
