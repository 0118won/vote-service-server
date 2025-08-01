package org.zerock.voteservice.adapter.out.grpc.stub;

import domain.event.block.protocol.BlockEventServiceGrpc;
import domain.event.block.protocol.BlockCreatedEventRequest;
import domain.event.block.protocol.BlockCreatedEventResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zerock.voteservice.adapter.out.grpc.common.AbstractGrpcClientStub;


@Log4j2
@Component
public class GrpcBlockEventServiceStub extends AbstractGrpcClientStub {
    private final BlockEventServiceGrpc.BlockEventServiceBlockingStub stub;

    public GrpcBlockEventServiceStub(
            @Value("${grpc.server.event.block.host}") String host,
            @Value("${grpc.server.event.block.port}") int port
    ) {
        super("L4", BlockEventServiceGrpc.class.getSimpleName(), host, port);

        stub = BlockEventServiceGrpc.newBlockingStub(channel);
    }

    public BlockCreatedEventResponse reportBlockCreatedEvent(
            String topic, int transactionCount, int height
    ) throws RuntimeException {
        BlockCreatedEventRequest request = BlockCreatedEventRequest.newBuilder()
                .setTopic(topic)
                .setTransactionCount(transactionCount)
                .setHeight(height)
                .build();

        return stub.reportBlockCreatedEvent(request);
    }
}
