package org.zerock.voteservice.adapter.in.admin.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.zerock.voteservice.adapter.in.admin.domain.request.grpc.CommandL4HealthCheckGrpcRequestDto;
import org.zerock.voteservice.adapter.in.admin.domain.response.CommandL4HealthCheckSuccessResponseDto;
import org.zerock.voteservice.adapter.in.common.Processor;
import org.zerock.voteservice.adapter.in.common.ResponseDto;
import org.zerock.voteservice.adapter.out.grpc.proxy.CommandProxy;
import org.zerock.voteservice.adapter.out.grpc.result.GrpcCommandL4HealthCheckResponseResult;

@Log4j2
@Service
@RequiredArgsConstructor
public class CommandL4HealthCheckProcessor implements Processor<
        CommandL4HealthCheckGrpcRequestDto,
        GrpcCommandL4HealthCheckResponseResult
        > {

    private final CommandProxy proxy;

    @Override
    public GrpcCommandL4HealthCheckResponseResult execute(
            CommandL4HealthCheckGrpcRequestDto dto
    ) {
        return proxy.L4CheckHealth(dto);
    }

    @Override
    public ResponseEntity<? extends ResponseDto> getSuccessResponseEntity(
            CommandL4HealthCheckGrpcRequestDto dto,
            GrpcCommandL4HealthCheckResponseResult result
    ) {
        CommandL4HealthCheckSuccessResponseDto successDto = CommandL4HealthCheckSuccessResponseDto.builder()
                .success(result.getSuccess())
                .status(result.getStatus())
                .message(result.getMessage())
                .httpStatusCode(result.getHttpStatusCode())
                .ping(dto.getPing())
                .pong(result.getData().getPong())
                .ip(result.getData().getIp())
                .ports(result.getData().getPorts())
                .build();

        return new ResponseEntity<>(successDto, HttpStatus.valueOf(successDto.getHttpStatusCode()));
    }
}
