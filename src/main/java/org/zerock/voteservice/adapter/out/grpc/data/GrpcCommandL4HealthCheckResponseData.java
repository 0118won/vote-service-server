package org.zerock.voteservice.adapter.out.grpc.data;

import domain.event.admin.L4.protocol.L4HealthCheckResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.voteservice.adapter.out.grpc.common.GrpcResponseData;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrpcCommandL4HealthCheckResponseData implements GrpcResponseData {
    Boolean success;
    String status;
    String pong;
    String ip;
    List<Integer> ports;

    public GrpcCommandL4HealthCheckResponseData(L4HealthCheckResponse response) {
        this.success = response.getConnected();
        this.status = response.getStatus();
        this.pong = response.getPong();
        this.ip = response.getIp();
        this.ports = response.getPortsList();
    }
}
