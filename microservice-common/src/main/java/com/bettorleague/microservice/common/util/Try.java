package com.bettorleague.microservice.common.util;

import org.springframework.util.CollectionUtils;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.function.Supplier;

import static java.util.Objects.nonNull;

public abstract class Try<T> {
    Try() {

    }

    public static <U> Try<U> of(Supplier<U> supplier) {
        try {
            return successful(supplier.get());
        } catch (Exception exception) {
            return failure(exception);
        }
    }

    public abstract boolean isSuccess();

    public abstract boolean isFailure();

    public abstract int status();

    public abstract Exception getFailure();

    public abstract T orElse(T value);

    public abstract <E extends RuntimeException> T orElseThrow(E exception) throws Exception;

    public abstract T orElseThrow(String notFoundMessage);

    public abstract T get();

    public static <T, E extends RuntimeException> Try<T> failure(Exception exception) {
        return new Try.Failure((RuntimeException) exception);
    }

    public static <T> Try<T> successful(T x) {
        return new Try.Success(x);
    }

    static class Failure<T, E extends RuntimeException> extends Try<T> {
        E exception;

        Failure(E exception) {
            this.exception = exception;
        }

        @Override
        public T get() {
            return null;
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public boolean isFailure() {
            return true;
        }

        @Override
        public int status() {
            return 500;
        }

        @Override
        public Exception getFailure() {
            return this.exception;
        }

        @Override
        public T orElse(T value) {
            return value;
        }

        @Override
        public <E extends RuntimeException> T orElseThrow(E e) {
            throw e;
        }

        @Override
        public T orElseThrow(String notFoundMessage) {
            throw this.exception;
        }
    }

    static class Success<T> extends Try<T> {
        private final T value;

        Success(T value) {
            this.value = value;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public boolean isFailure() {
            return false;
        }

        @Override
        public int status() {
            return 200;
        }

        @Override
        public Exception getFailure() {
            return null;
        }

        @Override
        public T orElse(T value) {
            return nonNull(this.value) ? this.value : value;
        }

        @Override
        public <E extends RuntimeException> T orElseThrow(E exception) {
            return this.value;
        }

        @Override
        public T orElseThrow(String notFoundMessage) {
            if (nonNull(this.value) && (!(this.value instanceof List) || !CollectionUtils.isEmpty((List) this.value))) {
                return this.value;
            } else {
                throw new ResourceAccessException(notFoundMessage);
            }
        }

        @Override
        public T get() {
            return this.value;
        }
    }
}
