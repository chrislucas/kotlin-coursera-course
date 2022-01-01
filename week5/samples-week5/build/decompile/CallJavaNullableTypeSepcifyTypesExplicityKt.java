/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.br.sample.nullability.SampleNullableType
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 */
package com.br.week5.ktypes.nullabletypes;

import com.br.sample.nullability.SampleNullableType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mv={1, 6, 0}, k=2, xi=48, d1={"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0002\u001a\b\u0010\u0002\u001a\u00020\u0001H\u0002\u001a\u0006\u0010\u0003\u001a\u00020\u0001\u00a8\u0006\u0004"}, d2={"checkSpecifyExplicitTypeAsNotNull", "", "checkSpecifyExplicitTypeAsNullable", "main", "samples-week5"})
public final class CallJavaNullableTypeSepcifyTypesExplicityKt {
    private static final void checkSpecifyExplicitTypeAsNullable() {
        String type;
        SampleNullableType sample = new SampleNullableType();
        String string = type = sample.getType();
        System.out.println(string == null ? null : Integer.valueOf(string.length()));
    }

    private static final void checkSpecifyExplicitTypeAsNotNull() {
        SampleNullableType sample = new SampleNullableType();
        String string = sample.getType();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"sample.type");
        String type = string;
        System.out.println(type.length());
    }

    public static final void main() {
        CallJavaNullableTypeSepcifyTypesExplicityKt.checkSpecifyExplicitTypeAsNotNull();
    }

    public static /* synthetic */ void main(String[] args) {
        CallJavaNullableTypeSepcifyTypesExplicityKt.main();
    }
}
