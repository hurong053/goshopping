package com.shopping.server.auth.redis.clients.jedis;

public abstract class Builder<T> {
    public abstract T build(Object data);
}
