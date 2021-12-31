package com.br.sample.nullability;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JavaNullableTypes {

    public static class Data {
        @Nullable
        private final Integer id;
        @NotNull
        private final Long timestamp;

        private final String hash;

        public Data(@Nullable Integer id, long timestamp, String hash) {
            this.id = id;
            this.timestamp = timestamp;
            this.hash = hash;
        }

        @Nullable
        public Integer getId() {
            return id;
        }

        @NotNull
        public Long getTimestamp() {
            return timestamp;
        }
    }
}
