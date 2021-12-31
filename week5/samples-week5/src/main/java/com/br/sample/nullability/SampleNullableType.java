package com.br.sample.nullability;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SampleNullableType {

    // 'null' is returned by the method declared as @ParameterNonNullDefault
    public String getType() {
        return null;
    }

    @Nullable
    public String getDescription() {
        return null;
    }

    public static void show(@Nullable String value) {
        System.out.println(value);
    }

    public static void showNotNull(@NotNull String value) {
        show(value);
    }
}
