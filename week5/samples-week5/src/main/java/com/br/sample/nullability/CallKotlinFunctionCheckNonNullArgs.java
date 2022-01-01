package com.br.sample.nullability;

import com.br.week5.ktypes.nullabletypes.CheckParameterIsNotNullKt;

public class CallKotlinFunctionCheckNonNullArgs {
    public static void main(String[] args) {
        CheckParameterIsNotNullKt.checker("Teste");
        /*
            Exception in thread "main" java.lang.NullPointerException:
                Parameter specified as non-null is null:
                method com.br.week5.ktypes.nullabletypes.CheckParameterIsNotNullKt.checker, parameter value
         */
        //CheckParameterIsNotNullKt.checker(null);
    }
}
