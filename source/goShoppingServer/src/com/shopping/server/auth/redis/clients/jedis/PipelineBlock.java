package com.shopping.server.auth.redis.clients.jedis;


public abstract class PipelineBlock extends Pipeline {
    public abstract void execute();
}
