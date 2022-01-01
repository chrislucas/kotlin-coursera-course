package com.br.week5.ktypes.nullabletypes

fun checker(value: String) {
    /**
     * @see kotlin.jvm.internal.Intrinsics
        Decompiler
           public static final void checker(@NotNull String value) {
              Intrinsics.checkNotNullParameter(value, "value");
              System.out.println(value);
           }

           public static void checkNotNullParameter(Object value, String paramName) {
                if (value == null) {
                    throwParameterIsNullNPE(paramName);
                }
            }
     */
    println(value)
}