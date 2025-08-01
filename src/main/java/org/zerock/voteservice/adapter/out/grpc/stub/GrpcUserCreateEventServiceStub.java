package org.zerock.voteservice.adapter.out.grpc.stub;

import com.google.protobuf.Timestamp;

import domain.event.user.create.protocol.UserCreateEventServiceGrpc;
import domain.event.user.create.protocol.UserValidateEventRequest;
import domain.event.user.create.protocol.UserValidateEventResponse;
import domain.event.user.create.protocol.UserCacheEventRequest;
import domain.event.user.create.protocol.UserCacheEventResponse;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.zerock.voteservice.adapter.out.grpc.common.AbstractGrpcClientStub;


@Log4j2
@Component
public class GrpcUserCreateEventServiceStub extends AbstractGrpcClientStub {
    private final UserCreateEventServiceGrpc.UserCreateEventServiceBlockingStub stub;

    public GrpcUserCreateEventServiceStub(
            @Value("${grpc.server.event.user.create.host}") String host,
            @Value("${grpc.server.event.user.create.port}") int port
    ) {
        super("L3", UserCreateEventServiceGrpc.class.getSimpleName(), host, port);

        stub = UserCreateEventServiceGrpc.newBlockingStub(channel);
    }

    public UserValidateEventResponse validateUser(
            Long uid, String userHash
    ) throws RuntimeException {
        UserValidateEventRequest request = UserValidateEventRequest.newBuilder()
                .setUid(uid)
                .setUserHash(userHash)
                .build();

        return stub.validateUserEvent(request);
    }

    public UserCacheEventResponse cacheUser(
            Long uid, String userHash, String gender, Timestamp birthDate
    ) throws RuntimeException {
        UserCacheEventRequest request = UserCacheEventRequest.newBuilder()
                .setUid(uid)
                .setUserHash(userHash)
                .setGender(gender)
                .setBirthDate(birthDate)
                .build();

        return stub.cacheUserEvent(request);
    }
}
