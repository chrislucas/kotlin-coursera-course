/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 */
package com.br.week4.properties.fields;

import com.br.week4.properties.fields.State;
import com.br.week4.properties.fields.StateLogger;
import kotlin.Metadata;

@Metadata(mv={1, 5, 1}, k=2, xi=48, d1={"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u00a8\u0006\u0002"}, d2={"main", "", "StudiesWeek1"})
public final class StateLoggerKt {
    public static final void main() {
        StateLogger stateLogger = new StateLogger();
        stateLogger.setState(State.ON);
        boolean bl = false;
        System.out.println(stateLogger);
    }

    public static /* synthetic */ void main(String[] args) {
        StateLoggerKt.main();
    }
}
