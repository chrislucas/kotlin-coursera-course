package com.br.sample.nullability;

import javax.annotation.meta.TypeQualifierDefault;
import java.lang.annotation.ElementType;

@javax.annotation.Nonnull
@TypeQualifierDefault({ElementType.PARAMETER, ElementType.METHOD})
public @interface ParameterNonNullDefault {
}
