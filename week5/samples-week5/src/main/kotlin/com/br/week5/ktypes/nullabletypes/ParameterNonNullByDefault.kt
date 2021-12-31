package com.br.week5.ktypes.nullabletypes

import java.lang.annotation.ElementType
import javax.annotation.meta.TypeQualifierDefault

/*
    https://stackoverflow.com/questions/4963300/which-notnull-java-annotation-should-i-use
    https://stackoverflow.com/questions/11776302/how-to-indicate-that-member-fields-are-nonnull-by-default
 */

@javax.annotation.Nonnull
@TypeQualifierDefault(ElementType.PARAMETER)
annotation class ParameterNonNullByDefault
